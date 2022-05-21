package com.example.notescategories

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_edit_note.*

class EditNoteActivity : AppCompatActivity() {
    val db = DataBaseHelper(this)
    lateinit var TAG_TEMP_NOTE : String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_note)

        popupBtn.setOnClickListener {
            showPopup_(popupBtn)
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

    private fun showPopup_(view: View){
        val popup = PopupMenu(this, view)
        popup.inflate(R.menu.popup_menu)
        popup.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item: MenuItem? ->
            when(item!!.itemId){
                R.id.item1->{ // giallo
                    TAG_TEMP_NOTE= "GIALLO"

                    Toast.makeText(this, item.title, Toast.LENGTH_SHORT).show()
                }
                R.id.item2->{ // rosso
                    TAG_TEMP_NOTE = "ROSSO"
                    Toast.makeText(this, item.title, Toast.LENGTH_SHORT).show()
                }
                R.id.item3 ->{ // blue
                    TAG_TEMP_NOTE = "BLUE"
                    Toast.makeText(this, item.title, Toast.LENGTH_SHORT).show()
                }

            }

            true
        })
        popup.show()
    }




}
