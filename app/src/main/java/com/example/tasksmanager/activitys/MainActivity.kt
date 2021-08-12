package com.example.tasksmanager.activitys


import android.app.AlertDialog
import android.content.Intent

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tasksmanager.R
import com.example.tasksmanager.adapters.MyTasksAdapter
import com.example.tasksmanager.models.TasksResponseItem
import com.example.tasksmanager.models.UserResponse
import com.example.tasksmanager.services.ApiService
import com.example.tasksmanager.services.RetrofitClient
import com.google.android.material.navigation.NavigationView
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_sign_in.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.item_tasks.*
import kotlinx.android.synthetic.main.item_tasks.view.*
import kotlinx.android.synthetic.main.nav_header_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,
    MyTasksAdapter.onItemClickListener {
    lateinit var mService: ApiService
    lateinit var adapter: MyTasksAdapter
    lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var dialog: AlertDialog
    private lateinit var recyclerView: RecyclerView
    private lateinit var userResponse: UserResponse
    private lateinit var userList: MutableList<UserResponse>



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        recyclerView = findViewById(R.id.recyclerView)
        mService = RetrofitClient.getTasks()
        recyclerView.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager
        dialog = SpotsDialog.Builder().setCancelable(true).setContext(this).build()

        fab_create_board.setOnClickListener {
            startActivity(Intent(this@MainActivity, CreateTaskActivity::class.java))
        }


        setupActionBar()
        getTasks()


    }







    private fun getTasks() {

        RetrofitClient.getTasks().tasks().enqueue(object : Callback<List<TasksResponseItem>?> {
            override fun onResponse(
                call: Call<List<TasksResponseItem>?>,
                response: Response<List<TasksResponseItem>?>,
            ) {
                val responseBody = response.body()!!

                adapter = MyTasksAdapter(baseContext, responseBody, this@MainActivity)
                adapter.notifyDataSetChanged()
                recyclerView.adapter = adapter


            }

            override fun onFailure(call: Call<List<TasksResponseItem>?>, t: Throwable) {
                Log.d("MainActivity", "onFailure" + t.message)
            }


        })
    }


    private fun setupActionBar() {

        setSupportActionBar(toolbar_main_activity)
        toolbar_main_activity.setNavigationIcon(R.drawable.ic_action_navigation_menu)

        toolbar_main_activity.setNavigationOnClickListener {
            toggleDrawer()
        }
    }


    private fun toggleDrawer() {

        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            drawer_layout.openDrawer(GravityCompat.START)
        }
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {


        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        TODO("Not yet implemented")
    }

    override fun onItemClick(position: Int, taskList: List<TasksResponseItem>) {
        val intent = Intent(this@MainActivity, AboutTasksActivity::class.java)
        intent.putExtra("id",taskList[position].id)
        startActivity(intent)
    }


}