package com.example.tasksmanager.services

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory


object RetrofitClient {


    private var retrofit: Retrofit? = null

    private lateinit var apiService: ApiService


    val baseUrl = "http://kassa67.ru:40010"



    // post изменение статуса задачи

    fun postChangeStatusTask():ApiService{

        if (!::apiService.isInitialized) {
            val retrofit = Retrofit.Builder()
                .baseUrl("http://kassa67.ru:40010")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            apiService = retrofit.create(ApiService::class.java)
        }

        return apiService



    }


    //post создание задачи

    fun postTask():ApiService{

        if (!::apiService.isInitialized) {
            val retrofit = Retrofit.Builder()
                .baseUrl("http://kassa67.ru:40010")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            apiService = retrofit.create(ApiService::class.java)
        }

        return apiService



    }



    //get запрос на получение задаччч
    fun getTasks(): ApiService  {

        if (!::apiService.isInitialized) {
            val retrofitBuilder = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .build()

            apiService=retrofitBuilder.create(ApiService::class.java)

        }
        return apiService

    }

    //POST авторизация
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