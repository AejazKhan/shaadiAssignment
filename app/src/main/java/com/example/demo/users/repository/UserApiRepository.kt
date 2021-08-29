package com.example.demo.users.repository

import androidx.lifecycle.LiveData
import com.example.demo.api.model.ResultData
import com.example.demo.api.retrofit.RetrofitClientInstance
import com.example.demo.api.service.APIService
import com.example.demo.users.model.RandomUsers
/**
 * Created by aejaz.khan.
 * Repository for random user API call.
 */
object UserApiRepository {

    fun fetchRandomUsers(result:Int) : LiveData<ResultData<RandomUsers>>?{
        val apiService = RetrofitClientInstance.getInstance()?.create(APIService::class.java)
        return apiService?.getUsersList(result)
    }
}