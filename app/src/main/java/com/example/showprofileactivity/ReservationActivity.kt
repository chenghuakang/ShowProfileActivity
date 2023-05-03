package com.example.showprofileactivity



import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class ReservationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation)
        val modifyButton = findViewById<Button>(R.id.modify)
        modifyButton.setOnClickListener {
            //
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        val cancleButton = findViewById<Button>(R.id.cancel_button)
        cancleButton.setOnClickListener {
            //
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
}
