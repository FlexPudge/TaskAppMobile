package com.example.tasksmanager.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.tasksmanager.R
import com.example.tasksmanager.models.TasksResponse
import kotlinx.android.synthetic.main.item_tasks.view.*
import kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.DeclaredMemberIndex


class MyTasksAdapter(
    val context: Context,
    val taskList: List<TasksResponse>,
    private val listener: onItemClickListener,


    ) : RecyclerView.Adapter<MyTasksAdapter.MyViewHolder>() {
   inner class MyViewHolder(itemView:View):RecyclerView.ViewHolder(itemView),View.OnClickListener {
       val tv_name: TextView = itemView.tv_name
       val tv_creator: TextView = itemView.tv_creator
       val tv_executor: TextView = itemView.tv_executor
       val tv_status: TextView = itemView.tv_status

       init {
           itemView.setOnClickListener(this)
       }



        override fun onClick(v: View?) {
            val position: Int = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position,taskList )
            }
        }

    }

    interface onItemClickListener {
        fun onItemClick(position: Int, taskList: List<TasksResponse>)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_tasks, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyTasksAdapter.MyViewHolder, position: Int) {
        val task: TasksResponse = taskList[position]
        holder.tv_name.setText("Название: ${taskList[position].name}")

        if (taskList[position].creator==null && taskList[position].creator.isNullOrEmpty() )
        {
            holder.tv_creator.setText("Создатель: неизвестно")
        }else{
            holder.tv_creator.setText("Создатель: ${taskList[position].creator}")
        }
        holder.tv_executor.setText("Исполнитель: ${taskList[position].executor}")
        holder.tv_status.setText(StatusTasks(position))
        holder.itemView.setOnClickListener{
            listener.onItemClick(position,taskList)
        }
    }

    override fun getItemCount(): Int {
        return taskList.size
    }


    fun StatusTasks(position: Int) = when (taskList[position].status) {

        1 -> "в процессе"
        2 -> "выполнено"
        3 -> "отклонено"

        else -> "ожидает"
    }

    fun CreatorStatus(taskList: List<TasksResponse>){


    }



}


