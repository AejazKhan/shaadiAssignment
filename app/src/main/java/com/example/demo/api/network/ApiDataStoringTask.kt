package com.example.demo.api.network

import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.example.demo.api.model.ResultData

/**
 * Created by aejaz.khan.
 * This AsyncTask is stores data from API into the DB in the background.
 * AsyncTask is deprecated and should be replaced.
 */
class ApiDataStoringTask<ApiResponseType,DbResponseType>(val apiResponseType : ApiResponseType,val apiDataStoringHelper : ApiDataStoringHelper<ApiResponseType, DbResponseType>) : AsyncTask<Void, Void, Void>() {

    override fun doInBackground(vararg p0: Void?) : Void? {
        apiDataStoringHelper.storeApiResult(apiResponseType)
        return null
    }

    override fun onPostExecute(result: Void?) {
        super.onPostExecute(result)
        var liveData : LiveData<DbResponseType> = apiDataStoringHelper.loadFromDb()
        apiDataStoringHelper.getResultLiveData().addSource(liveData) {
            apiDataStoringHelper.getResultLiveData().value = ResultData(it)
        }
    }
}