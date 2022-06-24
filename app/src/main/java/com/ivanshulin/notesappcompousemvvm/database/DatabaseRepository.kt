package com.ivanshulin.notesappcompousemvvm.database

import androidx.lifecycle.LiveData
import com.ivanshulin.notesappcompousemvvm.model.Note

interface DatabaseRepository {

    val readAll: LiveData<List<Note>>

    suspend fun create(note: Note, onSuccess: ()-> Unit)

    suspend fun update(note: Note, onSuccess: () -> Unit)

    suspend fun delete(note: Note, onSuccess: () -> Unit)
}