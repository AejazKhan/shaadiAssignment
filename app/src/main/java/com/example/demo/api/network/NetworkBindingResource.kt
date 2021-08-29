package com.example.demo.api.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.demo.api.model.ErrorData
import com.example.demo.api.model.ResultData

/**
 * Created by aejaz.khan.
 * This abstract class performs the network call and store the data into the DB.
 */
abstract class NetworkBindingResource<ApiResponseType, DbResponseType> : NetworkResource<ApiResponseType>(),
    ApiDataStoringHelper<ApiResponseType, DbResponseType> {

    private var dbResultLiveData: MediatorLiveData<ResultData<DbResponseType>> = MediatorLiveData()

    init {
        dbResultLiveData.value = ResultData(status = ResultData.Status.LOADING)
        if (shouldAlwaysFetchData()) {
            fetchFromNetwork()
        } else {
            val response: LiveData<DbResponseType> = loadFromDb()
            dbResultLiveData.addSource(response) {
                if (shouldRefreshData(it)) {
                    dbResultLiveData.removeSource(response)
                    fetchFromNetwork()
                } else {
                    dbResultLiveData.value = ResultData(it)
                }
            }
        }
    }

    protected abstract fun shouldRefreshData(data: DbResponseType?): Boolean

    protected abstract fun shouldAlwaysFetchData(): Boolean

    private fun onFetchFailed(errorCode: Int, errorData: ErrorData) {
        dbResultLiveData.value = ResultData(errorCode, errorData)
    }

    private fun saveResultAndReInit(response: ResultData<ApiResponseType>, apiDataStoringHelper: ApiDataStoringHelper<ApiResponseType, DbResponseType>) {
        ApiDataStoringTask(response.data!!, apiDataStoringHelper).execute()
    }


    override fun getResultLiveData(): MediatorLiveData<ResultData<DbResponseType>> {
        return dbResultLiveData
    }

    private fun fetchFromNetwork() {
        val apiResponse: LiveData<ResultData<ApiResponseType>> = createCall()
        dbResultLiveData.addSource(apiResponse) {
            dbResultLiveData.removeSource(apiResponse)
            if (it != null) {
                if (it.data != null) {
                    dbResultLiveData.value = ResultData(status = ResultData.Status.LOADED)
                    saveResultAndReInit(it, this)
                } else {
                    onFetchFailed(it.errorCode, it.errorMsg!!)
                }

            } else {
                val errorData = ErrorData()
                errorData.message = "Something went wrong"
                onFetchFailed(400, errorData)
            }
        }
    }
}