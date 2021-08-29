package com.example.demo.users.repository

import com.example.demo.db.AppDB
import com.example.demo.users.db.entity.Location
/**
 * Created by aejaz.khan.
 * Repository for storing Location data into the DB.
 */
object LocationDbRepository {
    fun insertAll(locationList: List<Location>) {
        AppDB.getAppDB()?.locationDao()?.insertAll(locationList)
    }
}