package com.bifrost.cocinarte.dialogs

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.DialogFragment
import androidx.navigation.findNavController
import com.bifrost.cocinarte.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ResetPasswordDialogFragment : DialogFragment() {

    lateinit var v: View

    lateinit var rootLayout: ConstraintLayout

    // Component variables
    lateinit var txtTitle: TextView
    lateinit var txtText: TextView
    lateinit var btnAccept: Button
    lateinit var btnCancel: Button

    //Firebase
    val db = Firebase.firestore
    var auth = FirebaseAuth.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_reset_password_dialog, container, false)


        // Initialize variables
        txtTitle = v.findViewById(R.id.txtResetPasswordDialogTitle)
        txtText = v.findViewById(R.id.txtResetPasswordDialogText)
        btnAccept = v.findViewById(R.id.btnResetPasswordDialogOK)
        btnCancel = v.findViewById(R.id.btnResetPasswordDialogCancel)

        return v
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dialog
    }

    companion object {
        const val TAG = "ResetPasswordConfirmationDialog"
    }

    override fun onStart() {
        super.onStart()

        // Initialize all text variables
        initializeText()

        btnCancel.setOnClickListener() {
            dismiss()
        }

        btnAccept.setOnClickListener {
            val email: String =  auth.currentUser!!.email.toString()

            auth.sendPasswordResetEmail(email)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            dismiss()
                            Toast.makeText(this.context, "EMAIL SENT", Toast.LENGTH_LONG).show()
                        }
                    }
        }
    }

    private fun initializeText() {
        txtTitle.setText("Reset your password")
        txtText.setText("We'll send a password reset link to your email.")
        btnAccept.setText("Reset")
        btnCancel.setText("Cancel")
    }
}