package com.example.tasksmanager.activitys


import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.tasksmanager.R
import com.example.tasksmanager.models.UserResponse
import com.example.tasksmanager.services.RetrofitClient
import com.example.tasksmanager.services.SessionManager
import com.squareup.moshi.Json
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)






    }
}