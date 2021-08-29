package com.example.demo.users.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull
/**
 * Created by aejaz.khan.
 * This entity for storing the accept/decline match status.
 * status : null->not accepted/declined, false-> declined, true->accepted.
 */
@Entity(tableName = "MatchStatus")
data class MatchStatus(
    @PrimaryKey(autoGenerate = true)
    @NotNull
    var id: Long = 0,
    val uuid: String,
    val status : Boolean?
)
