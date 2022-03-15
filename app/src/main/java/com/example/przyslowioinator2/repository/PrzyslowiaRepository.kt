package com.example.przyslowioinator2.repository

import com.example.przyslowioinator2.model.Przyslowie
import com.example.przyslowioinator2.network.RetrofitInstance
import retrofit2.Response

class PrzyslowiaRepository {

    suspend fun getRandomPrzyslowie(): Response<Przyslowie> {
        return RetrofitInstance.api.getRandomPrzyslowie()
    }

    suspend fun getPrzyslowia(): Response<List<Przyslowie>> {
        return RetrofitInstance.api.getPrzyslowia()
    }

    suspend fun getPrzyslowie(id : String) : Response<Przyslowie> {
        return RetrofitInstance.api.getPrzyslowie(id)
    }
}