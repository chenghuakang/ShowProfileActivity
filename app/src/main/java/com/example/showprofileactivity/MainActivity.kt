package com.example.showprofileactivity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val preferences = getSharedPreferences("user",MODE_PRIVATE)
        val fullNameValue = preferences.getString("full_name", "default full name")
        val nickNameValue = preferences.getString("nickname", "default nick name")
        val ageValue = preferences.getInt("age", 0)
        val genderValue = preferences.getString("gender", "default gender")
        val sportValue = preferences.getString("sport", "default sport")
        val playerValue = preferences.getInt("num_players", 0)
        val locationValue = preferences.getString("location", "default location")
        val skillValue = preferences.getString("skill_lvl", "default skill")
        val equipmentValue = preferences.getString("equipment", "default equipment")
        val disabledValue = preferences.getString("info_disabled", "default info_disabled")
        val portraitUri = preferences.getString("portrait_uri", null)
        val fullname: TextView = findViewById(R.id.fullname)
        fullname.text = fullNameValue
        val nickname: TextView = findViewById(R.id.nickname)
        nickname.text = nickNameValue
        val age: TextView = findViewById<TextView>(R.id.age1)
        age.text = ageValue.toString()
        val gender: TextView = findViewById<TextView>(R.id.gender)
        gender.text = genderValue
        val sport: TextView = findViewById<TextView>(R.id.sport)
        sport.text = sportValue
        val players: TextView = findViewById<TextView>(R.id.players)
        players.text = playerValue.toString()
        val location: TextView = findViewById<TextView>(R.id.location)
        location.text = locationValue
        val skill: TextView = findViewById<TextView>(R.id.skill)
        skill.text = skillValue
        val equipment: TextView = findViewById<TextView>(R.id.equipment)
        equipment.text = equipmentValue
        val disabled: TextView = findViewById<TextView>(R.id.disabled)
        disabled.text = disabledValue
        val portraitView = findViewById<ImageView>(R.id.imageView)
        if (portraitUri != null) {
            portraitView.setImageURI(Uri.parse(portraitUri))
        }

        val pgButton = findViewById<Button>(R.id.pgButton)
        pgButton.setOnClickListener {
            val intent = Intent(this, PlaygroundActivity::class.java)
            startActivity(intent)
        }

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.user_edit_menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.pencil -> {

                true
            }
            R.id.pencil2 -> {

                true
            }
            R.id.userEdit -> {
                //S
                val intent = Intent(this, EditProfileActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


}

