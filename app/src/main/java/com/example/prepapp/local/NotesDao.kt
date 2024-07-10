package com.example.prepapp.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao {
    @Insert
    suspend fun insert(data: NotesModel)

    @Insert
    suspend fun insert(data:List<NotesModel>)

    @Query("DELETE FROM notes")
    suspend fun clear()

    @Query("SELECT * FROM notes")
    fun getAllNotes(): Flow<List<NotesModel>>
}