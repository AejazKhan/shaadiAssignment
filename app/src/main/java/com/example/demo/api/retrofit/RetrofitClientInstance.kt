package com.example.demo.api.retrofit
import retrofit2.Retrofit
import com.example.demo.api.retrofit.configuration.LiveDataCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by aejaz.khan.
 * This class provides the retrofit singleton instance.
 */
class RetrofitClientInstance {

    companion object {
        private var instance: Retrofit? = null

        private val baseUrl = "https://randomuser.me"

        fun getInstance() : Retrofit? {
            if(instance == null){

                instance = Retrofit.Builder()
                        .baseUrl(baseUrl)
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(LiveDataCallAdapterFactory.create())
                        .build()
            }
            return instance
        }
    }




}