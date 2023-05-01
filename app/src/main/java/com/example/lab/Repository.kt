package com.example.lab

import androidx.lifecycle.LiveData

class Repository(private val dao: DAO) {

    val readAllData: LiveData<List<Reservation>> = dao.getReservation()

    suspend fun addReservation(reservation: Reservation){
        dao.addReservation(reservation)
    }
}