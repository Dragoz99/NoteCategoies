package com.example.notescategories

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val TAG = "MainActivity"
    val db = DataBaseHelper(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        buttonNewNote.setOnClickListener {
            openEditActivity()
        }


    }


    // inserimento dei dati all'interno del database.

    override fun onStart() {
        super.onStart()
        Log.v(TAG, "onStart")
        db.insertData(NoteClass("bello ", "figo"))
        var NoteClass = db.readData()
        list_view.adapter = MyAdapter(this, NoteClass)

    }

    fun openEditActivity(){
        Log.v(TAG, "onClick")
        db.removeData()
        val intent = Intent (this@MainActivity, EditNoteActivity::class.java)
        startActivity(intent)
    }

    //utilizzare per aprire una nota specifica
    fun openViewActivity(){
        Log.v(TAG, "onClick")
        val intent = Intent (this@MainActivity, viewMode::class.java)
        startActivity(intent)
    }




    class MyAdapter(private val context: Context, val data: MutableList<NoteClass>) : BaseAdapter(){
        override fun getCount(): Int {
            return data.size
        }

        override fun getItem(position: Int): Any {
            return position
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
            var newView = convertView
            if(convertView == null)
                newView = LayoutInflater.from(context).inflate(R.layout.row, parent, false)
            if(newView != null){
                val textView = newView.findViewById<TextView>(R.id.titleTextView) // name Note
                val dateTextView = newView.findViewById<TextView>(R.id.dateTextView) // date note
                val textviewId = newView.findViewById<TextView>(R.id.textViewPosition)

                textView.text = data[position].titleNote
                dateTextView.text = data[position].TextNote
                textviewId.text = "${data[position].id}"
            }
            return newView

        }
    }

}



