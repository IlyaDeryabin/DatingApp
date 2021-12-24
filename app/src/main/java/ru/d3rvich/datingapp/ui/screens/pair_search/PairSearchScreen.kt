package ru.d3rvich.datingapp.ui.screens.pair_search

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import ru.d3rvich.datingapp.R
import ru.d3rvich.datingapp.ui.navigation.Router
import ru.d3rvich.datingapp.ui.screens.pair_search.model.PairSearchEvent
import ru.d3rvich.datingapp.ui.screens.pair_search.model.PairSearchViewState
import ru.d3rvich.datingapp.ui.screens.pair_search.views.PairSearchViewDisplay
import ru.d3rvich.datingapp.ui.screens.pair_search.views.PairSearchViewError
import ru.d3rvich.datingapp.ui.screens.pair_search.views.PairSearchViewLoading

@Composable
fun PairSearchScreen(
    router: Router,
    openDrawer: () -> Unit,
    viewModel: PairSearchViewModel = hiltViewModel()
) {
    Scaffold(topBar = {
        TopAppBar(navigationIcon = {
            IconButton(onClick = openDrawer) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = stringResource(id = R.string.turn_back)
                )
            }
        }, title = {})
    }) {
        when (val viewState = viewModel.viewState.value) {
            PairSearchViewState.Idle -> {
            }
            PairSearchViewState.Loading -> {
                PairSearchViewLoading()
            }
            is PairSearchViewState.CandidateDisplay -> {
                PairSearchViewDisplay(currentCandidate = viewState.candidate,
                    onLike = { viewModel.obtainEvent(PairSearchEvent.OnLike) },
                    onDislike = { viewModel.obtainEvent(PairSearchEvent.OnDislike) })
            }
            PairSearchViewState.Error -> {
                PairSearchViewError(onReloadButtonClicked = {
                    viewModel.obtainEvent(PairSearchEvent.ReloadContent)
                })
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.obtainEvent(PairSearchEvent.EnterScreen)
    }
}