package com.bifrost.cocinarte.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bifrost.cocinarte.R
import com.bifrost.cocinarte.entities.RecipeHit
import com.bumptech.glide.Glide
import com.google.android.material.chip.Chip
import kotlin.properties.Delegates

class RecipesListAdapter (
    //private var userRecipesList: MutableList<RecipeHit>,

    val onCardItemsClick: (Int) -> Boolean

): RecyclerView.Adapter<RecipesListAdapter.RecipeHolder>(){

    var recipesListForAdapter: List<RecipeHit> by Delegates.observable(emptyList()){ _, _, _ -> notifyDataSetChanged() }
    var recipesFromUser: List<RecipeHit> by Delegates.observable(emptyList()){ _, _, _ -> notifyDataSetChanged() }

    fun clear(){
        this.recipesListForAdapter = emptyList()
    }

    fun setData(data: List<RecipeHit>){
        recipesListForAdapter = data
    }

    fun setPrepared(userRecipesList: List<RecipeHit>){
        recipesFromUser = userRecipesList
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecipesListAdapter.RecipeHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recipe_item, parent,false)
        return (RecipesListAdapter.RecipeHolder(view))
    }

    override fun onBindViewHolder(holder: RecipesListAdapter.RecipeHolder, position: Int) {
        try{
            holder.setName(recipesListForAdapter[position].label!!)
        }catch (e: Error){
            e.message?.let { Log.d("Error", it) }
        }

        holder.setImage(recipesListForAdapter[position].image_url!!)



        holder.getCardLayout().setOnClickListener() {
            onCardItemsClick(position)

        }

        //marcar como preparado
        if (checkPrepared(recipesListForAdapter[position],
                recipesFromUser as List<RecipeHit>
            )){
                //Log.d("List Position", position.toString())
                //Log.d("Adapter Position", holder.adapterPosition.toString())
            holder.getCardLayout().setCardBackgroundColor(0xFF2fdbbc.toInt())

        }



    }
    fun checkPrepared(recipeHit: RecipeHit, userRecipesList: List<RecipeHit>): Boolean{
        var found = false
        for(recipe in userRecipesList){
            if (recipeHit.getId().equals(recipe.getId()) ){
                //Log.d("RecipeParameter",recipeHit.getId().toString())
                //Log.d("RecipeInList", recipe.getId().toString())
                found = true
                //markPrepared(recipeHit)
            }
        }
        return found

    }



    class RecipeHolder(v: View) : RecyclerView.ViewHolder(v) {
    private var view: View

    init{
        this.view = v
    }

    fun setName(name: String){

        val txt : TextView = view.findViewById(R.id.txtRecipe)
        txt.setText(name)
    }

        fun setImage (urlImage : String) {
            var imgMovie : ImageView = view.findViewById(R.id.imgRecipe)
            Glide.with(getCardLayout())
                .load(urlImage)
                .into(imgMovie)
        }

    fun getCardLayout (): CardView {
        return view.findViewById(R.id.cardRecipe)
    }

        

    fun onClick(){
        val chip: Chip = view.findViewById(R.id.toggleButton)
        chip.closeIconTint

    }



}

    override fun getItemCount(): Int {

        return recipesListForAdapter.size

    }
}