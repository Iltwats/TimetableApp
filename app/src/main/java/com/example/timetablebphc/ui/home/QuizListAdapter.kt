package com.example.timetablebphc.ui.home

import android.content.Context
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.timetablebphc.R
import com.example.timetablebphc.courseDB.Quiz
import java.time.format.DateTimeFormatter


class QuizListAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<QuizListAdapter.CourseViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var quizzes = emptyList<Quiz>() // Cached copy of words

    inner class CourseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val quizType: TextView = itemView.findViewById(R.id.quiz_type)
        val courseName: TextView = itemView.findViewById(R.id.course_name)
        val quizDate: TextView = itemView.findViewById(R.id.quiz_date)
        val quizTime: TextView = itemView.findViewById(R.id.quiz_time)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val itemView = inflater.inflate(R.layout.row_quiz, parent, false)
        return CourseViewHolder(itemView)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        val current = quizzes[position]
        holder.quizType.text = current.type
        holder.courseName.text = current.course
        holder.quizDate.text = current.date.toString()
        holder.quizTime.text = current.time.format(DateTimeFormatter.ofPattern("h:mma"))

        holder.itemView.setOnClickListener {
            val action = HomeFragmentDirections.actionNavigationHomeToQuizDetail(position)
            holder.itemView.findNavController().navigate(action)
        }
    }

    internal fun setQuizzes(quizzes: List<Quiz>) {
        this.quizzes = quizzes
        Log.v("all quizzes list", quizzes.toString())
        notifyDataSetChanged()
    }

    override fun getItemCount() = quizzes.size

}