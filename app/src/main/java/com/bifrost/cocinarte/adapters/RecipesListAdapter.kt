package com.bifrost.cocinarte.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.bifrost.cocinarte.R
import com.bifrost.cocinarte.entities.Filter
import com.bifrost.cocinarte.entities.RecipesDataCollectionItem
import com.google.android.material.chip.Chip

class RecipesListAdapter (
    private var recipesList: MutableList<RecipesDataCollectionItem>

): RecyclerView.Adapter<RecipesListAdapter.RecipeHolder>(){



    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecipesListAdapter.RecipeHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recipe_item, parent,false)
        return (RecipesListAdapter.RecipeHolder(view))
    }

    override fun onBindViewHolder(holder: RecipesListAdapter.RecipeHolder, position: Int) {

        holder.setName(recipesList[position].name)


        holder.getCardLayout().setOnClickListener() {
            //TODO  onItemClick(buttonsList[position].nombreVino)

        }
    }
    class RecipeHolder(v: View) : RecyclerView.ViewHolder(v) {
    private var view: View

    init{
        this.view = v
    }

    fun setName(name: String){

        val txt : Button = view.findViewById(R.id.txtRecipe)
        txt.setText(name)
    }

    fun getCardLayout (): Button {
        return view.findViewById(R.id.cardRecipe)
    }

    fun onClick(){
        val chip: Chip = view.findViewById(R.id.toggleButton)
        chip.closeIconTint


        //TODO  val txt : TextView = view.findViewById(R.id.wineName)
        //txt.text = name
    }


    val txt : Button = view.findViewById(R.id.toggleButton)

}

override fun getItemCount(): Int {

    return recipesList.size

}
}