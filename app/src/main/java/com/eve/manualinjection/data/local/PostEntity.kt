package com.eve.manualinjection.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("posts")
data class PostEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val body: String,
)
