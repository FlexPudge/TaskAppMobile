package com.example.tasksmanager.activitys

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.tasksmanager.R
import com.example.tasksmanager.models.LoginModel
import com.example.tasksmanager.models.TaskCreateRequest
import com.example.tasksmanager.models.TasksResponseItem
import com.example.tasksmanager.models.UserResponse
import com.example.tasksmanager.services.RetrofitClient
import kotlinx.android.synthetic.main.activity_create_task.*
import kotlinx.android.synthetic.main.activity_sign_in.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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


            retrofitClient.postTask().taskCreate(TaskCreateRequest(
                name = name,description = description,
                creator = creator,executor = executor,
                comment = comment,deadline = deadline))
                .enqueue(object : Callback<List<TasksResponseItem>> {
                    override fun onResponse(
                        call: Call<List<TasksResponseItem>>,
                        response: Response<List<TasksResponseItem>>,
                    ) {
                        val taskResponse = response.body()
                        Log.i("TAG","${taskResponse.toString()}")
                    }

                    override fun onFailure(call: Call<List<TasksResponseItem>>, t: Throwable) {
                        Log.d("TAG","error ${t.localizedMessage}")
                    }

                })


        }


    }
}