package com.example.showprofileactivity

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
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

        val b = findViewById<Button>(R.id.button2)
        val cameraButton = findViewById<ImageButton>(R.id.imageButton)
        val imageView = findViewById<ImageView>(R.id.imageView)

        cameraButton.setOnClickListener {
            registerForContextMenu(cameraButton)
            openContextMenu(cameraButton)
        }

        b.setOnClickListener {
            onSaveClick(savedInstanceState ?: Bundle())
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

    override fun onSaveInstanceState(outState: Bundle) {
        //saves data when screen is rotated
        super.onSaveInstanceState(outState)
        outState.putString("fullname_text", fn.toString())
        outState.putString("nickname_text", nick.toString())
        outState.putInt("age_int", age.toString().toIntOrNull() ?: 0)
        outState.putString("gender_text", gender.toString())
        outState.putString("sport_text", sport.toString())
        outState.putInt("players_int", players.toString().toIntOrNull() ?: 0)
        outState.putString("location_text", location.toString())
        outState.putString("skill_text", skill.toString())
        outState.putString("equipment_text", equipment.toString())
        outState.putString("disabled_text", disabled.toString())
    }

    private fun onSaveClick(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("fullname_text", fn.toString())
        outState.putString("nickname_text", nick.toString())
        outState.putInt("age_int", age.toString().toIntOrNull() ?: 0)
        outState.putString("gender_text", gender.toString())
        outState.putString("sport_text", sport.toString())
        outState.putInt("players_int", players.toString().toIntOrNull() ?: 0)
        outState.putString("location_text", location.toString())
        outState.putString("skill_text", skill.toString())
        outState.putString("equipment_text", equipment.toString())
        outState.putString("disabled_text", disabled.toString())
    }


}
