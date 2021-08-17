package com.example.tasksmanager.activitys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.tasksmanager.R
import com.example.tasksmanager.models.ResponseOK
import com.example.tasksmanager.models.TaskCreateRequest
import com.example.tasksmanager.services.RetrofitClient
import kotlinx.android.synthetic.main.activity_create_task.*
import kotlinx.android.synthetic.main.activity_user_profil.*
import kotlinx.android.synthetic.main.app_bar_main.*
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateTaskActivity : AppCompatActivity() {

    private lateinit var retrofitClient: RetrofitClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_task)
        setupActionBar()
        retrofitClient = RetrofitClient

        val role = intent.extras?.getString("role")

        et_creatorTasks.setText(role)


        btn_save_task.setOnClickListener {

            val name = et_nameTask.text.toString()
          //  if (name.isEmpty())
         //   {
             //   Toast.makeText(this, "Введите Название", Toast.LENGTH_SHORT).show()
         //   }
            val description = et_descriptionTask.text.toString()
         //   if (description.isEmpty())
         //   {
         //      Toast.makeText(this, "Введите описание", Toast.LENGTH_SHORT).show()
         //   }
            val creator = et_creatorTasks.text.toString()
            val executor = et_executorTask.text.toString()
            val comment = et_commentTask.text.toString()
            val deadline=et_deadlineTask_t.text.toString()

            retrofitClient.postTask().taskCreate(TaskCreateRequest(name = name,description=description,creator=creator,executor = executor,comment = comment,deadline = deadline))
                .enqueue(object : Callback<ResponseOK> {
                    override fun onResponse(
                        call: Call<ResponseOK>,
                        response: Response<ResponseOK>,
                    ) {
                        val stringResponse = response.body()
                        Log.d("TAG", "${stringResponse?.ok.toString()}")
                    }

                    override fun onFailure(call: Call<ResponseOK>, t: Throwable) {
                        Log.e("TAG", "error ${t.localizedMessage}")
                    }
                })


        }




            //"name"
           // "coment"
          //  "creator"
          //  "deadline": null,
          //  "description"
         //   "executor"



    }

    private fun setupActionBar() {

        setSupportActionBar(toolbar_aboutTask_activity)

        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_back)
            //  actionBar.title = resources.getString(R.string.profil)
        }

        toolbar_aboutTask_activity.setNavigationOnClickListener { onBackPressed() }
    }
}


