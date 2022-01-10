package com.example.timetablebphc.ui.timetable

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import com.example.timetablebphc.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_course_details.*
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class CourseDetailFragment : Fragment() {

    private val timeTableViewModel: TimeTableViewModel by viewModels()

    private var position :Int = 0
    private val args: CourseDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        position = args.position
        return inflater.inflate(R.layout.fragment_course_details, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        timeTableViewModel.allCourses.observe(viewLifecycleOwner, { courses ->
            courses?.let { courses ->
                val displayCourses = timeTableViewModel.getDisplayCourseList(courses)
                val course = displayCourses[position]
                course_code.text = course.code
                course_detail.text = course.detail
                course_time.text = course.time.format(DateTimeFormatter.ofPattern("h:mma"))
                meet_link.text = course.link

                var s = ""
                for (i in 0..5){//create list of days on which course has a class
                    if(course.days[i]) s+= resources.getStringArray(R.array.week_days)[i] + " "
                }
                course_days.text = s
                delete_course_button.setOnClickListener {
                    val builder = context?.let { AlertDialog.Builder(it) }
                    if (builder != null) {
                        builder.setMessage("Are you sure you want to delete?").setCancelable(false)
                            .setPositiveButton("Yes") { _, _ ->
                                timeTableViewModel.deleteCourse(course)
                                NavHostFragment.findNavController(this).navigate(R.id.action_navigation_course_detail_to_dashboard)
                            }.setNegativeButton("No") { dialog, _ ->
                                // Dismiss the dialog
                                dialog.dismiss()
                            }
                        builder.create().show()
                    }
                }
            }
        })
    }

}