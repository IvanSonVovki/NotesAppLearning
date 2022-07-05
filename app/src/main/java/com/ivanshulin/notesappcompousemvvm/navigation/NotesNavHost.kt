package com.ivanshulin.notesappcompousemvvm.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ivanshulin.notesappcompousemvvm.MainViewModel
import com.ivanshulin.notesappcompousemvvm.screens.AddScreen
import com.ivanshulin.notesappcompousemvvm.screens.MainScreen
import com.ivanshulin.notesappcompousemvvm.screens.NoteScreen
import com.ivanshulin.notesappcompousemvvm.screens.StartScreen
import com.ivanshulin.notesappcompousemvvm.utils.Constans


sealed class NavRoute(val route: String) {
    object Start : NavRoute(Constans.Screens.START_SCREEN)
    object Main : NavRoute(Constans.Screens.MAIN_SCREEN)
    object Add : NavRoute(Constans.Screens.ADD_SCREEN)
    object Note : NavRoute(Constans.Screens.NOTE_SCREEN)
}

@Composable
fun NotesNavHost(mViewModel: MainViewModel, navController: NavHostController) {


    NavHost(navController = navController, startDestination = NavRoute.Start.route) {
        composable(NavRoute.Start.route) { StartScreen(navController, viewModel = mViewModel) }
        composable(NavRoute.Main.route) { MainScreen(navController, viewModel = mViewModel) }
        composable(NavRoute.Add.route) { AddScreen(navController, viewModel = mViewModel) }
        composable(NavRoute.Note.route + "/{${Constans.Keys.ID}}") { backStackEntry ->
            NoteScreen(
                navController,
                viewModel = mViewModel, noteId = backStackEntry.arguments?.getString(Constans.Keys.ID)
            )
        }


    }


}