package com.example.timetablebphc.ui.addQuiz

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.timetablebphc.activities.MainActivity
import com.example.timetablebphc.R
import com.example.timetablebphc.courseDB.Quiz
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_add_test.*
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import java.util.*

@AndroidEntryPoint
class AddQuizFragment : Fragment() {
    private val quizViewModel: QuizViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_test, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val quizTypes = resources.getStringArray(R.array.quiz_types)
        var quizType = quizTypes[1]

        if (spinner != null) {
            val adapter = context?.let {
                ArrayAdapter(
                    it,
                    android.R.layout.simple_spinner_item, quizTypes
                )
            }
            spinner.adapter = adapter
            spinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View, position: Int, id: Long
                ) {
                    quizType = quizTypes[position]
                }
                override fun onNothingSelected(parent: AdapterView<*>) {
                }
            }
        }

        var courseName = edit_course_name.text.toString()
        edit_course_name.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(arg0: Editable) {
                courseName = edit_course_name.text.toString()
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        })

        var quizDate = LocalDate.now()

        date.text = SimpleDateFormat("dd/MM").format(System.currentTimeMillis())
        val cal = Calendar.getInstance()
        val dateSetListener = DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, monthOfYear)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            val myFormat = "dd/MM"
            val sdf = SimpleDateFormat(myFormat, Locale.UK)

            val input = cal.time
            date.text = sdf.format(input)
            quizDate  = input.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()

        }

        date.setOnClickListener {
            context?.let { it1 ->
                DatePickerDialog(
                    it1, dateSetListener,
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)
                ).show()
            }
        }

        var quizTime =  LocalTime.now()
        timePicker.setOnTimeChangedListener { _, hour, minute ->
            quizTime = LocalTime.of(hour, minute)
        }

        button_save.setOnClickListener {
            if(courseName == ""){//check for empty field
                Toast.makeText(context,"Please enter course name!", Toast.LENGTH_SHORT).show()
            }else {
                val quiz = Quiz(0, quizType, courseName, quizDate, quizTime)
                quizViewModel.insertQuiz(quiz)
                val message = "$quizType saved!"
                Toast.makeText(context,message, Toast.LENGTH_SHORT).show()
                val intent = Intent(context, MainActivity::class.java) // (1) (2)
                startActivity(intent)
            }
        }
    }

}