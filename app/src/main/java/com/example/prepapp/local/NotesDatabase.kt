package com.example.prepapp.local

import android.content.Context
import android.provider.ContactsContract.CommonDataKinds.Note
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [NotesModel::class], version = 1)
abstract class NotesDatabase : RoomDatabase(){
    abstract fun notesDao(): NotesDao
    companion object{
        private var instance: NotesDatabase? = null

        @Synchronized
        fun getInstance(context: Context):NotesDatabase{
            if(instance == null){
                instance = Room.databaseBuilder(context,NotesDatabase::class.java,"notes_database").fallbackToDestructiveMigration().build()
            }
            return instance!!
        }
    }
}