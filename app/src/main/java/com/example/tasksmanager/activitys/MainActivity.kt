package com.example.tasksmanager.activitys


import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tasksmanager.R
import com.example.tasksmanager.adapters.MyTasksAdapter
import com.example.tasksmanager.models.TasksResponse
import com.example.tasksmanager.services.ApiService
import com.example.tasksmanager.services.Common
import com.example.tasksmanager.services.RetrofitClient
import com.google.android.material.navigation.NavigationView
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener  {
    lateinit var mService: ApiService
    lateinit var layoutManager: LinearLayoutManager
    lateinit var adapter: MyTasksAdapter
    lateinit var dialog: AlertDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        mService = Common.retrofitService
        recyclerView.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        dialog = SpotsDialog.Builder().setCancelable(true).setContext(this).build()

        getAllTasksList()
        setupActionBar()






    }

    private fun setupActionBar() {

        setSupportActionBar(toolbar_main_activity)
        toolbar_main_activity.setNavigationIcon(R.drawable.ic_action_navigation_menu)

        toolbar_main_activity.setNavigationOnClickListener {
            toggleDrawer()
        }
    }


    private fun getAllTasksList() {
        dialog.show()
        mService.tasks().enqueue(object : Callback<MutableList<TasksResponse>> {
            override fun onFailure(call: Call<MutableList<TasksResponse>>, t: Throwable) {

            }

            override fun onResponse(call: Call<MutableList<TasksResponse>>, response: Response<MutableList<TasksResponse>>) {
                adapter = MyTasksAdapter(baseContext, response.body() as MutableList<TasksResponse>)
                adapter.notifyDataSetChanged()
                recyclerView.adapter = adapter

                dialog.dismiss()
            }
        })
    }

    private fun toggleDrawer() {

        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            drawer_layout.openDrawer(GravityCompat.START)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        TODO("Not yet implemented")
    }


}