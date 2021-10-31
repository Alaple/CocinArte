package com.bifrost.cocinarte.entities

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiCaller {
    @GET("v2")
    fun listRecipes(@Query("type") type: String, @Query("q") search: String, @Query("app_id") key: String, @Query("app_key") appKey: String, @Query("health") lista: ArrayList<String>): Call<EdamamResponse>

}