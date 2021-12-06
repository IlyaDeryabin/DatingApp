package ru.d3rvich.datingapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import dagger.hilt.android.AndroidEntryPoint
import ru.d3rvich.datingapp.ui.screens.dialog.DIALOG_ID_KEY
import ru.d3rvich.datingapp.ui.screens.dialog.DialogScreen
import ru.d3rvich.datingapp.ui.screens.dialog.DialogViewModel
import ru.d3rvich.datingapp.ui.screens.dialog_list.DialogListScreen
import ru.d3rvich.datingapp.ui.screens.dialog_list.DialogListViewModel
import ru.d3rvich.datingapp.ui.screens.login_screen.LoginScreen
import ru.d3rvich.datingapp.ui.screens.login_screen.LoginViewModel
import ru.d3rvich.datingapp.ui.screens.profile_editor.ProfileEditorScreen
import ru.d3rvich.datingapp.ui.screens.profile_editor.ProfileEditorViewModel
import ru.d3rvich.datingapp.ui.screens.sing_up_screen.SignUpScreen
import ru.d3rvich.datingapp.ui.screens.sing_up_screen.SignUpViewModel
import ru.d3rvich.datingapp.ui.theme.DatingAppTheme

/**
 * Главное и единственное активити для всего приложения
 * */
@ExperimentalComposeUiApi
@ExperimentalCoilApi
@ExperimentalAnimationApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DatingAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screens.SignUpScreen.route // Change start destination on finish
                    ) {
                        composable(Screens.LoginScreen.route) {
                            val loginViewModel: LoginViewModel by viewModels()
                            LoginScreen(
                                navController = navController,
                                loginViewModel = loginViewModel
                            )
                        }
                        composable(Screens.SignUpScreen.route) {
                            val signUpViewModel: SignUpViewModel by viewModels()
                            SignUpScreen(
                                navController = navController,
                                signUpViewModel = signUpViewModel
                            )
                        }
                        composable(Screens.DialogListScreen.route) {
                            val dialogListViewModel: DialogListViewModel by viewModels()
                            DialogListScreen(
                                navController = navController,
                                viewModel = dialogListViewModel
                            )
                        }
                        composable(Screens.DialogScreen.route + "/{$DIALOG_ID_KEY}") {
                            DialogScreen(navController = navController)
                        }
                        composable(Screens.EmptyProfileEditor.route) {
                            val viewModel: ProfileEditorViewModel by viewModels()
                            ProfileEditorScreen(
                                navController = navController,
                                viewModel = viewModel
                            )
                        }
                        composable("empty") {
                            Text("Empty")
                        }
                    }
                }
            }
        }
    }
}