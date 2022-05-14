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
val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    val db = DataBaseHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        buttonNewNote.setOnClickListener {
            openEditActivity()
        }
        list_view.setOnItemClickListener { parent, view, position, id ->
            Log.v(TAG,"id: $id \n" +
                    "view: $view \n " +
                    "position:$position \n" +
                    "partent $parent")
            val intent = Intent (this@MainActivity, viewMode::class.java)
            intent.putExtra("TITLE_NOTE", (list_view.adapter as MyAdapter).returnTitleNote(position))
            intent.putExtra("ID_NOTE",(list_view.adapter as MyAdapter).retunrIdNote(position))
            intent.putExtra("TEXT_NOTE", (list_view.adapter as MyAdapter).returnTextNote(position))
            (list_view.adapter as MyAdapter).returnTitleNote(position)
            (list_view.adapter as MyAdapter).retunrIdNote(position)
            (list_view.adapter as MyAdapter).returnTextNote(position)
            startActivity(intent)
        }
    }


    // inserimento dei dati all'interno del database.

    override fun onStart() {
        super.onStart()
        Log.v(TAG, "onStart")
        //db.insertData(NoteClass("bello ", "figo"))
        var NoteClass = db.readData()
        list_view.adapter = MyAdapter(this, NoteClass)


    }

    fun openEditActivity(){
        Log.v(TAG, "onClick")

        val intent = Intent (this@MainActivity, EditNoteActivity::class.java)

        startActivity(intent)
    }

    //utilizzare per aprire una nota specifica
    fun openViewActivity(title: String, note : String, date: String){
        Log.v(TAG, "onClick")


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

        //funzione che ritorna l'id della nota dal numero della posizione
        fun retunrIdNote(position: Int): Int{
            Log.v(TAG, "id:return")
            return data[position].id

        }
        fun returnTitleNote(position: Int): String{
            Log.v(TAG, "title:return")
            return data[position].titleNote
        }
        fun returnTextNote(position: Int): String{
            Log.v(TAG, "text:return")
            return data[position].TextNote
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



