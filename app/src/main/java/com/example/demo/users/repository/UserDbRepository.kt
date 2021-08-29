package com.example.demo.users.repository

import androidx.lifecycle.LiveData
import com.example.demo.db.AppDB
import com.example.demo.users.db.entity.User
import com.example.demo.users.db.entity.UserWithLocation

/**
 * Created by aejaz.khan.
 * Repository for storing user data into the DB.
 */
object UserDbRepository {
    fun getAllUsers(): LiveData<List<UserWithLocation>> {
        return AppDB.getAppDB()?.userDao()?.getAllUsers()!!
    }

    fun insertAll(userList: List<User>) {
        AppDB.getAppDB()?.userDao()?.insertAll(userList)
    }
}