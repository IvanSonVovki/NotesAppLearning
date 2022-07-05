package com.ivanshulin.notesappcompousemvvm.database.roam.repository

import androidx.lifecycle.LiveData
import com.ivanshulin.notesappcompousemvvm.database.DatabaseRepository
import com.ivanshulin.notesappcompousemvvm.database.roam.dao.NoteRoomDao
import com.ivanshulin.notesappcompousemvvm.model.Note

class RoomRepository(private val noteRoomDao: NoteRoomDao): DatabaseRepository {
    override val readAll: LiveData<List<Note>>
        get() = noteRoomDao.getAllNotes()


    override suspend fun create(note: Note, onSuccess: () -> Unit) {
        noteRoomDao.addNote(note = note)
        onSuccess()
    }

    override suspend fun update(note: Note, onSuccess: () -> Unit) {
        noteRoomDao.updateNote(note = note)
        onSuccess()
    }

    override suspend fun delete(note: Note, onSuccess: () -> Unit) {
        noteRoomDao.deleteNote(note = note)
        onSuccess()
    }

    override fun signOut() { }
}