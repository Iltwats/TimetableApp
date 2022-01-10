package com.example.timetablebphc.courseDB

import android.content.Context
import androidx.room.TypeConverters
import com.example.timetablebphc.dao.CourseDao
import com.example.timetablebphc.repositories.CourseRepository
import com.example.timetablebphc.dao.QuizDao
import com.example.timetablebphc.repositories.QuizRepository
import com.example.timetablebphc.dao.TimeTableDao
import com.example.timetablebphc.repositories.TimeTableRepository
import com.example.timetablebphc.dao.HomeDao
import com.example.timetablebphc.repositories.HomeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@InstallIn(ApplicationComponent::class)
@Module
@TypeConverters(Converters::class)
object DBModule {

    @Provides
    fun provideStudentDao(@ApplicationContext appContext: Context) : CourseDao = CourseRoomDatabase.getInstance(appContext).courseDao()

    @Provides
    fun provideStudentDBRepository(courseDao: CourseDao) = CourseRepository(courseDao)

    @Provides
    fun provideQuizDao(@ApplicationContext appContext: Context) : QuizDao = CourseRoomDatabase.getInstance(appContext).quizDao()

    @Provides
    fun provideQuizDBRepository(quizDao: QuizDao) = QuizRepository(quizDao)

    @Provides
    fun provideTimetableDao(@ApplicationContext appContext: Context) : TimeTableDao = CourseRoomDatabase.getInstance(appContext).timeTableDao()

    @Provides
    fun provideTimetableDBRepository(timeTableDao: TimeTableDao) = TimeTableRepository(timeTableDao)

    @Provides
    fun provideHomeDao(@ApplicationContext appContext: Context) : HomeDao = CourseRoomDatabase.getInstance(appContext).homeDao()

    @Provides
    fun provideHomeDBRepository(homeDao: HomeDao) = HomeRepository(homeDao)
}