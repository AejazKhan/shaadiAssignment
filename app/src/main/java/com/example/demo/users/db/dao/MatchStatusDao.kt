package com.example.demo.users.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.demo.db.base.BaseDao
import com.example.demo.users.db.entity.MatchStatus
/**
 * Created by aejaz.khan.
 */
@Dao
interface MatchStatusDao : BaseDao<MatchStatus>{
    @Query("select * from MatchStatus")
    fun getAllMatch(): LiveData<MatchStatus>

}