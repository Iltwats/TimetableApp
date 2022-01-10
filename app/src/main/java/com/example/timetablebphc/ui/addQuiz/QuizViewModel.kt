package com.example.timetablebphc.ui.addQuiz

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
import com.example.timetablebphc.courseDB.Quiz
import com.example.timetablebphc.repositories.QuizRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class QuizViewModel @ViewModelInject constructor(
    private val repository: QuizRepository,
    application: Application
) : AndroidViewModel(application) {

    val context = application

    //launching coroutine
    @RequiresApi(Build.VERSION_CODES.O)
    fun insertQuiz(quiz: Quiz) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertQuiz(quiz)
        setNotification(quiz)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setNotification(quiz: Quiz) {
        val remindBefore = 10 //notification 10 minutes before the quiz
        val totalMinutes = (quiz.time.hour * 60 + quiz.time.minute) - remindBefore
        val hour = totalMinutes / 60
        val minutes = totalMinutes % 60
        val title = quiz.type + " : " + quiz.course//Displays in notification, might want to change to course detail later

        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager?

        val notificationIntent = Intent(context, AlarmReceiver::class.java)
        notificationIntent.putExtra("title", title)
        notificationIntent.putExtra("message", "Coming up.")
        val broadcast = PendingIntent.getBroadcast(
            context,
            100,
            notificationIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        val cal = Calendar.getInstance()
        cal[Calendar.YEAR] = quiz.date.year
        cal[Calendar.DAY_OF_YEAR] = quiz.date.dayOfYear
        cal[Calendar.HOUR_OF_DAY] = hour
        cal[Calendar.MINUTE] = minutes

        alarmManager!!.setExact(
            AlarmManager.RTC_WAKEUP,
            cal.timeInMillis,
            broadcast
        )
    }

}