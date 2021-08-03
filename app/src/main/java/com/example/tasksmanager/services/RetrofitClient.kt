package com.example.tasksmanager.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



public class RetrofitClient {

    private lateinit var apiService: ApiService

    fun getApiService(): ApiService {

        if (!::apiService.isInitialized) {
            val retrofit = Retrofit.Builder()
                .baseUrl("http://kassa67.ru:40010")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            apiService = retrofit.create(ApiService::class.java)
        }

        return apiService
    }
}