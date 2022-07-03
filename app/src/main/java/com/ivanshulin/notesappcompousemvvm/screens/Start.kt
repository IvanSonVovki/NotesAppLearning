package com.ivanshulin.notesappcompousemvvm.screens

import android.app.Application
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ivanshulin.notesappcompousemvvm.MainViewModel
import com.ivanshulin.notesappcompousemvvm.MainViewModelFactory
import com.ivanshulin.notesappcompousemvvm.navigation.NavRoute
import com.ivanshulin.notesappcompousemvvm.ui.theme.NotesAppCompouseMVVMTheme
import com.ivanshulin.notesappcompousemvvm.utils.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun StartScreen(navController: NavHostController, viewModel: MainViewModel) {

    val bottomSheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val coroutineScope = rememberCoroutineScope()
    var login by remember { mutableStateOf(Constans.Keys.EMPTY) }
    var password by remember { mutableStateOf(Constans.Keys.EMPTY) }

    ModalBottomSheetLayout(
        sheetState = bottomSheetState,
        sheetElevation = 5.dp,
        sheetShape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
        sheetContent = {

            Surface() {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 32.dp)
                ) {

                    Text(
                        text = Constans.Keys.LOG_IN,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )

                    OutlinedTextField(
                        value = login,
                        onValueChange = { login = it },
                        label = { Text(text = Constans.Keys.LOGIN_TEXT) },
                        isError = login.isEmpty()
                    )
                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text(text = Constans.Keys.PASSWORD_TEXT) },
                        isError = password.isEmpty()
                    )
                    Button(
                        modifier = Modifier.padding(top = 16.dp),
                        onClick = {
                            LOGIN = login
                            PASSWORD = password
                            viewModel.initDataBase(TYPE_FIREBASE) {
                                DB_TYPE = TYPE_FIREBASE
                                navController.navigate(NavRoute.Main.route)
                            }
                        },
                        enabled = login.isNotEmpty() && password.isNotEmpty()
                    ) {
                        Text(text = Constans.Keys.SIGN_IN)
                    }

                }

            }
        }
    ) {
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
                        viewModel.initDataBase(TYPE_ROOM) {
                            DB_TYPE = TYPE_ROOM
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
                        coroutineScope.launch {
                            bottomSheetState.show()

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