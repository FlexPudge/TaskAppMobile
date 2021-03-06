package com.example.tasksmanager.services

import com.example.tasksmanager.models.*
import okhttp3.Response
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*
 interface ApiService {
    @POST("/login")
    fun login(@Body request: LoginModel): Call<UserResponse>


    @POST("/task/create")
    fun taskCreate(@Body  request:TaskCreateRequest): Call<ResponseOK>


    @POST("/task/status")
    fun taskChangeStatus(@Body  request:ChangeTaskStatusRequest): Call<ResponseOK>


    @GET("/tasks")
    fun tasks(): Call<List<TasksResponse>>

     @GET("/get_workers")
     fun employee(): Call<List<EmployeeResponse>>



 }