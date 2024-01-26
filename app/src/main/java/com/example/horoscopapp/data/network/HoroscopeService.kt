package com.example.horoscopapp.data.network

import com.example.horoscopapp.data.network.response.PredictionResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface HoroscopeService {

    @GET("{sign}")
    suspend fun getHoroscopeDetail(@Path("sign") sign: String) : PredictionResponse
}