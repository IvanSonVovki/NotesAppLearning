package com.ivanshulin.notesappcompousemvvm

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ivanshulin.notesappcompousemvvm.database.roam.AppRoomDatabase
import com.ivanshulin.notesappcompousemvvm.database.roam.repository.RoomRepository
import com.ivanshulin.notesappcompousemvvm.model.Note
import com.ivanshulin.notesappcompousemvvm.utils.REPOSITORY
import com.ivanshulin.notesappcompousemvvm.utils.TYPE_ROOM
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    val context = application

    fun initDataBase(type: String, onSuccess: () -> Unit) {

        Log.d("checkData", "MainViewModel initDateBase with type: $type")
        when (type) {
            TYPE_ROOM -> {
                val dao = AppRoomDatabase.getInstance(context = context).getRoomDao()
                REPOSITORY = RoomRepository(dao)
                onSuccess()
            }
        }
    }

    fun addNote(note: Note, onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY.create(note = note) {
                viewModelScope.launch(Dispatchers.Main) {
                    onSuccess()
                }
            }
        }
    }

    fun readAllNotes() = REPOSITORY.readAll
}

class MainViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(application = application) as T
        }

        throw IllegalArgumentException("Unknown viewModel class")
    }
}
