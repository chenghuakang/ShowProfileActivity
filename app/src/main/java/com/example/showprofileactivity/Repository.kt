package com.example.showprofileactivity

import androidx.lifecycle.LiveData
import com.example.showprofileactivity.DAO
import com.example.showprofileactivity.Reservation

class Repository(private val dao: DAO) {

    val readAllData: LiveData<List<Reservation>> = dao.getReservation()

    suspend fun addReservation(reservation: Reservation){
        dao.addReservation(reservation)
    }
}