package com.example.notescategories

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_edit_note.*

class EditNoteActivity : AppCompatActivity() {
    val db = DataBaseHelper(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_note)

        btnUpdate.setOnClickListener{

        }


        btnCancle.setOnClickListener{

        }
    }


    override fun onStart() {
        super.onStart()
    }

    override fun onStop() {
        super.onStop()
        val TitleSendFull= TitleNoteText.text.toString()
        val TextSendFull = NoteTextField.text.toString()
        db.insertData(NoteClass(TitleSendFull, TextSendFull))
    }




}