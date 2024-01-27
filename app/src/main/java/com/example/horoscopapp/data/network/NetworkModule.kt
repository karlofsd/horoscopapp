package com.example.horoscopapp.data.network

import com.example.horoscopapp.data.network.interceptors.AuthInterceptor
import com.example.horoscopapp.data.repository.HoroscopeRepositoryImpl
import com.example.horoscopapp.domain.repository.HoroscopeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://newastro.vercel.app/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient{
        val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient
            .Builder()
            .addInterceptor(interceptor)
            .addInterceptor(authInterceptor)
            .build()
    }

    @Provides
    fun provideHoroscopeService(retrofit: Retrofit): HoroscopeService {
        return retrofit.create(HoroscopeService::class.java)
    }

    @Provides
    fun provideHoroscopeRepository(service: HoroscopeService): HoroscopeRepository {
        return HoroscopeRepositoryImpl(service)
    }
}