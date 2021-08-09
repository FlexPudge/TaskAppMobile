package com.example.tasksmanager.activitys


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tasksmanager.R
import com.example.tasksmanager.models.UserResponse
import com.example.tasksmanager.services.RetrofitClient
import com.example.tasksmanager.services.SessionManager
import kotlinx.android.synthetic.main.activity_start.*
import retrofit2.Response

class StartActivity : AppCompatActivity() {
    private lateinit var sessionManager: SessionManager
    private lateinit var retrofitClient: RetrofitClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        retrofitClient= RetrofitClient
        sessionManager = SessionManager(this)


        btn_sign_in_start.setOnClickListener {
            startActivity(Intent(this@StartActivity, SignInActivity::class.java))
        }


    }
}