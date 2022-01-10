package com.example.timetablebphc.courseDB

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.LocalTime

@Entity(tableName = "quiz_data_table")
data class Quiz(

    @PrimaryKey(autoGenerate = true)
    var key: Int,

    @ColumnInfo(name = "quiz_type")
    var type: String,

    @ColumnInfo(name = "course_name")
    var course: String,

    @ColumnInfo(name = "quiz_date")
    var date: LocalDate,

    @ColumnInfo(name = "quiz_time ")
    var time: LocalTime

)