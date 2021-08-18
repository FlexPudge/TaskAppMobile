package com.example.tasksmanager.activitys

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Base64
import android.util.Log
import android.widget.Toast
import com.example.tasksmanager.R
import com.example.tasksmanager.models.LoginModel
import com.example.tasksmanager.models.UserResponse
import com.example.tasksmanager.services.ApiService
import com.example.tasksmanager.services.RetrofitClient
import com.example.tasksmanager.services.SessionManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_sign_in.*
import okhttp3.internal.userAgent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.crypto.Cipher
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.PBEKeySpec
import javax.crypto.spec.SecretKeySpec

class SignInActivity : AppCompatActivity() {
    private lateinit var sessionManager: SessionManager
    private lateinit var retrofitClient: RetrofitClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)


        retrofitClient = RetrofitClient
        sessionManager = SessionManager(this)

        Log.d("TAG", "Проверка ${sessionManager.saveAuthToken(token = String())}")





        btn_sign_in.setOnClickListener {

            val user = et_user.text.toString()

           if (et_user.text.toString().isEmpty()) {
                Toast.makeText(this, "Введите логин", Toast.LENGTH_SHORT).show()
        }

            if (et_password.text.toString().isEmpty()) {
                Toast.makeText(this, "Введите пароль", Toast.LENGTH_SHORT).show()
            }

            val password = et_password.text.toString()



            retrofitClient.getApiService().login(LoginModel(user = user, pass = password))
                .enqueue(object : Callback<UserResponse> {
                    override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                        Log.d("TAG", "error ${t.localizedMessage}")
                    }

                    override fun onResponse(
                        call: Call<UserResponse>,
                        response: Response<UserResponse>
                    ) {
                        val loginResponse = response.body()
                        Log.i("TAG", "${loginResponse.toString()}")

                        if (loginResponse?.user_name != null) {
                            //startActivity(Intent(this@SignInActivity, MainActivity::class.java))
                            val intent = Intent(this@SignInActivity, MainActivity::class.java)
                            intent.putExtra("name", loginResponse.user_name)
                            intent.putExtra("role", loginResponse.user_role)
                            intent.putExtra("login", loginResponse.user_login)
                            startActivity(intent)

                            sessionManager.saveAuthToken(loginResponse.user_is_active)
                        } else {
                            Toast.makeText(applicationContext,"Неправильно введены логин или пароль!", Toast.LENGTH_SHORT).show()
                            Log.d("TAG", "error")
                        }
                    }
                })


        }


    }
}












