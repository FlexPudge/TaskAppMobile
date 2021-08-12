package com.example.tasksmanager.models

import com.squareup.moshi.Json

data class TaskCreateRequest(
    @Json(name="name") val name:String,
    @Json(name="description") val description:String,
    @Json(name="creator") val creator:String,
    @Json(name="executor") val executor:String,
    @Json(name="comment") val comment:String,
    @Json(name="deadline") val deadline:String,



)
