package com.bifrost.cocinarte.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import com.bifrost.cocinarte.R
import com.bifrost.cocinarte.entities.*
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore

class LoginActivity : AppCompatActivity() {

    // Google SigIn
    private lateinit var mGoogleSignInClient: GoogleSignInClient
    private var RC_SIGN_IN = 123

    // Firebase
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    private lateinit var navHostFragment : NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Navigation
        navHostFragment = supportFragmentManager.findFragmentById(R.id.logInContainerView) as NavHostFragment

        //Firebase
        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        createRequest()
    }

    private fun createRequest() {
        // Configure Google Sign In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("926429645876-8tgh3m7ks5m6uudgag6e3p6c64ffn4jp.apps.googleusercontent.com")
            .requestEmail()
            .build()

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    fun signIn() {
        val signInIntent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                //Toast.makeText(this, "firebaseAuthWithGoogle: " + account.id, Toast.LENGTH_LONG).show()
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                //Toast.makeText(this, "Google sign in failed " + e.message, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    //Toast.makeText(this, "signInWithCredential:success", Toast.LENGTH_LONG).show()
                    Toast.makeText(this, "Sign In With Credential Successfull", Toast.LENGTH_SHORT).show()
                    val user = auth.currentUser
                    // If its a new user create it on Firestore
                    checkExistingUser(user)
                } else {
                    // If sign in fails, display a message to the user.
                    //Toast.makeText(this, "signInWithCredential:failure" + task.exception, Toast.LENGTH_LONG).show()
                }
            }
    }

    private fun checkExistingUser(user: FirebaseUser?) {
        if (user != null) {
            Toast.makeText(this, user.email + " Auth User", Toast.LENGTH_LONG).show()
            val docRef = db.collection("users").document(user.email!!)
            docRef.get()
                .addOnSuccessListener { document ->
                    if (document.data != null) {
                        //Log.d(TAG, "DocumentSnapshot data: ${document.data}")
                        //Toast.makeText(this, "DocumentSnapshot data: ${document.data}", Toast.LENGTH_LONG).show()
                    } else {
                        //Log.d(TAG, "No such document")
                        //Toast.makeText(this, "No such document", Toast.LENGTH_LONG).show()
                        // IF THE FIRESTORE USER DONT EXIST CREATE IT
                        createDbUser(user.displayName!!, user.email!!)
                    }
                    // NAVIGATE TO MAIN FRAGMENT
                    val intent = Intent(applicationContext, MainActivity::class.java)
                    startActivity(intent)
                }
                .addOnFailureListener { exception ->
                    //Log.d(TAG, "get failed with ", exception)
                    //Toast.makeText(this, "get failed with " + exception.message, Toast.LENGTH_LONG).show()
                }
        }
    }

    fun createDbUser(name: String, email: String) {
        var reward : MutableList<Reward> = mutableListOf()
        var preference : Preference = Preference()
        var favorite: MutableList<RecipeHit> = mutableListOf()
        var prepared: MutableList<RecipeHit> = mutableListOf()
        var profile: Category = Category.NONE
        val user = User(name,
            email,
            "",
            true,
            0,
            reward,
            preference,
            favorite,
            prepared,
            profile
        )
        uploadUser(user)
    }

    fun uploadUser(user: User){
        db.collection("users").document(user.email!!).set(user)
    }
}