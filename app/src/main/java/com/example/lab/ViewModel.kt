package com.example.lab

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModel(application: Application): AndroidViewModel(application) {

    private val readAllData: LiveData<List<Reservation>>
    private val repository1: Repository

    init {
        val dao = db.getDatabase(application).Dao()
        repository1 = Repository(dao)
        readAllData = repository1.readAllData
    }

    fun addReservation(reservation: Reservation){
        viewModelScope.launch(Dispatchers.IO) {
            repository1.addReservation(reservation)
        }
    }

}