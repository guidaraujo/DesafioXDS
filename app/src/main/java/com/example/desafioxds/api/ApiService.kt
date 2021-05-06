package com.example.desafioxds.api

import com.example.desafioxds.model.LoginResponse
import com.example.desafioxds.model.PizzaResponse
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @POST("signin")
    fun login(@Body q:RequestBody?):Call<LoginResponse>

    @GET("pizza")
    fun getPizzaList():Call<List<PizzaResponse>>

    @GET("pizza")
    fun pizzaString():Call<ResponseBody>
}