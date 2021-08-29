package com.example.demo.users.db.entity

import androidx.room.Embedded
import androidx.room.Relation
/**
 * Created by aejaz.khan.
 */
data class UserWithLocation(
    @Embedded
    var userWithMatchStatus : UserWithMatchStatus,
    @Relation(
        parentColumn = "uuid",
        entityColumn = "uuid",
        entity = Location::class
    )
    var location : Location
)
