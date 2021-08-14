package com.example.tasksmanager.activitys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tasksmanager.R
import com.example.tasksmanager.services.RetrofitClient
import kotlinx.android.synthetic.main.activity_create_task.*

class CreateTaskActivity : AppCompatActivity() {

    private lateinit var retrofitClient: RetrofitClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_task)

        retrofitClient = RetrofitClient


        btn_save_task.setOnClickListener {

            // val user=et_user.text.toString()

            val name = et_nameTask.text.toString()
            val description = et_descriptionTask.text.toString()
            val creator = et_creatorTasks.text.toString()
            val executor = et_executorTask.text.toString()
            val comment = et_commentTask.text.toString()
            val deadline = et_deadlineTask_t.text.toString()





        }


    }
}