package com.example.timetablebphc.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.timetablebphc.courseDB.Quiz
import com.example.timetablebphc.repositories.HomeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel @ViewModelInject constructor(
    private val repository: HomeRepository
) : ViewModel()  {

    val allQuizzes: LiveData<List<Quiz>> = repository.allQuizzes

    fun deleteQuiz(quiz: Quiz) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteQuiz(quiz)
    }

}