package com.example.tasksmanager.services

import com.example.tasksmanager.models.LoginModel
import com.example.tasksmanager.models.UserResponse
import com.example.tasksmanager.сonstants.Constants
import okhttp3.MultipartBody
import okhttp3.Request
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*
 interface ApiService {
    @POST("/login")
    fun login(@Body request: LoginModel): Call<UserResponse>


}