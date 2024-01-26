package com.example.horoscopapp.ui.detail

import com.example.horoscopapp.domain.model.HoroscopeType

sealed class DetailState {
    data object Loading : DetailState()
    data class Error(val error: String) : DetailState()
    data class Success(val data: String, val type: HoroscopeType, val sign: String) : DetailState()
}
