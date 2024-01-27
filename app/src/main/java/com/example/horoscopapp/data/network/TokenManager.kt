package com.example.horoscopapp.data.network

import javax.inject.Inject

class TokenManager @Inject constructor() {
    fun getToken(): String{
        return "token"
    }
}