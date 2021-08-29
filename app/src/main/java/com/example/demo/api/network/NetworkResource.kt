package com.example.demo.api.network


import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.demo.api.model.ErrorData
import com.example.demo.api.model.ResultData

/**
 * Created by aejaz.khan.
 */
abstract class NetworkResource<ResultType> {

    private var apiResult: MediatorLiveData<ResultData<ResultType>> = MediatorLiveData()

    init {
        fetchFromNetwork()
    }

    @MainThread
    protected abstract fun createCall(): LiveData<ResultData<ResultType>>

    @MainThread
    private fun onFetchFailed(errorCode: Int, errorData: ErrorData) {
        apiResult.value = ResultData(errorCode, errorData)
    }

    @MainThread
    private fun fetchFromNetwork() {
        var apiResponse: LiveData<ResultData<ResultType>> = createCall()
        apiResult.addSource(apiResponse) {
            apiResult.removeSource(apiResponse)
            if(it != null){
                if(it.data != null){
                    apiResult.value =it

                }else{
                    onFetchFailed(it.errorCode,it.errorMsg!!)
                }
            }else{
                var errorData = ErrorData()
                errorData.message = "Network Error"
                onFetchFailed(400,errorData)
            }
        }
    }

    fun getApiResultLiveData() : MediatorLiveData<ResultData<ResultType>>{
        return  apiResult
    }

}