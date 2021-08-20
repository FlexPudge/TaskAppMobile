package com.example.tasksmanager.activitys


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import com.example.tasksmanager.R
import com.example.tasksmanager.models.ChangeTaskStatusRequest
import com.example.tasksmanager.models.ResponseOK
import com.example.tasksmanager.models.TaskCreateRequest
import com.example.tasksmanager.services.RetrofitClient
import kotlinx.android.synthetic.main.activity_about_tasks.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AboutTasksActivity() : AppCompatActivity() {

    private lateinit var retrofitClient: RetrofitClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_tasks)
        setupActionBar()
        loadTaskData()

        retrofitClient = RetrofitClient



        btn_save_aboutTask.setOnClickListener() {
            val id_task = intent.extras?.getInt("id")
            val status_task = et_statusTask.text.toString()

            val _status = when (status_task) {

                "в процессе" -> 1
                "выполнено"  -> 2
                "отклонено"  -> 3

                else -> 4
            }

            retrofitClient.postChangeStatusTask().taskChangeStatus(ChangeTaskStatusRequest(id =id_task,status = _status))
            .enqueue(object : Callback<ResponseOK> {
                    override fun onResponse(
                        call: Call<ResponseOK>,
                        response: Response<ResponseOK>,
                    ) {
                        val stringResponse = response.body()
                        Log.d("TAG", "${stringResponse}")
                        Toast.makeText(this@AboutTasksActivity, "Статус обновлен!", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@AboutTasksActivity, MainActivity::class.java))
                    }

                    override fun onFailure(call: Call<ResponseOK>, t: Throwable) {
                        Log.e("TAG", "чики бом бом ${t.localizedMessage}")
                        Toast.makeText(this@AboutTasksActivity, "Статус обновлен!", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@AboutTasksActivity, MainActivity::class.java))
                    }
                })


        }


    }


    private fun loadTaskData() {

        //val _id = intent.extras?.getString("id")
        val _comment = intent.extras?.getString("comment")
        val _created = intent.extras?.getString("created")
        val _creator = intent.extras?.getString("creator")
        val _deadline = intent.extras?.getString("deadline")
        val _description = intent.extras?.getString("description")
        val _executor = intent.extras?.getString("executor")
        val _name = intent.extras?.getString("name")
        val _status_task = intent.extras?.getString("status")




        et_nameTask.setText(_name)
        et_commentTask.setText(_comment)
        et_createdTask.setText(_created)
        et_creatorTasks.setText(_creator)
        et_deadlineTask.setText(_deadline)
        et_descriptionTask.setText(_description)
        et_executorTask.setText(_executor)

        et_statusTask.setText(_status_task)


        val status = resources.getStringArray(R.array.Status)
        val arrayAdapter = ArrayAdapter(this, R.layout.status_item, status)
        val autocompleteTV = findViewById<AutoCompleteTextView>(R.id.et_statusTask)
        autocompleteTV.setAdapter(arrayAdapter)


    }


    private fun setupActionBar() {
        toolbar_aboutTask_activity.setNavigationIcon(R.drawable.ic_back)
        toolbar_aboutTask_activity.setNavigationOnClickListener { onBackPressed() }
    }
}


