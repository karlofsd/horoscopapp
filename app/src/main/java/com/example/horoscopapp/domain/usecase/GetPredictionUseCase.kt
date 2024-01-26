package com.example.horoscopapp.domain.usecase

import com.example.horoscopapp.domain.repository.HoroscopeRepository
import javax.inject.Inject

class GetPredictionUseCase @Inject constructor(private val repository: HoroscopeRepository) {

    suspend operator fun invoke(sign: String) = repository.getPrediction(sign)
}