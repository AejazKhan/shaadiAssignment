package com.example.demo.users.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.demo.db.base.BaseDao
import com.example.demo.users.db.entity.User
import com.example.demo.users.db.entity.UserWithLocation
/**
 * Created by aejaz.khan.
 */
@Dao
interface UserDao : BaseDao<User>{
    @Transaction
    @Query("select * from User Order By createdAt DESC")
    fun getAllUsers(): LiveData<List<UserWithLocation>>
}