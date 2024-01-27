package com.example.horoscopapp.data.providers

import com.example.horoscopapp.domain.model.HoroscopeInfo
import com.example.horoscopapp.domain.model.HoroscopeInfo.*
import javax.inject.Inject

class HoroscopeProvider @Inject constructor(){
    fun getHoroscope(): List<HoroscopeInfo> {
        return listOf(
            Aries,
            Taurus,
            Cancer,
            Pisces,
            Gemini,
            Libra,
            Scorpio,
            Leo,
            Capricorn,
            Sagittarius,
            Virgo
        )
    }
}