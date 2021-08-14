package com.example.tasksmanager.services

import com.example.tasksmanager.models.LoginModel
import com.example.tasksmanager.models.TaskCreateRequest
import com.example.tasksmanager.models.TasksResponse
import com.example.tasksmanager.models.UserResponse
import retrofit2.Call
import retrofit2.http.*
 interface ApiService {
    @POST("/login")
    fun login(@Body request: LoginModel): Call<UserResponse>


    @POST("/task/create")
    fun taskCreate(@Body  request:TaskCreateRequest)


    @GET("/tasks")
    fun tasks(): Call<List<TasksResponse>>



 }