package com.example.demo.db.base

import androidx.room.*
/**
 * Created by aejaz.khan.
 */
@Dao
interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(entityList: List<T>) : List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity : T) :Long

    @Delete
    fun delete(entity : T)

    @Update
    fun update(entity : T) : Int

    @Update
    fun updateAll(entityList : List<T>) : Int
}