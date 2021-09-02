package com.example.tasksmanager.activitys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tasksmanager.R
import kotlinx.android.synthetic.main.activity_user_profil.*

class UserProfilActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profil)

        val name = intent.extras?.getString("name")
        val role = intent.extras?.getString("role")
        val login = intent.extras?.getString("login")

        et_loginUser.setText(login)
        et_nameUser.setText(name)
        et_roleUser.setText(role)



        setupActionBar()
    }

    private fun setupActionBar() {
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_back)
                //  actionBar.title = resources.getString(R.string.profil)
        }

        toolbar_profile_activity.setNavigationOnClickListener { onBackPressed() }
    }


}