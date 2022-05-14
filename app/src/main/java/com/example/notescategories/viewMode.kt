package com.example.notescategories

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_view_mode.*


class viewMode : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_mode)

        val intent = intent
        val title = intent.getStringExtra("TITLE_NOTE")
        val id = intent.getStringExtra("ID_NOTE")
        val text  = intent.getStringExtra("TEXT_NOTE")

        textViewTitle.setText(title)
        textViewId.setText(id)
        textViewField.setText(text)











    }
}