package com.example.tasksmanager.activitys

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tasksmanager.R
import com.example.tasksmanager.adapters.MyTasksAdapter
import com.example.tasksmanager.adapters.SelectedUserAdapter
import com.example.tasksmanager.models.EmployeeResponse
import com.example.tasksmanager.models.TasksResponse
import com.example.tasksmanager.services.ApiService
import com.example.tasksmanager.services.RetrofitClient
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.activity_create_task.*
import kotlinx.android.synthetic.main.activity_selected_user.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SelectedUserActivity : AppCompatActivity(),SelectedUserAdapter.onItemClickListener {

    lateinit var mService: ApiService
    lateinit var adapter: SelectedUserAdapter
    lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var dialog: AlertDialog
    private lateinit var recyclerView: RecyclerView
    private lateinit var userList: List<EmployeeResponse>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selected_user)

        recyclerView = findViewById(R.id.recyclerViewSelectedUser)
        mService = RetrofitClient.getTaskEmployeeSelection()
        recyclerView.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager
        dialog = SpotsDialog.Builder().setCancelable(true).setContext(this).build()

        EmployeeSelect()
        setupActionBar()


    }


    private fun EmployeeSelect(){
        RetrofitClient.getTaskEmployeeSelection().employee().enqueue(object : Callback<List<EmployeeResponse>> {
            override fun onResponse(
                call: Call<List<EmployeeResponse>>,
                response: Response<List<EmployeeResponse>>,
            ) {
                val responseBody = response.body()!!
                Log.d("TAG"," ${responseBody}")

                adapter = SelectedUserAdapter(baseContext, responseBody, this@SelectedUserActivity)
                adapter.notifyDataSetChanged()
                recyclerView.adapter = adapter
            }

            override fun onFailure(call: Call<List<EmployeeResponse>>, t: Throwable) {
                Log.d("SelectedUserActivity", "onFailure, ловко ты это придумал. Я сначала даже не понял" + t.message)
            }
        })
    }

    private fun setupActionBar() {
        toolbar_selectedUser_activity.setNavigationIcon(R.drawable.ic_back)
        toolbar_selectedUser_activity.setNavigationOnClickListener { onBackPressed() }
    }

    override fun onItemClick(position: Int, userList: List<EmployeeResponse>) {
        val intent = Intent(this@SelectedUserActivity, CreateTaskActivity::class.java)
        intent.putExtra("name",userList[position].name)
        intent.putExtra("role",userList[position].role)
        val _stat=findViewById<TextView>(R.id.et_executorTask)
        startActivity(intent)
    }
}