package com.example.horoscopapp.data.providers

import com.example.horoscopapp.domain.model.HoroscopeInfo
import com.example.horoscopapp.domain.model.HoroscopeInfo.Aries
import com.example.horoscopapp.domain.model.HoroscopeInfo.Cancer
import com.example.horoscopapp.domain.model.HoroscopeInfo.Capricorn
import com.example.horoscopapp.domain.model.HoroscopeInfo.Gemini
import com.example.horoscopapp.domain.model.HoroscopeInfo.Leo
import com.example.horoscopapp.domain.model.HoroscopeInfo.Libra
import com.example.horoscopapp.domain.model.HoroscopeInfo.Pisces
import com.example.horoscopapp.domain.model.HoroscopeInfo.Sagittarius
import com.example.horoscopapp.domain.model.HoroscopeInfo.Scorpio
import com.example.horoscopapp.domain.model.HoroscopeInfo.Taurus
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
            Sagittarius
        )
    }
}