package com.example.timetablebphc.ui.home

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
import kotlinx.android.synthetic.main.fragment_quiz_details.*
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class QuizDetailFragment :Fragment(){

    private val homeViewModel: HomeViewModel by viewModels()

    private var position :Int = 0
    private val args: QuizDetailFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        position = args.position
        return inflater.inflate(R.layout.fragment_quiz_details, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel.allQuizzes.observe(viewLifecycleOwner, { quizzes ->
            quizzes?.let { quizzes ->
                if(quizzes.isNotEmpty()) {
                    val quiz = quizzes[position]
                    quiz_type.text = quiz.type
                    course_name.text = quiz.course
                    quiz_date.text = quiz.date.toString()
                    quiz_time.text = quiz.time.format(DateTimeFormatter.ofPattern("h:mma"))
                    delete_quiz_button.setOnClickListener {
                        val builder = context?.let { AlertDialog.Builder(it) }
                        if (builder != null) {
                            builder.setMessage("Are you sure you want to delete?").setCancelable(false)
                                .setPositiveButton("Yes") { _, _ ->
                                    homeViewModel.deleteQuiz(quiz)
                                    NavHostFragment.findNavController(this).navigate(R.id.action_navigation_quiz_detail_to_home)
                                }.setNegativeButton("No") { dialog, _ ->
                                    // Dismiss the dialog
                                    dialog.dismiss()
                                }
                            builder.create().show()
                        }
                    }
                }
            }
        })
    }

}