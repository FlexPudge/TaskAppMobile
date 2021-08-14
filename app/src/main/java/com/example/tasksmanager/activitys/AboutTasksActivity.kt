package com.example.tasksmanager.activitys

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.tasksmanager.R
import com.example.tasksmanager.adapters.MyTasksAdapter
import kotlinx.android.synthetic.main.activity_about_tasks.*
import kotlinx.android.synthetic.main.activity_main.*

class AboutTasksActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_tasks)
        setupActionBar()
        loadTaskData()






    }

   private fun loadTaskData(){

       val _id =  intent.extras?.getString("id")
       val _comment =  intent.extras?.getString("comment")
       val _created = intent.extras?.getString("created")
       val _creator =  intent.extras?.getString("creator")
       val _deadline =   intent.extras?.getString("deadline")
       val _description =  intent.extras?.getString("description")
       val _executor =  intent.extras?.getString("executor")
       val _name =   intent.extras?.getString("name")

       val _status = when (intent.extras?.getInt("status")) {

             1 -> "в процессе"
             2 -> "выполнино"
             3 -> "отклонено"

             else -> "ожидает"
         }


       //et_nameTask.setText(_id)
       et_commentTask.setText(_comment)
       et_createdTask.setText(_created)
       et_creatorTasks.setText(_creator)
       et_deadlineTask.setText(_deadline)
       et_descriptionTask.setText(_description)
       et_executorTask.setText(_executor)
       et_nameTask.setText(_name)
       et_statusTask.setText(_status)

    }



    private fun setupActionBar() {

        setSupportActionBar(toolbar_aboutTask_activity)

        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_back)
        }

        toolbar_aboutTask_activity.setNavigationOnClickListener { onBackPressed() }
    }
}