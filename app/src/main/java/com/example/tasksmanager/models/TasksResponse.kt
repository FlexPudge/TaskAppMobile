package com.example.tasksmanager.models

import com.squareup.moshi.Json
import org.w3c.dom.Comment

data class TasksResponse(

    @Json(name = "comment") var comment: String,
    @Json(name = "created") var created: String,
    @Json(name = "creator") var creator: String,
    @Json(name = "deadline") var deadline: String,
    @Json(name = "description") var description: String,
    @Json(name = "executor") var executor: String,
    @Json(name = "id") var id: Int,
    @Json(name = "name") var name: String,
    @Json(name = "status") var status: Int,

    )