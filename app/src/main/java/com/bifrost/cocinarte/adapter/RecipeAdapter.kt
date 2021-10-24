package com.bifrost.cocinarte.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bifrost.cocinarte.entities.Recipe
import com.bifrost.cocinarte.R

class RecipeAdapter (
    var recipes : MutableList <Recipe>,
    var onClick : (Int) -> Unit
) : RecyclerView.Adapter<RecipeAdapter.RecipeHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeHolder {
        val view =  LayoutInflater.from(parent.context).inflate(R.layout.recipe_item,parent,false)
        return (RecipeHolder(view))
    }

    override fun onBindViewHolder(holder: RecipeHolder, position: Int) {
       holder.setTitle(recipes[position].title)
       holder.setImage(recipes[position].image_url)
       holder.getCardView().setOnClickListener {
           onClick(position)
       }
    }

    override fun getItemCount(): Int {
      return recipes.size
    }

    class RecipeHolder (v: View) : RecyclerView.ViewHolder(v) {
        private var view: View

        init {
            this.view = v
        }

        fun setTitle (title : String) {
            var txtRecipe : TextView = view.findViewById(R.id.txtRecipe)
            txtRecipe.text = title
        }

        fun setImage (image : String) {
            var imgRecipe : ImageView = view.findViewById(R.id.imgRecipe)
            val uri: Uri = Uri.parse(image)
            //imgRecipe.setImageURI(uri)
            imgRecipe.setImageResource(R.drawable.lacinato_salad)
        }

        fun getCardView () : CardView {
            return view.findViewById(R.id.cardRecipe)
        }
    }
}