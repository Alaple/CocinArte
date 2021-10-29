package com.bifrost.cocinarte.entities

import retrofit2.Retrofit
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory

class RestEngine {
    companion object{
        fun getRestEngine(): Retrofit{
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            //val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
            val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl("https://api.edamam.com/api/recipes/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit
        }
    }
}