package com.example.demo.users.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demo.api.model.ResultData
import com.example.demo.users.db.entity.UserWithLocation
import com.example.demo.users.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by aejaz.khan.
 * ViewModel for user.
 */
class UserViewModel : ViewModel() {

    fun getUsersList(result: Int): LiveData<ResultData<List<UserWithLocation>>> {
        return UserRepository.getUsersList(result)
    }

    fun insertMatchStatus(uuid: String, status: Boolean) {
        viewModelScope.launch(Dispatchers.IO){
            UserRepository.insertMatchStatus(uuid,status)
        }
    }
}