package com.example.tasksmanager.activitys


import android.R.attr.data
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.tasksmanager.R
import com.example.tasksmanager.adapters.MyTasksAdapter
import com.example.tasksmanager.models.TasksResponse
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
    private lateinit var taskList: List<TasksResponse>
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    var alertDialog: AlertDialog? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        nav_view.setNavigationItemSelectedListener(this)

        recyclerView = findViewById(R.id.recyclerView)
        mService = RetrofitClient.getTasks()
        recyclerView.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager
        dialog = SpotsDialog.Builder().setCancelable(true).setContext(this).build()

        taskList= listOf()
        swipeRefreshLayout=findViewById(R.id.swipeRefreshLayout)









        val name = intent.extras?.getString("name")
        val role = intent.extras?.getString("role")

        fab_create_board.setOnClickListener {
            val intent = Intent(this@MainActivity, CreateTaskActivity::class.java)
            intent.putExtra("role", role)
            startActivity(intent)
        }


        val headerView = nav_view.getHeaderView(0)
        val navUsername = headerView.findViewById<TextView>(R.id.tv_username)
        val navUserLastname = headerView.findViewById<TextView>(R.id.tv_userRole)

        navUsername.setText(name)
        navUserLastname.setText(role)

        setupActionBar()
        getTasks()



        swipeRefreshLayout.setOnRefreshListener {
            getTasks()
            Toast.makeText(this,"???????????????? ??????????????????!",Toast.LENGTH_SHORT).show()
            swipeRefreshLayout.isRefreshing=false
        }


    }








    private fun getTasks() {

        RetrofitClient.getTasks().tasks().enqueue(object : Callback<List<TasksResponse>> {
            override fun onResponse(
                call: Call<List<TasksResponse>>,
                response: Response<List<TasksResponse>>,
            ) {
                val responseBody = response.body()!!
                Log.d("TAG"," ${responseBody}")

                adapter = MyTasksAdapter(baseContext, responseBody, this@MainActivity)
                adapter.notifyDataSetChanged()
                recyclerView.adapter = adapter




            }

            override fun onFailure(call: Call<List<TasksResponse>?>, t: Throwable) {
                Log.d("MainActivity", "onFailure" + t.message)
            }


        })
    }


    private fun setupActionBar() {

      //  setSupportActionBar()
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

        alertDialog?.show()
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {


        }
    }



    override fun onItemClick(position: Int, taskList: List<TasksResponse>) {
        val intent = Intent(this@MainActivity, AboutTasksActivity::class.java)
        intent.putExtra("id",taskList[position].id)
        intent.putExtra("comment",taskList[position].comment)
        intent.putExtra("created",taskList[position].created)
        intent.putExtra("creator",taskList[position].creator)
        intent.putExtra("deadline",taskList[position].deadline)
        intent.putExtra("description",taskList[position].description)
        intent.putExtra("executor",taskList[position].executor)
        intent.putExtra("name",taskList[position].name)
        val _stat=findViewById<TextView>(R.id.tv_status)
        intent.putExtra("status",_stat.text.toString())
        startActivity(intent)
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        val login = intent.extras?.getString("login")
        when (menuItem.itemId) {
            R.id.nav_my_profile -> {

                val intent = Intent(this@MainActivity, UserProfilActivity::class.java)
                intent.putExtra("name",tv_username.text.toString())
                intent.putExtra("role",tv_userRole.text.toString())
                intent.putExtra("login", login)
                startActivity(intent)



                Log.d("TAG","open this door ")
            }

            R.id.nav_sign_out -> {
                val alertDialogBuilder = AlertDialog.Builder(this)
                alertDialogBuilder.setTitle("??????????")
                alertDialogBuilder.setMessage("???? ?????????????????????????? ???????????? ???????????")
                alertDialogBuilder.setPositiveButton("????") { _: DialogInterface, _: Int ->
                    finish()
                }
                alertDialogBuilder.setNegativeButton("??????????",
                    { dialogInterface: DialogInterface, i: Int -> })
                    .show()
                alertDialog = alertDialogBuilder.create()

            }

        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }







}