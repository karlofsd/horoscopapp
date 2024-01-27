package com.example.horoscopapp.data.network.interceptors

import com.example.horoscopapp.data.network.TokenManager
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(private val tokenManager: TokenManager): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder().header("Authorization", tokenManager.getToken()).build()
        return chain.proceed(request)
    }
}