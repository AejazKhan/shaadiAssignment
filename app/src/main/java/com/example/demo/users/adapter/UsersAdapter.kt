package com.example.demo.users.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.demo.R
import com.example.demo.users.db.entity.UserWithLocation
import com.example.demo.users.interfaces.MatchClickListener
import com.example.demo.users.viewholder.UserViewHolder
/**
 * Created by aejaz.khan.
 * This is adapter class for user list.
 */
class UsersAdapter(var userList : List<UserWithLocation>,private val matchClickListener: MatchClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return UserViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.matches_row,parent,false),matchClickListener)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is UserViewHolder){
            holder.bind(userList[position])
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun setUserListData(userList : List<UserWithLocation>){
        this.userList = userList
        notifyDataSetChanged()
    }

}