package com.example.demo.users.db.entity

import androidx.room.Embedded
import androidx.room.Relation
/**
 * Created by aejaz.khan.
 */
data class UserWithMatchStatus(
    @Embedded
    var user : User,
    @Relation(
        parentColumn = "uuid",
        entityColumn = "uuid",
        entity = MatchStatus::class
    )
    var matchStatus : MatchStatus?
)
