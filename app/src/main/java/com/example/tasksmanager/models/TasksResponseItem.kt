package com.example.tasksmanager.models

data class TasksResponseItem(
    val comment: String,
    val created: String,
    val creator: String,
    val deadline: String,
    val description: String,
    val executor: String,
    val id: Int,
    val name: String,
    val status: Int,
)
