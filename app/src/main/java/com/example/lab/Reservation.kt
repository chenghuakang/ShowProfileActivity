package com.example.lab

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Reservation(
    val name: String,
    val nickName: String,
    val age: String,
    val location:String,
    val sport:String,
    val numPlayer:String,
    val equipment:String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)