package com.ivanshulin.notesappcompousemvvm.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ivanshulin.notesappcompousemvvm.MainViewModel
import com.ivanshulin.notesappcompousemvvm.screens.AddScreen
import com.ivanshulin.notesappcompousemvvm.screens.MainScreen
import com.ivanshulin.notesappcompousemvvm.screens.NoteScreen
import com.ivanshulin.notesappcompousemvvm.screens.StartScreen


sealed class NavRoute(val route: String) {
    object Start : NavRoute("start screen")
    object Main : NavRoute("main screen")
    object Add : NavRoute("add screen")
    object Note : NavRoute("note screen")
}

@Composable
fun NotesNavHost(mViewModel: MainViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavRoute.Start.route) {
        composable(NavRoute.Start.route) { StartScreen(navController,viewModel = mViewModel) }
        composable(NavRoute.Main.route) { MainScreen(navController,viewModel = mViewModel) }
        composable(NavRoute.Add.route) { AddScreen(navController,viewModel = mViewModel) }
        composable(NavRoute.Note.route) { NoteScreen(navController,viewModel = mViewModel) }


    }


}