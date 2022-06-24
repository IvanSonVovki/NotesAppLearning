package com.ivanshulin.notesappcompousemvvm

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ivanshulin.notesappcompousemvvm.database.roam.AppRoomDatabase
import com.ivanshulin.notesappcompousemvvm.database.roam.repository.RoomRepository
import com.ivanshulin.notesappcompousemvvm.utils.REPOSITORY
import com.ivanshulin.notesappcompousemvvm.utils.TYPE_ROOM

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
}

class MainViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(application = application) as T
        }

        throw IllegalArgumentException("Unknown viewModel class")
    }
}
