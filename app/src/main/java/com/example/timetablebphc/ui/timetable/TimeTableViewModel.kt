package com.example.timetablebphc.ui.timetable

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.timetablebphc.courseDB.*
import com.example.timetablebphc.repositories.TimeTableRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalTime

@RequiresApi(Build.VERSION_CODES.O)
class TimeTableViewModel @ViewModelInject constructor(
    private val repository: TimeTableRepository
) : ViewModel() {


    val allCourses: LiveData<List<Course>> = repository.allCourses

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text

    fun deleteCourse(course: Course) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteCourse(course)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getDisplayCourseList(courses : List<Course>) : List<Course> {

        if(courses.isEmpty())
            return courses

        var posCourses =0
        var posDisplayCourses = 0

        val displayCourses = emptyList<Course>().toMutableList()

        val emptyCourse =Course(0,"","", LocalTime.now(), mutableListOf(false), "", false)
        for(i in 1..60)
            displayCourses.add(emptyCourse)

        for (day in 0 until 6){
            for (hour in 0..9){

                if(hour == 0) {//if the position should contain week header
                    posDisplayCourses++
                    continue
                }
                for (course in courses) {
                    val courseHour = course.time.hour - 7
                    if (course.days[day] && courseHour == hour) {//if the course has a class for the current cell
                        displayCourses[posDisplayCourses] = course
                        posCourses++
                    }
                }
                posDisplayCourses++
            }
        }
        return displayCourses
    }

}
