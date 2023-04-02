package com.example.showprofileactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout

class EditProfileActivity : AppCompatActivity() {
    lateinit var fn: EditText
    lateinit var nick: EditText
    lateinit var age: EditText
    lateinit var gender: EditText
    lateinit var sport: EditText
    lateinit var players: EditText
    lateinit var location: EditText
    lateinit var skill: EditText
    lateinit var equipment: EditText
    lateinit var disabled: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        fn = findViewById(R.id.fullname)
        nick = findViewById(R.id.nickname)
        age = findViewById(R.id.age)
        gender = findViewById(R.id.gender)
        sport = findViewById(R.id.sport)
        players = findViewById(R.id.players)
        location = findViewById(R.id.location)
        skill = findViewById(R.id.skill)
        equipment = findViewById(R.id.equipment)
        disabled = findViewById(R.id.disabled)

        val b= findViewById<Button>(R.id.button2)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        //saves data when screen is rotated

        super.onSaveInstanceState(outState)
        outState.putString("fullname_text",fn.toString())
        outState.putString("nickname_text",nick.toString())
        outState.putInt("age_int",age.toString().toIntOrNull() ?: 0)
        outState.putString("gender_text",gender.toString())
        outState.putString("sport_text",sport.toString())
        outState.putInt("players_int",players.toString().toIntOrNull() ?: 0)
        outState.putString("location_text",location.toString())
        outState.putString("skill_text",skill.toString())
        outState.putString("equipment_text",equipment.toString())
        outState.putString("disabled_text",disabled.toString())
    }
}
