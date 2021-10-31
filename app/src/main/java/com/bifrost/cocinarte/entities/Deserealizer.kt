package com.bifrost.cocinarte.entities

import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class Deserealizer<T> : JsonDeserializer<T> {
    @Override
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): T {
        var recipe : JsonElement = json!!.asJsonObject.get("recipe")

        return Gson().fromJson(recipe, typeOfT)
    }

}