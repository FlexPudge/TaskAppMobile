package com.example.tasksmanager.models


data class TasksResponse(

    var comment: String? = null,
    var created: String? = null,
    var creator: String? = null,
    var deadline: String? = null,
    var description: String? = null,
    var executor: String? = null,
    var id: Int? = null,
    var name: String,
    var status: Int? = null,

    )