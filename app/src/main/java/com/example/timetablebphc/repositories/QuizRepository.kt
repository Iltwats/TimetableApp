package com.example.timetablebphc.repositories

import androidx.annotation.WorkerThread
import com.example.timetablebphc.courseDB.Quiz
import com.example.timetablebphc.dao.QuizDao
import javax.inject.Inject

class QuizRepository @Inject constructor(private val quizDao: QuizDao) {

    @WorkerThread
    suspend fun insertQuiz(quiz: Quiz) {
        quizDao.insertQuiz(quiz)
    }
}