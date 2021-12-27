package ru.d3rvich.datingapp.ui.screens.main

import androidx.activity.compose.BackHandler
import androidx.annotation.StringRes
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import kotlinx.coroutines.launch
import ru.d3rvich.datingapp.R
import ru.d3rvich.datingapp.ui.DrawerScreen
import ru.d3rvich.datingapp.ui.Screen
import ru.d3rvich.datingapp.ui.navigation.Router
import ru.d3rvich.datingapp.ui.screens.dialog_list.DialogListScreen
import ru.d3rvich.datingapp.ui.screens.main.models.MainScreenState
import ru.d3rvich.datingapp.ui.screens.main.views.Drawer
import ru.d3rvich.datingapp.ui.screens.pair_search.PairSearchScreen

@ExperimentalAnimationApi
@ExperimentalCoilApi
@Composable
fun MainScreen(router: Router, isDarkMode: Boolean, onDarkModeChanged: (Boolean) -> Unit) {
    val viewModel: MainViewModel = hiltViewModel()
    val navController = rememberNavController()

    val coroutineScope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val openDrawer: () -> Unit = {
        coroutineScope.launch {
            drawerState.open()
        }
    }

    BackHandler(drawerState.isOpen) {
        coroutineScope.launch {
            drawerState.close()
        }
    }

    when (val state = viewModel.state.collectAsState().value) {
        is MainScreenState.Loading -> {
            OnLoading()
        }
        is MainScreenState.Error -> {
            OnError(errorMessageResId = state.messageResId) {
                viewModel.loadingUserProfile()
            }
        }
        // Если вынести блок ниже в отдельную функцию, замучаюсь протягивать аргументы
        is MainScreenState.Loaded -> {
            ModalDrawer(
                modifier = Modifier.fillMaxSize(),
                drawerState = drawerState,
                drawerContent = {
                    Drawer(
                        onDestinationClicked = { destination ->
                            coroutineScope.launch {
                                drawerState.close()
                            }
                            if (destination == Screen.Settings.route) {
                                router.routeTo(destination)
                            } else {
                                navController.navigate(destination) {
                                    popUpTo(navController.graph.startDestinationId)
                                    launchSingleTop = true
                                }
                            }
                        },
                        profile = state.profile,
                        onUserProfileClicked = {
                            coroutineScope.launch {
                                drawerState.close()
                            }
                            router.routeTo(Screen.ProfileScreen.route)
                        },
                        isDarkMode = isDarkMode,
                        onDarkModeChange = onDarkModeChanged,
                        onSignOffButtonClicked = {
                            router.routeTo(Screen.LoginScreen.route)
                        }
                    )
                },
                gesturesEnabled = true
            ) {
                NavHost(
                    navController = navController,
                    startDestination = DrawerScreen.DialogListScreen.route
                ) {
                    composable(DrawerScreen.DialogListScreen.route) {
                        DialogListScreen(router = router, openDrawer = openDrawer)
                    }
                    composable(DrawerScreen.PairSearchScreen.route) {
                        PairSearchScreen(openDrawer = openDrawer, router = router)
                    }
                }
            }
        }
    }
}

@Composable
private fun OnLoading() {
    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        TopAppBar(title = {
            Text(text = stringResource(id = R.string.app_name))
        })
    }) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }
}

@Composable
private fun OnError(@StringRes errorMessageResId: Int, onReloadButtonClicked: () -> Unit) {
    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        TopAppBar(title = {
            Text(text = stringResource(id = R.string.app_name))
        })
    }) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(text = stringResource(id = errorMessageResId))
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = onReloadButtonClicked) {
                    Text(text = stringResource(id = R.string.try_again))
                }
            }
        }
    }
}