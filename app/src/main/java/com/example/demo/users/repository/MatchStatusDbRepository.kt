package com.example.demo.users.repository

import com.example.demo.db.AppDB
import com.example.demo.users.db.entity.MatchStatus
/**
 * Created by aejaz.khan.
 * Repository for string match status into the DB.
 */
object MatchStatusDbRepository {

    fun insert(matchStatus: MatchStatus) {
        AppDB.getAppDB()?.matchStatusDao()?.insert(matchStatus)
    }
}