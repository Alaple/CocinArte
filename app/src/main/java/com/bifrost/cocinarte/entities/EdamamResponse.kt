package com.bifrost.cocinarte.entities

import androidx.annotation.NonNull
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class EdamamResponse {
    @SerializedName("hits")
    @Expose
    var hitList =  ArrayList<RecipeHit>()

    fun getHits(): ArrayList<RecipeHit>{
      return hitList
    }

    @NonNull
    @Override
    override fun toString(): String {
        return hitList.toString()
    }

}