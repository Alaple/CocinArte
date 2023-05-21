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
import android.widget.Toast
import androidx.navigation.findNavController
import com.bifrost.cocinarte.R
import com.bifrost.cocinarte.entities.UserRecipe
import com.bifrost.cocinarte.fragments.login.RegisterFragmentDirections
import com.bifrost.cocinarte.models.main.AccountProfileViewModel
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
    lateinit var inputRCalories: EditText
    lateinit var inputRImageUrl: EditText
    lateinit var inputRRecepieUrl: EditText
    lateinit var inputRTime: EditText
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
        inputRCalories = v.findViewById(R.id.inputRCalories)
        inputRImageUrl = v.findViewById(R.id.inputRImageUrl)
        inputRRecepieUrl = v.findViewById(R.id.inputRRecepieUrl)
        inputRTime = v.findViewById(R.id.inputRTime)

        auth = FirebaseAuth.getInstance()

        return v
    }

    override fun onStart() {
        super.onStart()

        // Initialize all buttons variables
        initializeButtons()

        // TEXTOS PARA PRUEBAS
        initializeText()
    }

    private fun initializeText() {
        inputRName.setText("Curry básico")
        inputRDescription.setText("Curry básico")
        inputRCalories.setText("200")
        inputRImageUrl.setText("https://c8i5i9x9.stackpathcdn.com/download/bancorecursos/recetas/receta-curry-basico.jpg")
        inputRRecepieUrl.setText("https://www.cocinista.es/web/es/recetas/cocina-internacional/india/curry-basico.html")
        inputRTime.setText("60")
    }

    private fun initializeButtons() {
        btnCreate.setOnClickListener() {
            if(inputRName.text.isNotEmpty() &&
                inputRDescription.text.isNotEmpty() &&
                inputRCalories.text.isNotEmpty() &&
                inputRImageUrl.text.isNotEmpty() &&
                inputRRecepieUrl.text.isNotEmpty() &&
                inputRTime.text.isNotEmpty()) {

                val recipeLabel = inputRName.text.toString()
                val recipeDescription = inputRDescription.text.toString()
                val recipeCalories = inputRCalories.text.toString().toDouble()
                val recipeImageUrl = inputRImageUrl.text.toString()
                val recipeURL = inputRRecepieUrl.text.toString()
                val recipeTime = inputRTime.text.toString().toDouble()

                viewModel.createDbNewRecipe(
                    recipeCalories,
                    recipeDescription,
                    recipeImageUrl,
                    recipeLabel,
                    recipeTime,
                    recipeURL
                )

                val action = NewRecipeFragmentDirections.actionNewRecipeFragment3ToMyRecipesFragment() //RegisterFragmentDirections.actionRegisterFragmentToMainActivity()
                v.findNavController().navigate(action)
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

    interface RecipeCreationCallback {
        fun onRecipeCreated()
    }
}