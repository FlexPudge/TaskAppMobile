package com.example.tasksmanager.models

import com.squareup.moshi.Json

data class ChangeTaskStatusRequest(

    @Json(name="id") val id:Int?,
    @Json(name="status") val status:Int?,

)
