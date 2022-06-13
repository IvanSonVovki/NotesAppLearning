package com.ivanshulin.notesappcompousemvvm.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ivanshulin.notesappcompousemvvm.screens.Add
import com.ivanshulin.notesappcompousemvvm.screens.Main
import com.ivanshulin.notesappcompousemvvm.screens.Note
import com.ivanshulin.notesappcompousemvvm.screens.Start

sealed class NovRoute(val route: String) {
    object Start : NovRoute("start screen")
    object Main : NovRoute("main screen")
    object Add : NovRoute("add screen")
    object Note : NovRoute("note screen")
}

@Composable
fun NotesNavHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NovRoute.Start.route) {
        composable(NovRoute.Start.route) { Start(navController) }
        composable(NovRoute.Main.route) { Main(navController) }
        composable(NovRoute.Add.route) { Add(navController) }
        composable(NovRoute.Note.route) { Note(navController) }


    }


}