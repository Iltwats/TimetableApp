package com.example.timetablebphc.ui.addCourse

import android.app.AlarmManager
import android.app.Application
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.timetablebphc.notifications.AlarmReceiver
import com.example.timetablebphc.courseDB.Course
import com.example.timetablebphc.repositories.CourseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*


class CourseViewModel @ViewModelInject constructor(
    private val repository: CourseRepository,
    application: Application
) : AndroidViewModel(application) {

    val context = application

    @RequiresApi(Build.VERSION_CODES.O)
    fun insertCourse(course: Course) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertCourse(course)
        if (course.notify)
            setNotificationForCourse(course)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setNotificationForCourse(course: Course) {
        val remindBefore = 10
        val totalMinutes = (course.time.hour * 60 + course.time.minute) - remindBefore
        val hour = totalMinutes / 60
        val minutes = totalMinutes % 60
        val courseCode =
            course.code //Displays in notification

        for (i in 0..5) {
            if (course.days[i])
                setNotification(courseCode, i, hour, minutes)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun setNotification(courseCode: String, dayNumber: Int, hour: Int, minutes: Int) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager?

        val notificationIntent = Intent(context, AlarmReceiver::class.java)
        notificationIntent.putExtra("title", courseCode)
        notificationIntent.putExtra("message", "You have a class!")
        val broadcast = PendingIntent.getBroadcast(
            context,
            100,
            notificationIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        val cal = Calendar.getInstance()
        cal[Calendar.DAY_OF_WEEK] = dayNumber
        cal[Calendar.HOUR_OF_DAY] = hour
        cal[Calendar.MINUTE] = minutes

        alarmManager!!.setRepeating(
            AlarmManager.RTC_WAKEUP,
            cal.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            broadcast
        )
    }

}
