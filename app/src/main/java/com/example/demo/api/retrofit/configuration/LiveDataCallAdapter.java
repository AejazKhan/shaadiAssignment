package com.example.demo.api.retrofit.configuration;


import android.util.Log;

import com.example.demo.api.model.ErrorData;
import com.example.demo.api.model.ResultData;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.lang.reflect.Type;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Callback;
import retrofit2.Response;

public class LiveDataCallAdapter<R> implements CallAdapter<R, LiveData<ResultData<R>>> {

    private final Type responseType;

    LiveDataCallAdapter(Type responseType) {
        this.responseType = responseType;
    }

    @Override
    public Type responseType() {
        return responseType;
    }

    @Override
    public LiveData<ResultData<R>> adapt(@NonNull final Call<R> call) {

        return new LiveData<ResultData<R>>() {
            @Override
            protected void onActive() {
                super.onActive();
                call.clone().enqueue(new Callback<R>() {
                    @Override
                    public void onResponse(@NonNull Call<R> call1, @NonNull Response<R> response) {
                        if (call1.isCanceled()) return;
                        Log.d("TAG", "onResponse: "+response.body().toString());
                        if (response.isSuccessful()) {
                            if (response.body() != null) {
                                postValue(new ResultData<>(response.body()));
                            }else{
                                postValue(null);
                            }
                        } else {
                            ErrorData errorResponse = null;
                            if (response.errorBody() != null) {
                                errorResponse = getErrorResponse(response.errorBody());
                            }
                            postValue(new ResultData<R>(response.code(), errorResponse));
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<R> call1, @NonNull Throwable t) {
                        if (call1.isCanceled()) return;
                        t.printStackTrace();
                        Log.d("TAG", "onFailure: ");
                        postValue(null);
                    }
                });
            }
        };
    }

    private ErrorData getErrorResponse(ResponseBody responseBody) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(responseBody.string(), ErrorData.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ErrorData();

    }

}
