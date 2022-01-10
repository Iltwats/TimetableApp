package com.example.timetablebphc.ui.addCourse

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.example.timetablebphc.R
import kotlinx.android.synthetic.main.fragment_add_menu.*

class AddMenuFragment : Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        add_course_button.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(R.id.action_navigation_menu_fragment_to_navigation_add_course)
        }
        add_test_button.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(R.id.action_navigation_menu_fragment_to_navigation_add_test)
        }
    }

}