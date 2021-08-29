package com.example.demo.application

import android.app.Application
import com.example.demo.db.AppDB
/**
 * Created by aejaz.khan.
 */
class DemoApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initDB()
    }

    private fun initDB() {
        AppDB.initAppDB(this)
    }
}