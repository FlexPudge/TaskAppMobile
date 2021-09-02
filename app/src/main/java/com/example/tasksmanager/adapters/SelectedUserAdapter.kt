package com.example.tasksmanager.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tasksmanager.R
import com.example.tasksmanager.models.EmployeeResponse
import kotlinx.android.synthetic.main.item_user.view.*


class SelectedUserAdapter(
    val context: Context,
    val userList: List<EmployeeResponse>,
    private val listener: onItemClickListener,
        ) : RecyclerView.Adapter<SelectedUserAdapter.MyViewHolder>() {

    inner class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val tv_selectedUserName: TextView = itemView.tv_selectedUserName
        val tv_selectedUserRole: TextView = itemView.tv_selectedUserRole


        init {
            itemView.setOnClickListener(this)
        }



        override fun onClick(v: View?) {
            val position: Int = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position,userList )
            }
        }

    }


    interface onItemClickListener {
        fun onItemClick(position: Int, userList: List<EmployeeResponse>)
    }



    override fun onCreateViewHolder( parent: ViewGroup,viewType: Int,): SelectedUserAdapter.MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: SelectedUserAdapter.MyViewHolder, position: Int) {
        val user: EmployeeResponse = userList[position]
        holder.tv_selectedUserName.setText("Имя: ${userList[position].name}")
        holder.tv_selectedUserRole.setText("Роль: ${userList[position].role}")

        holder.itemView.setOnClickListener{
            listener.onItemClick(position,userList)
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }


}