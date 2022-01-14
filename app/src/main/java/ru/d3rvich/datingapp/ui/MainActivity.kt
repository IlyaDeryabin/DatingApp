package ru.d3rvich.datingapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.insets.*
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import ru.d3rvich.datingapp.ui.navigation.createExternalRouter
import ru.d3rvich.datingapp.ui.screens.main.MainScreen
import ru.d3rvich.datingapp.ui.screens.dialog.DIALOG_ID_KEY
import ru.d3rvich.datingapp.ui.screens.dialog.DialogScreen
import ru.d3rvich.datingapp.ui.screens.login_screen.LoginScreen
import ru.d3rvich.datingapp.ui.screens.login_screen.LoginViewModel
import ru.d3rvich.datingapp.ui.screens.profile_editor.ProfileEditorScreen
import ru.d3rvich.datingapp.ui.screens.profile_editor.ProfileEditorViewModel
import ru.d3rvich.datingapp.ui.screens.profile_view.ProfileViewScreen
import ru.d3rvich.datingapp.ui.screens.profile_view.ProfileViewViewModel
import ru.d3rvich.datingapp.ui.screens.settings.SettingsScreen
import ru.d3rvich.datingapp.ui.screens.sing_up_screen.SignUpScreen
import ru.d3rvich.datingapp.ui.screens.sing_up_screen.SignUpViewModel
import ru.d3rvich.datingapp.ui.theme.DatingAppTheme

/**
 * Главное и единственное активити для всего приложения
 * */
@ExperimentalMaterialApi
@ExperimentalCoilApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            val isDarkModeValue = isSystemInDarkTheme()

            var isDarkMode by remember {
                mutableStateOf(isDarkModeValue)
            }

            DatingAppTheme(isDarkMode) {
                val systemUiController = rememberSystemUiController()
                systemUiController.setStatusBarColor(
                    color = MaterialTheme.colors.primaryVariant
                )
                systemUiController.setNavigationBarColor(
                    color = MaterialTheme.colors.background,
                    darkIcons = !isDarkMode
                )
                ProvideWindowInsets(windowInsetsAnimationsEnabled = true) {
                    Surface(
                        color = MaterialTheme.colors.background,
                        modifier = Modifier
                            .statusBarsPadding()
                            .navigationBarsWithImePadding()
                    ) {
                        val navController = rememberNavController()
                        NavHost(
                            navController = navController,
                            startDestination = Screen.LoginScreen.route
                        ) {
                            composable(Screen.MainScreen.route) {
                                MainScreen(
                                    router = createExternalRouter { route, _ ->
                                        navController.navigate(route)
                                    },
                                    isDarkMode = isDarkMode,
                                    onDarkModeChanged = { mode ->
                                        isDarkMode = mode
                                    }
                                )
                            }
                            composable(Screen.LoginScreen.route) {
                                val loginViewModel: LoginViewModel by viewModels()
                                LoginScreen(
                                    navController = navController,
                                    loginViewModel = loginViewModel
                                )
                            }
                            composable(Screen.SignUpScreen.route) {
                                val signUpViewModel: SignUpViewModel by viewModels()
                                SignUpScreen(
                                    navController = navController,
                                    signUpViewModel = signUpViewModel
                                )
                            }
                            composable(Screen.DialogScreen.route + "/{$DIALOG_ID_KEY}") {
                                DialogScreen(navController = navController)
                            }
                            composable(Screen.ProfileScreen.route) {
                                val viewModel: ProfileViewViewModel by viewModels()
                                ProfileViewScreen(
                                    navController = navController,
                                    viewModel = viewModel
                                )
                            }
                            composable(Screen.EmptyProfileEditor.route) {
                                val viewModel: ProfileEditorViewModel by viewModels()
                                ProfileEditorScreen(
                                    navController = navController,
                                    viewModel = viewModel
                                )
                            }
                            composable(Screen.Settings.route) {
                                SettingsScreen(onBackButtonPressed = { navController.popBackStack() },
                                    isDarkMode = isDarkMode,
                                    onDarkModeChange = { isDarkMode = it })
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
}