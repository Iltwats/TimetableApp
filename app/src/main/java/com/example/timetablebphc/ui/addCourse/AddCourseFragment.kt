package com.example.timetablebphc.ui.addCourse

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.timetablebphc.activities.MainActivity
import com.example.timetablebphc.R
import com.example.timetablebphc.courseDB.Course
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_add_course.*
import kotlinx.android.synthetic.main.fragment_add_course.button_save
import java.time.LocalTime

@AndroidEntryPoint
class AddCourseFragment : Fragment() {
    private val courseViewModel: CourseViewModel by viewModels()

    private lateinit var daysList: MutableList<Boolean>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        daysList = mutableListOf(false, false, false, false, false, false)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_course, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var courseTime = LocalTime.now()
        course_time_spinner.setOnTimeChangedListener { _, hour, minute ->
            courseTime = LocalTime.of(hour, minute)
        }

        for (i in 0 until week_grid_layout.childCount) {
            val dayCheckBox = week_grid_layout.getChildAt(i)
            dayCheckBox.setOnClickListener {
                if (dayCheckBox is CheckBox) {
                    val checked: Boolean = dayCheckBox.isChecked
                    daysList[i] = checked
                }
            }
        }

        var notify = false
        notification_switch.setOnCheckedChangeListener { _, isChecked ->
            notify = isChecked
        }

        button_save.setOnClickListener {
            val courseCode = edit_course_code.text.trim().toString()
            val courseDetails = edit_course_detail.text.trim().toString()
            val meetLink = edit_meet_link.text.trim().toString()

            val course =
                Course(0, courseCode, courseDetails, courseTime, daysList, meetLink, notify)
            if(checkInput(course)) {
                courseViewModel.insertCourse(course)
                Toast.makeText(context,"Course saved!", Toast.LENGTH_SHORT).show()
                val intent = Intent(context, MainActivity::class.java)
                startActivity(intent)
            }

        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun checkInput(course: Course) : Boolean{
        if(course.code == ""){
            Toast.makeText(context,"Please enter course code!", Toast.LENGTH_SHORT).show()
            return false
        } else if(course.detail == ""){
            Toast.makeText(context,"Please enter course name/detail!", Toast.LENGTH_SHORT).show()
            return false
        }else if(course.time.hour >16 || course.time.hour <8 ){
            Toast.makeText(context,"Please enter time during academic hours!", Toast.LENGTH_SHORT).show()
            return false
        } else if(!course.days.contains(true)){//if no checkbox is clicked all the list won't contain true
            Toast.makeText(context,"Please select at least one day!", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

}
