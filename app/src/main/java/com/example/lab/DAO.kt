package com.example.lab

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow
@Dao
interface DAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addReservation(reservation: Reservation)

    @Update
    suspend fun upsertReservation(reservation: Reservation)

    @Delete
    suspend fun deleteReservation(reservation: Reservation)

    @Query("SELECT * FROM reservation ORDER BY id ASC")
    fun getReservation(): LiveData<List<Reservation>>


}