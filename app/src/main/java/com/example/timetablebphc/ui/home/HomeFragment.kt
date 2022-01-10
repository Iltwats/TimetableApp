package com.example.timetablebphc.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.timetablebphc.R
import com.example.timetablebphc.courseDB.Quiz
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = context?.let { QuizListAdapter(it) }
        recyclerview_home.adapter = adapter
        recyclerview_home.layoutManager = LinearLayoutManager(context)

        Log.v("all quizzes", homeViewModel.allQuizzes.toString())
        homeViewModel.allQuizzes.observe(viewLifecycleOwner, { quizzes ->
            quizzes?.let {
                adapter?.setQuizzes(rearrangeList(it))
            }
        })
    }
    private fun rearrangeList(quizzes: List<Quiz>): List<Quiz>{//sort list with earliest first
        val comparator = compareBy<Quiz> { it.date }
        return quizzes.sortedWith(comparator.thenBy { it.time })
    }

}