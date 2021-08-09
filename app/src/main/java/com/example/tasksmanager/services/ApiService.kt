package com.example.tasksmanager.services

import com.example.tasksmanager.models.LoginModel
import com.example.tasksmanager.models.TasksResponse
import com.example.tasksmanager.models.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.*
 interface ApiService {
    @POST("/login")
    fun login(@Body request: LoginModel): Call<UserResponse>


    @GET("/tasks")
    fun tasks(): Call<MutableList<TasksResponse>>



 }