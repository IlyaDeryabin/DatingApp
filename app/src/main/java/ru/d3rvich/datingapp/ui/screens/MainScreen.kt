package ru.d3rvich.datingapp.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import kotlinx.coroutines.launch
import ru.d3rvich.datingapp.ui.DrawerScreen
import ru.d3rvich.datingapp.ui.Screen
import ru.d3rvich.datingapp.ui.navigation.Router
import ru.d3rvich.datingapp.ui.screens.dialog_list.DialogListScreen

@ExperimentalAnimationApi
@ExperimentalCoilApi
@Composable
fun MainScreen(router: Router) {
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

    ModalDrawer(
        modifier = Modifier.fillMaxSize(),
        drawerState = drawerState,
        drawerContent = {
            Drawer(onItemClick = { destination ->
                coroutineScope.launch {
                    drawerState.close()
                }
                navController.navigate(destination.route) {
                    popUpTo(navController.graph.startDestinationId)
                    launchSingleTop = true
                }
            })
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
                Text(text = "Pair Search")
            }
            composable(DrawerScreen.ProfileScreen.route) {
                Column {
                    Text(text = "Profile view")
                    Button(onClick = { router.routeTo(Screen.EmptyProfileEditor.route) }) {
                        Text(text = "Edit profile")
                    }
                }
            }
        }
    }
}

@Composable
private fun Drawer(onItemClick: (destination: DrawerScreen) -> Unit) {
    val screens = listOf(
        DrawerScreen.DialogListScreen,
        DrawerScreen.PairSearchScreen,
        DrawerScreen.ProfileScreen
    )
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(top = 40.dp, start = 20.dp)
    ) {
        screens.forEach { screen ->
            TextButton(onClick = { onItemClick(screen) }) {
                Text(text = screen.route)
            }
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}