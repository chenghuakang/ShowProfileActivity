package com.example.showprofileactivity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reservation")
data class Reservation(
    val name: String,
    val nickName: String,
    val time: String,
    val location:String,
    val sport:String,
    val numPlayer:String,
    val equipment:String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)