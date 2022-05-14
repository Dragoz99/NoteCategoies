package com.example.notescategories

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class EditNoteActivity : AppCompatActivity() {
    val db = DataBaseHelper(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_note)


    }


}