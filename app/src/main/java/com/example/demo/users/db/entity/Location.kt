package com.example.demo.users.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull
/**
 * Created by aejaz.khan.
 * This is the entity for storing location details of user.
 */
@Entity(tableName = "Location")
data class Location(
    @PrimaryKey(autoGenerate = true)
    @NotNull
    var id: Long = 0,
    val uuid: String,
    val streetName: String?,
    val streetNumber: Int?,
    val city: String?,
    val state: String?,
    val country: String?,
    val postCode: String?,
)
