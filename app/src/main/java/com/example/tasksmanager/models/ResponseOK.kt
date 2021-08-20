package com.example.tasksmanager.models

import com.squareup.moshi.Json

data class ResponseOK(
    @Json(name = "ok")
    val ok: String,

)
