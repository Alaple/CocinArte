package com.bifrost.cocinarte.adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bifrost.cocinarte.entities.Recipe
import com.bifrost.cocinarte.R
import java.io.InputStream
import java.lang.Exception
import java.net.URL

class RecipeAdapter (
    private var recipes : MutableList <Recipe>,
    private var onClick : (Int) -> Unit
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
            val txtRecipe : TextView = view.findViewById(R.id.txtRecipe)
            txtRecipe.text = title
        }

        fun setImage (image : String) {
            val imgRecipe : ImageView = view.findViewById(R.id.imgRecipe)

            try {
                val `is`: InputStream = URL(image).content as InputStream
                val imgDrawable = Drawable.createFromStream(`is`, "src image")
                imgRecipe.setImageDrawable(imgDrawable)
            } catch (e: Exception) {
                // No fue posible cargar la imagen.
            }
        }

        fun getCardView () : CardView {
            return view.findViewById(R.id.cardRecipe)
        }
    }
}