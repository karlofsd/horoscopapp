package com.example.horoscopapp.domain.model

import com.example.horoscopapp.R

sealed class HoroscopeInfo(val img: Int, val name: Int){
    object Aries: HoroscopeInfo(R.drawable.aries, R.string.aries)
    object Taurus: HoroscopeInfo(R.drawable.tauro, R.string.taurus)
    object Cancer: HoroscopeInfo(R.drawable.cancer, R.string.cancer)
    object Pisces: HoroscopeInfo(R.drawable.piscis, R.string.pisces)
    object Gemini: HoroscopeInfo(R.drawable.geminis, R.string.gemini)
    object Libra: HoroscopeInfo(R.drawable.libra, R.string.libra)
    object Scorpio: HoroscopeInfo(R.drawable.escorpio, R.string.scorpio)
    object Leo: HoroscopeInfo(R.drawable.leo, R.string.leo)
    object Capricorn: HoroscopeInfo(R.drawable.capricornio, R.string.capricorn)
    object Sagittarius: HoroscopeInfo(R.drawable.sagitario, R.string.sagittarius)
    object Aquarius: HoroscopeInfo(R.drawable.aquario, R.string.aquarius)
}
