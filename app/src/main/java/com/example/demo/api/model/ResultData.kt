package com.example.demo.api.model

/**
 * Created by aejaz.khan.
 * This is the generic result data class used to wrap actual data.
 */
data class ResultData<ResultType>(var data: ResultType? = null, var status: Status = Status.LOADING, var errorMsg : ErrorData? = null, var errorCode: Int = 0) {


    constructor(data: ResultType) : this(data, Status.SUCCESS,null,0) {
        this.data = data
    }

    constructor(errorCode: Int,errorMsg: ErrorData?) : this(null, Status.ERROR,errorMsg,errorCode) {
        this.errorCode = errorCode
        this.errorMsg = errorMsg
    }

    enum class Status{
        SUCCESS,ERROR,LOADING,LOADED
    }
}