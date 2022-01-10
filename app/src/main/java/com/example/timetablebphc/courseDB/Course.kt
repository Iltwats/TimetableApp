package com.example.timetablebphc.courseDB

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalTime

@Entity(tableName = "course_data_table")
data class Course(

    @PrimaryKey(autoGenerate = true)
    var key: Int,

    @ColumnInfo(name = "course_code")
    var code: String,

    @ColumnInfo(name = "course_detail")
    var detail: String,

    @ColumnInfo(name = "course_time")
    var time: LocalTime,

    @ColumnInfo(name = "course_days")
    var days: MutableList<Boolean>,

    @ColumnInfo(name = "meet_link")
    var link: String,

    @ColumnInfo(name = "notify")
    var notify: Boolean

)