package com.example.timetablebphc.ui.timetable

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.timetablebphc.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_timetable.*

@AndroidEntryPoint
class DashboardFragment : Fragment() {
    private val timeTableViewModel: TimeTableViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //timeTableViewModel = ViewModelProvider(this).get(TimeTableViewModel::class.java)
        return inflater.inflate(R.layout.fragment_timetable, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = context?.let { CourseListAdapter(it) }
        recyclerview.adapter = adapter
        recyclerview.layoutManager = GridLayoutManager(context, 10, GridLayoutManager.HORIZONTAL, false)

        Log.v("all courses", timeTableViewModel.allCourses.toString())
        timeTableViewModel.allCourses.observe(viewLifecycleOwner, { courses ->
            courses?.let {
                val displayCourses = timeTableViewModel.getDisplayCourseList(it)//create the list to be displayed in the grid layout recyclerview
                adapter?.setCourses(displayCourses)
            }
        })
    }
}