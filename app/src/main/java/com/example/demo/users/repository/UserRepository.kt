package com.example.demo.users.repository

import androidx.lifecycle.LiveData
import com.example.demo.api.model.ResultData
import com.example.demo.api.network.NetworkBindingResource
import com.example.demo.users.db.entity.Location
import com.example.demo.users.db.entity.MatchStatus
import com.example.demo.users.db.entity.User
import com.example.demo.users.db.entity.UserWithLocation
import com.example.demo.users.model.RandomUsers
/**
 * Created by aejaz.khan.
 * Repository for user.
 */
object UserRepository {

    fun getUsersList(result : Int): LiveData<ResultData<List<UserWithLocation>>> {

        return object : NetworkBindingResource<RandomUsers, List<UserWithLocation>>(){
            override fun storeApiResult(item: RandomUsers) {
                storeUserData(item)
            }

            override fun loadFromDb(): LiveData<List<UserWithLocation>> {
                return UserDbRepository.getAllUsers()
            }

            override fun createCall(): LiveData<ResultData<RandomUsers>> {
                return UserApiRepository.fetchRandomUsers(result)!!
            }

            override fun shouldRefreshData(data: List<UserWithLocation>?): Boolean {
                return true
            }

            override fun shouldAlwaysFetchData(): Boolean {
                return true
            }

        }.getResultLiveData()
    }

    private fun storeUserData(randomUsers: RandomUsers){
        if(randomUsers.results == null){
            return
        }
        val userList = arrayListOf<User>()
        val locationList = arrayListOf<Location>()
        for (result in randomUsers.results){
            if(result?.login?.uuid != null){
                userList.add(User(
                    result.login.uuid,
                    result.name?.title,
                    result.name?.first,
                    result.name?.last,
                    result.email,
                    result.gender,
                    result.dob?.date,
                    result.dob?.age,
                    result.phone,
                    result.cell,
                    result.picture?.large,
                    result.picture?.medium,
                    result.picture?.thumbnail,
                    result.nat,
                    System.currentTimeMillis()
                ))
                locationList.add(Location(
                    uuid = result.login.uuid,
                    streetName = result.location?.street?.name,
                    streetNumber = result.location?.street?.number,
                    city = result.location?.city,
                    state = result.location?.state,
                    country = result.location?.country,
                    postCode = result.location?.postcode
                ))
            }
            UserDbRepository.insertAll(userList)
            LocationDbRepository.insertAll(locationList)
        }
    }

    fun insertMatchStatus(uuid: String, status: Boolean){
        MatchStatusDbRepository.insert(MatchStatus(uuid = uuid,status = status))
    }
}