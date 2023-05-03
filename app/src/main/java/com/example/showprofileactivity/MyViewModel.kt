package com.example.showprofileactivity

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

public class MyViewModel(application: Application): ViewModel() {

    private val readAllData: LiveData<List<Reservation>>
    private val repository1: Repository


    init {
        val dao = MyDB.getDatabase(application).dao()
        repository1 = Repository(dao)
        readAllData = repository1.readAllData
    }

    fun addReservation(reservation: Reservation){
        viewModelScope.launch(Dispatchers.IO) {
            repository1.addReservation(reservation)
        }
    }

}