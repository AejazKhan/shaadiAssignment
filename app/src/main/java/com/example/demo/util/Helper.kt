package com.example.demo.util

import android.content.Context
import android.widget.Toast

/**
 * Created by aejaz.khan.
 */
class Helper {

    companion object{
        fun showToast(context : Context,msg : String) {
            Toast.makeText(context,msg, Toast.LENGTH_LONG).show()
        }
    }
}