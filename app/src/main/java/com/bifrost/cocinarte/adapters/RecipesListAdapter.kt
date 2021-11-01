package com.bifrost.cocinarte.adapters

import android.media.Image
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bifrost.cocinarte.R
import com.bifrost.cocinarte.entities.Filter
import com.bifrost.cocinarte.entities.RecipeHit
import com.bifrost.cocinarte.entities.RecipesDataCollectionItem
import com.bumptech.glide.Glide
import com.google.android.material.chip.Chip
import kotlin.properties.Delegates

class RecipesListAdapter (
    //private var recipesList: MutableList<RecipesDataCollectionItem>

): RecyclerView.Adapter<RecipesListAdapter.RecipeHolder>(){

    var recipesListForAdapter: List<RecipeHit> by Delegates.observable(emptyList()){ _, _, _ -> notifyDataSetChanged() }


    fun setData(data: List<RecipeHit>){
        recipesListForAdapter = data
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
            //TODO  onItemClick(buttonsList[position])

        }
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


        //TODO  val txt : TextView = view.findViewById(R.id.wineName)
        //txt.text = name
    }


    //val txt : Button = view.findViewById(R.id.toggleButton)

}

override fun getItemCount(): Int {

    return recipesListForAdapter.size

}
}