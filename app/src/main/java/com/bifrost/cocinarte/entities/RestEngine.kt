package com.bifrost.cocinarte.entities

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory

class RestEngine {
    companion object{


        var gson : Gson = GsonBuilder()
            .registerTypeAdapter(RecipeHit::class.java, Deserealizer<RecipeHit>())
            .create()

        fun getRestEngine(): Retrofit{
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            //val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
            val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl("https://api.edamam.com/api/recipes/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

            return retrofit
        }
    }
}