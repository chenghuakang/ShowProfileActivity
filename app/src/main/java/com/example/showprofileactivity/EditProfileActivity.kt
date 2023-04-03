package com.example.showprofileactivity

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Configuration
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity


class EditProfileActivity : AppCompatActivity() {
    private lateinit var fn: EditText
    private lateinit var nick: EditText
    private lateinit var age: EditText
    private lateinit var gender: EditText
    private lateinit var sport: EditText
    private lateinit var players: EditText
    private lateinit var location: EditText
    private lateinit var skill: EditText
    private lateinit var equipment: EditText
    private lateinit var disabled: EditText
    private lateinit var cameraActivityResultLauncher: ActivityResultLauncher<Intent>
    private lateinit var galleryActivityResultLauncher: ActivityResultLauncher<Intent>
    private lateinit var imageUri: Uri
    private lateinit var sharedPreferences: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)
        val editView = findViewById<LinearLayout>(R.id.portraitView)
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            editView.orientation = LinearLayout.HORIZONTAL
        }

        sharedPreferences = getSharedPreferences("user", MODE_PRIVATE)
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
        fn.setText(sharedPreferences.getString("full_name", "default full name").toString())
        nick.setText(sharedPreferences.getString("nickname", "default nick name").toString())
        age.setText(sharedPreferences.getInt("age", 0).toString())
        gender.setText(sharedPreferences.getString("gender", "default gender").toString())
        sport.setText(sharedPreferences.getString("sport", "default sport").toString())
        players.setText(sharedPreferences.getInt("num_players", 0).toString())
        location.setText(sharedPreferences.getString("location", "default location").toString())
        skill.setText(sharedPreferences.getString("skill_lvl", "default skill").toString())
        equipment.setText(sharedPreferences.getString("equipment", "default equipment").toString())
        disabled.setText(
            sharedPreferences.getString("info_disabled", "default info_disabled").toString()
        )
        imageUri =
            Uri.parse(sharedPreferences.getString("portrait_uri", "default image uri").toString())

        val b = findViewById<Button>(R.id.button2)
        val cameraButton = findViewById<ImageButton>(R.id.imageButton)
        val imageView = findViewById<ImageView>(R.id.imageView)
        if (imageUri != Uri.EMPTY) {
            imageView.setImageURI(imageUri)
        }

        cameraButton.setOnClickListener {
            registerForContextMenu(cameraButton)
            openContextMenu(cameraButton)
        }

        b.setOnClickListener {
            onSaveClick(savedInstanceState ?: Bundle())
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        cameraActivityResultLauncher =
            registerForActivityResult(
                ActivityResultContracts.StartActivityForResult()
            ) {
                if (it.resultCode == Activity.RESULT_OK) {
//                    imageUri = it.data?.data!!
                    imageView.setImageURI(imageUri)
                }
            }


        galleryActivityResultLauncher =
            registerForActivityResult(
                ActivityResultContracts.StartActivityForResult()
            )
            {
                if (it.resultCode == RESULT_OK) {
                    imageUri = it.data?.data!!
                    imageView.setImageURI(imageUri)
                }
            }

    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.photo_menu, menu)
    }

//    private fun checkPerm(cb: () -> Any?) {
//        if (checkSelfPermission(android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED || checkSelfPermission(
//                android.Manifest.permission.READ_EXTERNAL_STORAGE
//            )
//            == PackageManager.PERMISSION_DENIED
//        ) {
//            val permission = arrayOf(
//                android.Manifest.permission.CAMERA,
//                android.Manifest.permission.READ_EXTERNAL_STORAGE
//            )
//            requestPermissions(permission, 112)
//        } else {
//            cb()
//        }
//    }

    private fun openCamera() {
        val values = ContentValues()
        values.put(MediaStore.Images.Media.TITLE, "New Picture")
        values.put(MediaStore.Images.Media.DESCRIPTION, "From the Camera")
        imageUri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)!!
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
        cameraActivityResultLauncher.launch(cameraIntent)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.take_photo -> {
//                checkPerm { openCamera() }
                openCamera()
            }
            R.id.choose_from_gallery -> {
                val galleryIntent =
                    Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                galleryActivityResultLauncher.launch(galleryIntent)
            }
        }
        return super.onContextItemSelected(item)
    }

    private fun onSaveClick(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val sharedPref = getSharedPreferences("user", MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            sharedPref.edit().clear().apply()
            if (fn.text.isNotEmpty())
                putString("full_name", fn.text.toString())
            if (nick.text.isNotEmpty())
                putString("nickname", nick.text.toString())
            if (age.text.isNotEmpty())
                putInt("age", age.text.toString().toIntOrNull() ?: 0)
            if (gender.text.isNotEmpty())
                putString("gender", gender.text.toString())
            if (sport.text.isNotEmpty())
                putString("sport", sport.text.toString())
            if (players.text.isNotEmpty())
                putInt("num_players", players.text.toString().toIntOrNull() ?: 0)
            if (location.text.isNotEmpty())
                putString("location", location.text.toString())
            if (skill.text.isNotEmpty())
                putString("skill_lvl", skill.text.toString())
            if (equipment.text.isNotEmpty())
                putString("equipment", equipment.text.toString())
            if (disabled.text.isNotEmpty())
                putString("info_disabled", disabled.text.toString())
            if (imageUri != Uri.EMPTY)
                putString("portrait_uri", imageUri.toString())
            apply()
        }
    }


}
