package com.example.notescategories

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.ContactsContract
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

val DATABASE_VERSION = 1
val DATABASE_NAME ="NoteDataBaseDbd.db"
// per aver un database pulito bisogna rinominare DATABASE_NAME
val TABLENAME = "NoteDataBase"
val COL_TEXT_NOTE= "textNote"
val COL_TITLE_NOTE ="titleNote"
val COL_ID_NOTE = "id"
val COL_TAG_NOTE = "tag"





class DataBaseHelper(var context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {

        var createTable =  "CREATE TABLE " + TABLENAME + " (" +
                COL_ID_NOTE + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_TITLE_NOTE + " VARCHAR(256)," +
                COL_TEXT_NOTE + " VARCHAR(256), "+
                COL_TAG_NOTE + " VARCHAR(256))"

        db?.execSQL(createTable)
    }


    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
       db?.execSQL("DROP TABLE IF EXISTS"+ TABLENAME)
        onCreate(db)
    }

    fun insertData(nota: NoteClass){
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_TEXT_NOTE, nota.TextNote)
        contentValues.put(COL_TITLE_NOTE, nota.titleNote)
        //contentValues.put(COL_TAG_NOTE, nota.tag_note)

        val result = database.insert(TABLENAME, null, contentValues)

        if (result == (0).toLong()) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        }

        else {
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
        }



    }



    fun readData(): MutableList<NoteClass>{
        val list: MutableList<NoteClass> = ArrayList()
        val db = this.readableDatabase
        val query = "Select * from $TABLENAME"
        val result = db.rawQuery(query, null)
        if (result.moveToFirst()) {
            do {
                val note = NoteClass()
                note.id = result.getInt(result.getColumnIndex(COL_ID_NOTE))
                note.titleNote = result.getString(result.getColumnIndex(COL_TITLE_NOTE))
                note.TextNote = result.getString(result.getColumnIndex(COL_TEXT_NOTE))
                //note.tag_note = result.getString(result.getColumnIndex(COL_TAG_NOTE))
                list.add(note)
            }while(result.moveToNext())
        }
        return list
    }
    fun removeAllData(){
        val db = this.writableDatabase
        db?.execSQL("DELETE FROM "+TABLENAME)
    }
    fun removeSingleNote(pos: Int){
        val db = this.writableDatabase
        db?.execSQL("DELETE FROM "+TABLENAME +" WHERE "+COL_ID_NOTE+ " = "+pos)
    }




}

