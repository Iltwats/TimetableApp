package com.example.timetablebphc.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.timetablebphc.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
    }

}