package com.example.tasksmanager.activitys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.tasksmanager.R
import com.example.tasksmanager.models.ResponseOK
import com.example.tasksmanager.models.TaskCreateRequest
import com.example.tasksmanager.services.RetrofitClient
import kotlinx.android.synthetic.main.activity_create_task.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.text.format.DateFormat
import android.widget.*
import com.example.tasksmanager.models.EmployeeResponse
import com.example.tasksmanager.services.ApiService
import okhttp3.internal.notifyAll
import java.util.*

class CreateTaskActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener,
    TimePickerDialog.OnTimeSetListener {
    lateinit var mService: ApiService
    private lateinit var editText: EditText
    private lateinit var retrofitClient: RetrofitClient
    lateinit var userList: List<EmployeeResponse>



    var day = 0
    var month: Int = 0
    var year: Int = 0
    var hour: Int = 0
    var minute: Int = 0
    var myDay = 0
    var myMonth: Int = 0
    var myYear: Int = 0
    var myHour: Int = 0
    var myMinute: Int = 0

    var datatime:String?=""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_task)
        setupActionBar()

        retrofitClient = RetrofitClient

        val role = intent.extras?.getString("role")
        et_creatorTasks.setText(role)

        val name= intent.extras?.getString("name")
        et_executorTask.setText(name)


        editText=findViewById(R.id.et_deadlineTask_t)


        date_time_picker.setEndIconOnClickListener() {
            val calendar: Calendar = Calendar.getInstance()
            day = calendar.get(Calendar.DAY_OF_MONTH)
            month = calendar.get(Calendar.MONTH)
            year = calendar.get(Calendar.YEAR)
            val datePickerDialog =
                DatePickerDialog(this@CreateTaskActivity, this@CreateTaskActivity, year, month,day)
            datePickerDialog.show()
        }

        btn_save_task.setOnClickListener {

            val name = et_nameTask.text.toString()
            if (name.isEmpty())
            {
                Toast.makeText(this, "Введите Название", Toast.LENGTH_SHORT).show()
            }
            val description = et_descriptionTask.text.toString()
            if (description.isEmpty())
            {
                Toast.makeText(this, "Введите Описание", Toast.LENGTH_SHORT).show()
            }
            val creator = et_creatorTasks.text.toString()
            if (creator.isEmpty())
            {
                Toast.makeText(this, "Введите создателя", Toast.LENGTH_SHORT).show()
            }
            val executor = et_executorTask.text.toString()
            if (executor.isEmpty())
            {
                Toast.makeText(this, "Выберите исполнителя", Toast.LENGTH_SHORT).show()
            }
            val comment = et_commentTask.text.toString()
            if (comment.isEmpty())
            {
                Toast.makeText(this, "Введите описание", Toast.LENGTH_SHORT).show()
            }

            val deadline= datatime
            if (deadline.isNullOrBlank())
            {
                Toast.makeText(this, "Введите крайний срок", Toast.LENGTH_SHORT).show()
            }

            retrofitClient.postTask().taskCreate(TaskCreateRequest(name = name,description=description,creator=creator,executor = executor,comment = comment,deadline = deadline))
                .enqueue(object : Callback<ResponseOK> {
                    override fun onResponse(
                        call: Call<ResponseOK>,
                        response: Response<ResponseOK>,
                    ) {
                        val stringResponse = response.body()
                        Log.d("TAG", "${stringResponse?.ok.toString()}")
                    }

                    override fun onFailure(call: Call<ResponseOK>, t: Throwable) {
                        Log.e("TAG", "error ${t.localizedMessage}")
                        Toast.makeText(this@CreateTaskActivity, "Задача создана!", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@CreateTaskActivity, MainActivity::class.java))
                    }
                })


        }



        selected_user.setEndIconOnClickListener(){
            //Toast.makeText(this,"Вы нажали кнопку",Toast.LENGTH_SHORT).show()
            startActivity(Intent(this@CreateTaskActivity, SelectedUserActivity::class.java))

        }



    }

    private fun setupActionBar() {
            toolbar_aboutTask_activity.setNavigationIcon(R.drawable.ic_back)
        toolbar_aboutTask_activity.setNavigationOnClickListener { onBackPressed() }
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        myDay = day
        myYear = year
        myMonth = month
        val calendar: Calendar = Calendar.getInstance()
        hour = calendar.get(Calendar.HOUR)
        minute = calendar.get(Calendar.MINUTE)
        val timePickerDialog = TimePickerDialog(this@CreateTaskActivity, this@CreateTaskActivity, hour, minute,
            DateFormat.is24HourFormat(this))
        timePickerDialog.show()
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        myHour = hourOfDay
        myMinute = minute
        //2021-08-28T18:25:43

        datatime="$myYear-$myMonth-$myDay"+"T"+"$myHour:$myMinute"
         editText.setText("$myDay.$myMonth.$myYear г. , $myHour:$myMinute" )

        Log.d("ABOBA","$datatime")
    }




}











