package com.example.notescategories

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_edit_note.*
import kotlinx.android.synthetic.main.row.*

class EditNoteActivity : AppCompatActivity() {
    val db = DataBaseHelper(this)
    var TAG_TEMP_NOTE : String = "no tag"

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
        //val TagTextNote = tagTextView.text.toString()
        db.insertData(NoteClass(TitleSendFull, TextSendFull,TAG_TEMP_NOTE))
    }

    private fun showPopup_(view: View){
        val popup = PopupMenu(this, view)
        popup.inflate(R.menu.popup_menu)
        popup.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item: MenuItem? ->
            when(item!!.itemId){
                R.id.item1->{ // giallo
                    TAG_TEMP_NOTE= "PRIMO"
                   // textViewTag.setBackgroundColor(getResources().getColor(R.color.yellow))
                    tagTextView.setText("TAG : PRIMO")

                 //   tagTextView.setTextColor(R.color.)
                    Toast.makeText(this, item.title, Toast.LENGTH_SHORT).show()
                }
                R.id.item2->{ // rosso
                    TAG_TEMP_NOTE = "SECONDO"
                    tagTextView.setText("TAG : SECONDO")
                    Toast.makeText(this, item.title, Toast.LENGTH_SHORT).show()
                }
                R.id.item3 ->{ // blue
                    TAG_TEMP_NOTE = "TERZO"
                    tagTextView.setText("TAG : TERZO")
                    Toast.makeText(this, item.title, Toast.LENGTH_SHORT).show()
                }

            }

            true
        })
        popup.show()
    }




}
