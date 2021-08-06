package com.example.tasksmanager.services

import com.squareup.moshi.Moshi
import retrofit2.converter.moshi.MoshiConverterFactory
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit

object ApiClient {


    private val BASE_URL = "http://kassa67.ru:40010"

    private val moshi= Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    private val retrofit:Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    val apiService:ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }

}


