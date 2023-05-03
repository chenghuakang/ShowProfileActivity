package com.example.showprofileactivity



import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider


class ReservationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation)
        val modifyButton = findViewById<Button>(R.id.modify)
        modifyButton.setOnClickListener {
            //
            modifyReservation()
        }
        val cancleButton = findViewById<Button>(R.id.cancel_button)
        cancleButton.setOnClickListener {
            //
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


    }
    private fun modifyReservation(){
        val fullName = R.string.name.toString()
        val reservation1 =Reservation(fullName,"","xx","","","","")
        val db =  MyDB.getDatabase(application)
        val vm = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory(application)
        )[MyViewModel::class.java]
        vm.addReservation(reservation1)
        val d = db.dao()
        val data = d.getReservation()

    }
}
