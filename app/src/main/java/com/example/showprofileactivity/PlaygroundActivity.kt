package com.example.showprofileactivity


import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.stacktips.view.CalendarListener
import com.stacktips.view.CustomCalendarView
import com.stacktips.view.DayDecorator
import com.stacktips.view.DayView
import com.stacktips.view.utils.CalendarUtils
import java.text.SimpleDateFormat
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