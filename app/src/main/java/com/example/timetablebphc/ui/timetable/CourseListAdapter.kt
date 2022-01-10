/*
 * Copyright (C) 2017 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.timetablebphc.ui.timetable

import android.content.Context
import android.content.res.Resources
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.timetablebphc.R
import com.example.timetablebphc.courseDB.Course
import java.time.format.DateTimeFormatter


class CourseListAdapter internal constructor(
        context: Context,
) : RecyclerView.Adapter<CourseListAdapter.CourseViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var courses = emptyList<Course>() // Cached copy of words
    private val resources: Resources = context.resources
    private val theme: Resources.Theme = context.theme
    inner class CourseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.cell_text_view)
        val cellTime: TextView = itemView.findViewById(R.id.cell_time)
        val courseCardView: CardView = itemView.findViewById(R.id.course_card_view)
        val headerCardView: CardView = itemView.findViewById(R.id.header_card_view)
        val headerTextView: TextView = itemView.findViewById(R.id.header_text_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val itemView = inflater.inflate(R.layout.row_course, parent, false)
        parent.context
        return CourseViewHolder(itemView)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {

        holder.setIsRecyclable(false)

        val current = courses[position]

        val a = resources.getStringArray(R.array.week_days)

        if(position%10 == 0){//day header
            holder.courseCardView.visibility = GONE
            holder.headerCardView.visibility = VISIBLE
            holder.headerCardView.isClickable = true
            holder.headerTextView.text = a[position/10]
        }else{
            if(current.code == "") {//empty course
                holder.courseCardView.setCardBackgroundColor(resources.getColor(R.color.empty_cell_color, theme))
            }
            else {
                holder.courseCardView.isClickable = false //the course card view is to be set clickable. Strangely, it is clickable when value is false and not clickable when true.
                holder.textView.text = current.code
                val time = current.time.format(DateTimeFormatter.ofPattern("h:mma"))
                holder.cellTime.text = time
            }
        }

        holder.itemView.setOnClickListener {
            val action = DashboardFragmentDirections.actionNavigationDashboardToCourseDetail(position)
            holder.itemView.findNavController().navigate(action)//open course detail fragment
        }
    }

    internal fun setCourses(courses: List<Course>) {
        this.courses = courses
        Log.v("all courses list", courses.toString())
        notifyDataSetChanged()
    }

    override fun getItemCount() = courses.size

}


