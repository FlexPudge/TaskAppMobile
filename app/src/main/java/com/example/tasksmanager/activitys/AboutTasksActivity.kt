package com.example.tasksmanager.activitys

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.tasksmanager.R
import com.example.tasksmanager.models.TasksResponse
import com.example.tasksmanager.models.TasksResponseItem
import kotlinx.android.synthetic.main.activity_about_tasks.*
import kotlinx.android.synthetic.main.item_tasks.*

class AboutTasksActivity : AppCompatActivity() {

    lateinit var loadUserData: MutableLiveData<TasksResponseItem?>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_tasks)
        setupActionBar()





        loadTaskData()

    }


    fun getLoadUserObservable(): MutableLiveData<TasksResponseItem?> {
        return  loadUserData
    }

    init {

        loadUserData = MutableLiveData()
    }


    private fun loadTaskData(){



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