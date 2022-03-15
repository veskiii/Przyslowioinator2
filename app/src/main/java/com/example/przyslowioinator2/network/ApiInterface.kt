package com.example.przyslowioinator2.network

import com.example.przyslowioinator2.model.Przyslowie
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {

    @GET("randomPrzyslowie")
    suspend fun getRandomPrzyslowie() : Response<Przyslowie>

    @GET("przyslowia")
    suspend fun getPrzyslowia() : Response<List<Przyslowie>>

    @GET("przyslowie/{id}")
    suspend fun getPrzyslowie(@Path("id") id : String) : Response<Przyslowie>
}