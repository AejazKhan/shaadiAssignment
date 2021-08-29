package com.example.demo.api.network

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.demo.api.model.ResultData
import org.jetbrains.annotations.NotNull

/**
 * Created by aejaz.khan.
 * This is used to provider interface for storing and fetching data from DB.
 */
interface ApiDataStoringHelper<ApiResponseType,DbResponseType> {

    @WorkerThread
    fun storeApiResult(@NotNull item: ApiResponseType)

    @NotNull
    @MainThread
    fun loadFromDb() :LiveData<DbResponseType>

    fun getResultLiveData() : MediatorLiveData<ResultData<DbResponseType>>
}