package com.example.tasksmanager.services

object Common {
//get запрос на получение тасков
    private val BASE_URL = "http://kassa67.ru:40010"
    val retrofitService: ApiService
        get() = RetrofitClient.getTasks(BASE_URL).create(ApiService::class.java)
}