package com.example.showprofileactivity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
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
        val fullname: TextView = findViewById(R.id.fullname) as TextView
        fullname.setText(fullNameValue)
        val nickname: TextView = findViewById(R.id.nickname) as TextView
        nickname.setText(nickNameValue)
        val age: TextView = findViewById(R.id.age1) as TextView
        age.setText(ageValue.toString())
        val gender: TextView = findViewById(R.id.gender) as TextView
        gender.setText(genderValue)
        val sport: TextView = findViewById(R.id.sport) as TextView
        sport.setText(sportValue)
        val players: TextView = findViewById(R.id.players) as TextView
        players.setText(playerValue.toString())
        val location: TextView = findViewById(R.id.location) as TextView
        location.setText(locationValue)
        val skill: TextView = findViewById(R.id.skill) as TextView
        skill.setText(skillValue)
        val equipment: TextView = findViewById(R.id.equipment) as TextView
        equipment.setText(equipmentValue)
        val disabled: TextView = findViewById(R.id.disabled) as TextView
        disabled.setText(disabledValue)

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
                //
                val intent = Intent(this, EditProfileActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


}

