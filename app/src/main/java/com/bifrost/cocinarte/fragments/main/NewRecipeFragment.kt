package com.bifrost.cocinarte.fragments.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.InputType
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bifrost.cocinarte.R
import com.bifrost.cocinarte.adapters.ButtonListAdapter
import com.bifrost.cocinarte.models.main.ListIngredientsViewModel
import com.bifrost.cocinarte.models.main.NewRecipeModel
import com.google.firebase.auth.FirebaseAuth
import android.widget.LinearLayout
import android.widget.EditText
import kotlinx.coroutines.*


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

    lateinit var buttons: RecyclerView
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var buttonListAdapter: ButtonListAdapter
    private lateinit var buttonsViewModel: ListIngredientsViewModel

    private lateinit var containerIngredients: LinearLayout
    private lateinit var addButton: Button

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

        // Para las categorias
        containerIngredients = v.findViewById(R.id.containerIngredients);
        addButton = v.findViewById(R.id.inputAdd);

        // Para la lista de ingredientes
        buttonsViewModel = ViewModelProvider(requireActivity()).get(ListIngredientsViewModel::class.java)
        buttons = v.findViewById(R.id.buttonsRecView)
        buttonsViewModel = ViewModelProvider(requireActivity()).get(ListIngredientsViewModel::class.java)

        auth = FirebaseAuth.getInstance()


        addButton.setOnClickListener { addIngredientField() }

        return v
    }

    override fun onStart() {
        super.onStart()

        // Initialize all buttons variables
        initializeButtons()

        // TEXTOS PARA PRUEBAS
        initializeText()

        // LOAD USER (Coroutine)
        val job = Job()
        val scope = CoroutineScope(Dispatchers.Default + job)
        scope.launch {
            val getUser = async {
                try {
                    buttonsViewModel.loadUserProfile()
                } catch (e: Exception) {
                    e.message?.let { Log.d("Error", it) }
                }
            }

            getUser.await()
        }

        //Load RecyclerView for filters
        buttons.setHasFixedSize(true)
        linearLayoutManager = GridLayoutManager(context, 3)
        buttons.layoutManager = linearLayoutManager
        buttonListAdapter = ButtonListAdapter { i -> onItemsClick(i) }

        //Load RecyclerView for filters
        buttons.setHasFixedSize(true)
        buttons.layoutManager = GridLayoutManager(context, 3)

        buttonsViewModel.userLiveData.observe(viewLifecycleOwner, { result ->
            buttonListAdapter.defaultFilter = result.profile
            buttonListAdapter.buttonsList = buttonsViewModel.filters
            buttons.adapter = buttonListAdapter
        })
    }

    private fun onItemsClick(position: Int) {
        buttonsViewModel.selectFilter(position)
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

                // INGREDIENTS
                val recipeIngredients = mutableListOf<String>()
                for (i in 0 until containerIngredients.childCount) {
                    val ingredientView = containerIngredients.getChildAt(i) as? TextView
                    val ingredientText = ingredientView?.text.toString()
                    recipeIngredients.add(ingredientText)
                }

                // CATEGORIES
                val recipeCategory = mutableListOf<String>()
                for (i in buttonsViewModel.selectedFilters) {
                    recipeCategory.add(i.uppercase())
                }

                viewModel.createDbNewRecipe(
                    recipeCalories,
                    recipeDescription,
                    recipeImageUrl,
                    recipeLabel,
                    recipeTime,
                    recipeURL,
                    recipeIngredients,
                    recipeCategory
                )

                val action = NewRecipeFragmentDirections.actionNewRecipeFragment3ToMyRecipesFragment() //RegisterFragmentDirections.actionRegisterFragmentToMainActivity()
                v.findNavController().navigate(action)
            } else {
                Toast.makeText(context,"Input fields cannot be empty", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun addIngredientField() {
        val newIngredientField = EditText(activity)
        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(0, 30, 0, 30) // Agrega los márgenes deseados (en píxeles)
        newIngredientField.layoutParams = params
        newIngredientField.setBackgroundResource(R.drawable.input_round_style)
        newIngredientField.inputType =
            InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_CAP_SENTENCES
        newIngredientField.setPadding(0, 0, 0, 0)
        containerIngredients.addView(newIngredientField)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(NewRecipeModel::class.java)
        // TODO: Use the ViewModel
    }
}