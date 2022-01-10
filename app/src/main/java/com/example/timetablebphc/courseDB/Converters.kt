package com.example.timetablebphc.courseDB

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.TypeConverter
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class Converters {

    @TypeConverter
    fun fromString(stringListString: String): MutableList<Boolean> {
        val a = stringListString.split(",").map { it }
        val list = mutableListOf(false, false, false, false, false, false)
        for (i in a.indices){
            if (a[i] == "true")
                try {
                    list[i] = true
                }catch (e: Exception){}
        }
        return list
    }

    @TypeConverter
    fun listToString(list: MutableList<Boolean>): String {
        return list.joinToString(separator = ",")
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun fromTime(value: String?): LocalTime? {
        val a = value?.split(":")?.map { it }
        return a?.get(0)?.toInt()?.let { LocalTime.of(it, a[1].toInt()) }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun timeToString(time: LocalTime?): String? {
        val formatter = DateTimeFormatter.ofPattern("HH:mm")

        return time?.format(formatter)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun fromDate(value: String?): LocalDate? {
        val a = value?.split("/")?.map { it }
        return a?.get(1)?.toInt()?.let { LocalDate.of(a[2].toInt(), it,a[0].toInt()) }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun dateToString(date: LocalDate?): String? {
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        return date?.format(formatter)
    }

}
