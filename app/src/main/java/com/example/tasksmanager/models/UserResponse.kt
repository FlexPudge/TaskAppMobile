package com.example.tasksmanager.models

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json

data class UserResponse(
    //ответ при успешном входе в систему

    @Json(name = "status")
    var status: String,

    @Json(name ="user_name")
    var user_name: String,

    @Json(name ="user_role")
    var user_role: String,

    @Json(name ="user_login")
    var user_login: String,

    @Json(name ="user_is_active")
    var user_is_active: String


)



