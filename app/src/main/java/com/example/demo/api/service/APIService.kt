package com.example.demo.api.service

import androidx.lifecycle.LiveData
import com.example.demo.api.model.ResultData
import com.example.demo.users.model.RandomUsers
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by aejaz.khan.
 */
interface APIService {
    @GET("/api/")
    fun getUsersList(@Query("results") result : Int): LiveData<ResultData<RandomUsers>>

}