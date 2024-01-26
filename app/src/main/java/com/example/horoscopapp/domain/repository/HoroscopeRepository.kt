package com.example.horoscopapp.domain.repository

import com.example.horoscopapp.data.network.response.PredictionResponse
import com.example.horoscopapp.domain.model.PredictionModel

interface HoroscopeRepository {
    suspend fun getPrediction(sign: String) : PredictionModel?
}