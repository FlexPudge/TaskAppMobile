package com.example.tasksmanager.models

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json

data class UserResponse(
    var status: String,
    var user_name: String,
    var user_role: String,
    var user_login: String,
    var user_is_active: String
)



