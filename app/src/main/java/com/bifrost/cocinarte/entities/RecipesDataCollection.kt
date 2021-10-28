package com.bifrost.cocinarte.entities

import java.util.ArrayList

class RecipesDataCollection: ArrayList<RecipesDataCollectionItem>()

data class RecipesDataCollectionItem(
    val name: String //El nombrede la receta

)