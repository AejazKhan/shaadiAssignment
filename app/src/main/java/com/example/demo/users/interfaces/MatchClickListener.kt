package com.example.demo.users.interfaces

/**
 * Created by aejaz.khan.
 * This interface is for transmitting accept/decline click event from view holder to activity.
 */
interface MatchClickListener {
    fun onDecline(position : Int)
    fun onAccept(position : Int)
}