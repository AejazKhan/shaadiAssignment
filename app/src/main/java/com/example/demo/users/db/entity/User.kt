package com.example.demo.users.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull
/**
 * Created by aejaz.khan.
 * This is the entity for storing user related data.
 */
@Entity(tableName = "User")
data class User(
    @PrimaryKey
    @NotNull
    val uuid: String,
    val nameTitle: String?,
    val firstName: String?,
    val lastName: String?,
    val email: String?,
    val gender: String?,
    val dob: String?,
    val age: Int?,
    val phone: String?,
    val cell: String?,
    val picLarge: String?,
    val picMedium: String?,
    val picThumbnail: String?,
    val nat: String?,
    val createdAt : Long
)
