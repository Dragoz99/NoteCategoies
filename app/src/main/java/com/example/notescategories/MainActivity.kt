package com.example.notescategories

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.*
import android.widget.*
import androidx.core.view.get
import kotlinx.android.synthetic.main.activity_edit_note.view.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.row.view.*

val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    val db = DataBaseHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        onUpdate()

        buttonNewNote.setOnClickListener {
           onUpdate()
            openEditActivity()

        }
        onUpdate()
        // al click dell'oggetto
        list_view.setOnItemClickListener { parent, view, position, id ->
            onUpdate()
            val intent = Intent (this@MainActivity, viewMode::class.java)
            // passaggio parametri activity
            intent.putExtra("TITLE_NOTE", (list_view.adapter as MyAdapter).returnTitleNote(position))
            intent.putExtra("ID_NOTE",(list_view.adapter as MyAdapter).retunrIdNote(position))
            intent.putExtra("TEXT_NOTE", (list_view.adapter as MyAdapter).returnTextNote(position))
            intent.putExtra("TAG_NOTE",(list_view.adapter as MyAdapter).returnTagNote(position)) // tag note

            onUpdate()
            startActivity(intent)

        }

        var NoteClass = db.readData()
        list_view.adapter = MyAdapter(this, NoteClass)
        onUpdate()

        list_view.onItemLongClickListener = AdapterView.OnItemLongClickListener { parent, view, position, id ->
            onLongClick(view,position)
            true

        }

        onUpdate()
    }
    @Override

    // cancellazione nota
    fun onLongClick(v: View, p: Int): Boolean{
        val menu = PopupMenu(this, v)
        menu.menu.add("DELATE")
        menu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
            if(item.title.equals("DELATE")){
                // azione di cancellazione
                val id_nota = (list_view.adapter as MyAdapter).retunrIdNote(p) // salva l'id della nota selezionata
               // System.out.println(id_nota) // per debug
                db?.removeSingleNote(id_nota)
                onUpdate() // aggiornamento
            }
            true
        })
        menu.show() // mosta il menu
        onUpdate() // aggiornamento
        return true
    }


    // custom
    fun onUpdate(){
        var NoteClass = db.readData()
        list_view.adapter = MyAdapter(this, NoteClass)
    }

    // inserimento dei dati all'interno del database.


    override fun onStop(){
        super.onStop()
        Log.v(TAG, "onStart")
        onUpdate()
    }
    override fun onStart() {
        super.onStart()
        Log.v(TAG, "onStart")
        onUpdate()
    }
    override fun onRestart() {
        super.onRestart()
        Log.v(TAG, "onRestart")
          onUpdate()
    }
    override fun onPause() {
        super.onPause()
        Log.v(TAG, "onPause")
        onUpdate()

    }
    override fun onDestroy() {
        super.onDestroy()
        Log.v(TAG, "onDestroy")
        onUpdate()
    }
    override fun onResume() {
        super.onResume()
        onUpdate()
    }
    fun openEditActivity(){
        Log.v(TAG, "onClick")

        val intent = Intent (this@MainActivity, EditNoteActivity::class.java)

        onUpdate()
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
        fun returnTagNote(position: Int): String{
            Log.v(TAG, "tag: return ")
            return data[position].TagNote
        }


        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
            var newView = convertView
            if(convertView == null)
                newView = LayoutInflater.from(context).inflate(R.layout.row, parent, false)
            if(newView != null){
                val textView = newView.findViewById<TextView>(R.id.titleTextView) // name Note
                val dateTextView = newView.findViewById<TextView>(R.id.dateTextView) // date note
                val textTagView = newView.findViewById<TextView>(R.id.textViewTag) // tag note

                textView.text = data[position].titleNote
                dateTextView.text = data[position].TextNote
                textTagView.text = data[position].TagNote

            }

            return newView

        }
    }

}



