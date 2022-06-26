package com.ivanshulin.notesappcompousemvvm.screens

import android.app.Application
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ivanshulin.notesappcompousemvvm.MainViewModel
import com.ivanshulin.notesappcompousemvvm.MainViewModelFactory
import com.ivanshulin.notesappcompousemvvm.navigation.NavRoute
import com.ivanshulin.notesappcompousemvvm.ui.theme.NotesAppCompouseMVVMTheme
import com.ivanshulin.notesappcompousemvvm.utils.Constans
import com.ivanshulin.notesappcompousemvvm.utils.TYPE_FIREBASE
import com.ivanshulin.notesappcompousemvvm.utils.TYPE_ROOM

@Composable
fun StartScreen(navController: NavHostController, viewModel: MainViewModel) {

    val context = LocalContext.current
    val mViewModel: MainViewModel =
        viewModel(factory = MainViewModelFactory(context.applicationContext as Application))
    Scaffold(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = Constans.Keys.WHAT_WILL_WE_USE)
            Button(
                onClick = {
                    mViewModel.initDataBase(TYPE_ROOM) {
                        navController.navigate(route = NavRoute.Main.route)

                    }
                },
                modifier = Modifier
                    .width(200.dp)
                    .padding(vertical = 8.dp)
            ) {
                Text(text = Constans.Keys.ROOM_DATABASE)
            }

            Button(
                onClick = {
                    mViewModel.initDataBase(TYPE_FIREBASE) {
                        navController.navigate(route = NavRoute.Main.route)

                    }

                },
                modifier = Modifier
                    .width(200.dp)
                    .padding(vertical = 8.dp)
            ) {
                Text(text = Constans.Keys.FIREBASE_DATABASE)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PrevStartScreen() {
    NotesAppCompouseMVVMTheme() {
        val context = LocalContext.current
        val mViewModel: MainViewModel =
            viewModel(factory = MainViewModelFactory(context.applicationContext as Application))

        StartScreen(navController = rememberNavController(), viewModel = mViewModel)

    }
}