package com.example.horoscopapp.data.repository

import android.util.Log
import com.example.horoscopapp.data.network.HoroscopeService
import com.example.horoscopapp.data.network.response.PredictionResponse
import com.example.horoscopapp.domain.model.PredictionModel
import com.example.horoscopapp.domain.repository.HoroscopeRepository
import javax.inject.Inject

class HoroscopeRepositoryImpl @Inject constructor(private val service: HoroscopeService) : HoroscopeRepository {
    override suspend fun getPrediction(sign: String): PredictionModel? {
        kotlin.runCatching { service.getHoroscopeDetail(sign) }
            .onSuccess { return it.toDomain() }
            .onFailure { Log.i("Error API", it.message.orEmpty()) }
        return null
    }
}