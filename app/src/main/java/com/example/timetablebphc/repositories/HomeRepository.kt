package com.example.timetablebphc.repositories

import androidx.lifecycle.LiveData
import com.example.timetablebphc.courseDB.Quiz
import com.example.timetablebphc.dao.HomeDao
import javax.inject.Inject

class HomeRepository @Inject constructor(private val homeDao: HomeDao) {

    val allQuizzes: LiveData<List<Quiz>> = homeDao.getAllQuizzes()

    suspend fun deleteQuiz(quiz: Quiz) {
        homeDao.delete(quiz)
    }
}