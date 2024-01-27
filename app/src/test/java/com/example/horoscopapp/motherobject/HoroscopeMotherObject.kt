package com.example.horoscopapp.motherobject

import com.example.horoscopapp.data.network.response.PredictionResponse
import com.example.horoscopapp.domain.model.HoroscopeInfo

object HoroscopeMotherObject {

    val anyResponse = PredictionResponse("date", "prediction", "taurus", "icon")

    val horoscopeInfoList = listOf(
        HoroscopeInfo.Aries,
        HoroscopeInfo.Taurus,
        HoroscopeInfo.Gemini,
        HoroscopeInfo.Cancer,
        HoroscopeInfo.Leo,
        HoroscopeInfo.Virgo,
        HoroscopeInfo.Libra,
        HoroscopeInfo.Scorpio,
        HoroscopeInfo.Sagittarius,
        HoroscopeInfo.Capricorn,
        HoroscopeInfo.Aquarius,
        HoroscopeInfo.Pisces
    )
}