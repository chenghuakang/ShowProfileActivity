package com.example.showprofileactivity


import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.stacktips.view.CustomCalendarView
import com.stacktips.view.DayDecorator
import com.stacktips.view.DayView
import com.stacktips.view.utils.CalendarUtils
import java.util.*


class PlaygroundActivity : AppCompatActivity() {
    var calendarView: CustomCalendarView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playground)

        calendarView = findViewById(R.id.calendar_view)
        val currentCalendar: Calendar = Calendar.getInstance(Locale.getDefault())
        calendarView?.firstDayOfWeek = Calendar.MONDAY
        calendarView?.refreshCalendar(currentCalendar)


        val decorators: MutableList<DayDecorator> = mutableListOf()
        decorators.add(DisabledColorDecorator())
        calendarView?.decorators = decorators
        calendarView?.refreshCalendar(currentCalendar)

        val b = findViewById<Button>(R.id.choose)
        b.setOnClickListener {
            //
            insertDataToDatabase()

            val intent = Intent(this, ReservationActivity::class.java)
            startActivity(intent)
        }
        }
    private fun insertDataToDatabase(){
        val reservation1 = Reservation("111","11","1","11","11","11","1",0)
        var vm = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory(application)
        ).get(
            MyViewModel::class.java
        )
        vm.addReservation(reservation1)
        Toast.makeText(applicationContext, "Successfully added!", Toast.LENGTH_LONG).show()

    }

    //makes the background of past dates gray
    private class DisabledColorDecorator : DayDecorator {
        override fun decorate(dayView: DayView) {
            if (CalendarUtils.isPastDay(dayView.date)) {
                val color = Color.parseColor("#a9afb9")
                dayView.setBackgroundColor(color)
            }
        }
    }


    fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {
            val checked = view.isChecked

            when (view.getId()) {
                R.id.radio_football ->
                    if (checked) {
                       //filter dates
                    }
                R.id.radio_basketball ->
                    if (checked) {
                        //filter dates
                    }
            }
        }
    }
}