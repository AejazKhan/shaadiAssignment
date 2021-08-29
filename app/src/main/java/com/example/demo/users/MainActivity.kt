package com.example.demo.users

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demo.R
import com.example.demo.api.model.ResultData
import com.example.demo.users.adapter.UsersAdapter
import com.example.demo.users.db.entity.User
import com.example.demo.users.db.entity.UserWithLocation
import com.example.demo.users.interfaces.MatchClickListener
import com.example.demo.users.viewmodel.UserViewModel
import com.example.demo.util.Helper
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by aejaz.khan.
 */
class MainActivity : AppCompatActivity(), MatchClickListener {

    private val TAG = MainActivity::class.java.simpleName
    private var userViewModel : UserViewModel? = null
    private var usersAdapter : UsersAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        rvUsers.layoutManager = LinearLayoutManager(this)
        piMain.show()
        userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        getAllUsers()
    }

    private fun getAllUsers() {
        userViewModel?.getUsersList(10)?.observe(this, Observer {
            setUserList(it)
        })

    }

    private fun setUserList(it: ResultData<List<UserWithLocation>>?) {
        if(it == null){
            return
        }
        when (it.status){
            ResultData.Status.LOADED ->{
                showLog("Response = Loaded")
            }
            ResultData.Status.ERROR ->{
                showLog("Response = errorCode =${it.errorCode},msg= ${it.errorMsg}")
            }
            ResultData.Status.LOADING ->{
                showLog("Response = Loading")
            }
            ResultData.Status.SUCCESS ->{
                showLog("Response = Success")
                setUserAdapter(it.data)
                piMain.hide()
            }

        }
    }

    private fun setUserAdapter(userList: List<UserWithLocation>?) {
        if(userList == null){
            return
        }
        if(usersAdapter == null){
            usersAdapter = UsersAdapter(userList,this)
            rvUsers.adapter = usersAdapter
        }else{
            usersAdapter?.setUserListData(userList)
        }
    }

    fun showLog(msg : String){
        Log.d(TAG,msg)
    }

    override fun onDecline(position: Int) {
        Helper.showToast(this,getString(R.string.decline_msg))
        updateMatchStatus(false,usersAdapter?.userList?.get(position)?.userWithMatchStatus?.user)
    }

    override fun onAccept(position: Int) {
        Helper.showToast(this,getString(R.string.accept_msg))
        updateMatchStatus(true,usersAdapter?.userList?.get(position)?.userWithMatchStatus?.user)
    }

    private fun updateMatchStatus(status: Boolean, user: User?) {
        if(user == null){
            return
        }
        userViewModel?.insertMatchStatus(user.uuid,status)
    }

}