package com.example.tasksmanager.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tasksmanager.R
import com.example.tasksmanager.models.TasksResponse
import com.example.tasksmanager.models.UserResponse

class TasksItemAdapter (
    private val list: ArrayList<TasksResponse>,
    private val listener: onItemClickListener
): RecyclerView.Adapter<TasksItemAdapter.MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasksItemAdapter.MyViewHolder {

        val itemView= LayoutInflater.from(parent.context).inflate(R.layout.item_tasks,parent,false)

        return MyViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: TasksItemAdapter.MyViewHolder, position: Int) {

        val tasksResponse:TasksResponse = list[position]

       // holder.name.text=TasksResponse.name.toString()


    }

    override fun getItemCount(): Int {
        return list.size
    }



    inner  class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener{

        val name : TextView = itemView.findViewById(R.id.tv_name)

     //   val createdBy: TextView = itemView.findViewById(R.id.tv_created_by)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position:Int = adapterPosition
            if(position != RecyclerView.NO_POSITION) {
          //      listener.onItemClick(position,tasksResponse= TasksResponse())
            }
        }


    }

    interface onItemClickListener{
        fun onItemClick(position: Int,tasksResponse: TasksResponse)
    }

}