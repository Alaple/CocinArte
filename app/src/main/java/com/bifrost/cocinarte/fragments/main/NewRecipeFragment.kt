package com.bifrost.cocinarte.fragments.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.findNavController
import com.bifrost.cocinarte.R
import com.bifrost.cocinarte.fragments.login.RegisterFragmentDirections
import com.bifrost.cocinarte.models.main.NewRecipeModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class NewRecipeFragment : Fragment() {

    lateinit var v: View
    lateinit var btnCreate: Button
    lateinit var inputRName: EditText
    lateinit var inputRDescription: EditText
    private lateinit var auth: FirebaseAuth

    companion object {
        fun newInstance() = NewRecipeFragment()
    }

    private lateinit var viewModel: NewRecipeModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.new_recepie_fragment, container, false)

        btnCreate = v.findViewById(R.id.btnCreate)
        inputRName = v.findViewById(R.id.inputRName)
        inputRDescription = v.findViewById(R.id.inputRDescription)

        auth = FirebaseAuth.getInstance()

        return v
    }

    override fun onStart() {
        super.onStart()

        // Initialize all buttons variables
        initializeButtons()
    }

    private fun initializeButtons() {

        btnCreate.setOnClickListener() {
            if(inputRName.text.isNotEmpty() &&
                inputRDescription.text.isNotEmpty()) {
                val recipeName = inputRName.text.toString()
                val recipeDescription = inputRDescription.text.toString()
                val scope = CoroutineScope(Dispatchers.Default)

                scope.launch{
                    val createUserJob = async{
                        //Add user to Auth
                        try{
                            viewModel.createNewRecipe(
                                recipeName,
                                recipeDescription
                            )
                            //Add user to DB
                            viewModel.createDbNewRecipe(
                                recipeName,
                                recipeDescription
                            )
                        } catch (e: Exception) {
                            Log.d("Coroutine: ", e.message.toString())
                        }
                    }
                    val logIn = async {

                        createUserJob.await()

                        auth.signInWithEmailAndPassword(
                            inputRName.text.toString(),
                            inputRDescription.text.toString()
                        ).addOnCompleteListener(requireActivity()) {
                            val action = RegisterFragmentDirections.actionRegisterFragmentToMainActivity()
                            v.findNavController().navigate(action)
                        }
                    }

                    logIn.await()
                }
            } else {
                Toast.makeText(context,"Input fields cannot be empty", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(NewRecipeModel::class.java)
        // TODO: Use the ViewModel
    }

}