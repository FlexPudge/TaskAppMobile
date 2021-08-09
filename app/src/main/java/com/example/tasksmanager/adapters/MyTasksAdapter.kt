package com.example.tasksmanager.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.tasksmanager.R
import com.example.tasksmanager.models.TasksResponse
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_tasks.view.*

class MyTasksAdapter
    (private val context: Context, private val tasksList: MutableList<TasksResponse>)
    : RecyclerView.Adapter<MyTasksAdapter.MyViewHolder>(){

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val tv_name: TextView = itemView.tv_name
        val tv_creator: TextView = itemView.tv_creator
        val tv_executor: TextView = itemView.tv_executor
        val tv_status: TextView = itemView.tv_status

        fun bind(listItem: TasksResponse)  {
            //image.setOnClickListener {
            //    Toast.makeText(it.context, "нажал на ${itemView.image_movie}", Toast.LENGTH_SHORT)
             //       .show()
         //   }
           itemView.setOnClickListener {
               Toast.makeText(it.context, "нажал на ${itemView.tv_name.text}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_tasks, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount() = tasksList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val listItem = tasksList[position]
        holder.bind(listItem)

       // Picasso.get().load(tasksList[position].imageurl).into(holder.image)
        holder.tv_name.setText("Название:  ${tasksList[position].name}")
        if (tasksList[position].creator == null)
        {
            holder.tv_creator.setText("Создал: --")
        }else {
            holder.tv_creator.setText("Создал:  ${tasksList[position].creator}")
        }

        holder.tv_executor.setText("Исполнитель:  ${tasksList[position].executor}")
        holder.tv_status.setText("Статус:  ${StatusTasks(holder,position)}")
    }

    fun StatusTasks(holder: MyViewHolder,position: Int) = when(tasksList[position].status){

        1 -> "в процессе"
        2-> "выполнино"
        3-> "отклонено"

        else -> "ожидает"
    }


}