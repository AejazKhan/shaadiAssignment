package com.example.demo.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.demo.users.db.dao.LocationDao
import com.example.demo.users.db.dao.MatchStatusDao
import com.example.demo.users.db.dao.UserDao
import com.example.demo.users.db.entity.Location
import com.example.demo.users.db.entity.MatchStatus
import com.example.demo.users.db.entity.User
/**
 * Created by aejaz.khan.
 */
@Database(
    entities = [User::class, Location::class, MatchStatus::class],
    version = AppDB.DB_VERSION,
    exportSchema = false
)
abstract class AppDB : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun locationDao(): LocationDao
    abstract fun matchStatusDao(): MatchStatusDao

    companion object {
        const val DB_VERSION = 2
        private const val USER_DB = "app_db"
        var instance: AppDB? = null
            private set

        fun initAppDB(context: Context){
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDB::class.java, USER_DB
                ).fallbackToDestructiveMigration()
//                    .allowMainThreadQueries()
                    .build()
            }
        }

        fun getAppDB() : AppDB?{
            return instance
        }
    }
}