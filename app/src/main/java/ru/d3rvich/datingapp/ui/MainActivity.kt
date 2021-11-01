package ru.d3rvich.datingapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.d3rvich.datingapp.ui.screens.login_screen.LoginScreen
import ru.d3rvich.datingapp.ui.screens.login_screen.LoginViewModel
import ru.d3rvich.datingapp.ui.theme.DatingAppTheme

/**
 * Главное и единственное активити для всего приложения
 * */
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
                        startDestination = Screens.LoginScreen.route
                    ) {
                        composable(Screens.LoginScreen.route) {
                            val loginViewModel: LoginViewModel by viewModels()
                            LoginScreen(loginViewModel = loginViewModel)
                        }
                    }
                }
            }
        }
    }
}