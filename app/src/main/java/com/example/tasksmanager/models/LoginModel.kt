package com.example.tasksmanager.models

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json

data class LoginModel(

    @Json(name="user") val user:String,
    @Json(name="pass") val pass:String

)
