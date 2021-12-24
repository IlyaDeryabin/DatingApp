package ru.d3rvich.datingapp.ui.screens.pair_search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.d3rvich.datingapp.domain.interactor.DatingInteractor
import ru.d3rvich.datingapp.ui.base.EventHandler
import ru.d3rvich.datingapp.ui.screens.pair_search.model.PairSearchEvent
import ru.d3rvich.datingapp.ui.screens.pair_search.model.PairSearchViewState
import javax.inject.Inject

@HiltViewModel
class PairSearchViewModel @Inject constructor(private val interactor: DatingInteractor) :
    ViewModel(), EventHandler<PairSearchEvent> {
    private val _viewState = mutableStateOf<PairSearchViewState>(PairSearchViewState.Idle)
    val viewState: State<PairSearchViewState>
        get() = _viewState

    override fun obtainEvent(event: PairSearchEvent) {
        when (val currentState = _viewState.value) {
            is PairSearchViewState.CandidateDisplay -> {
                reduce(event, currentState)
            }
            is PairSearchViewState.Error -> {
                reduce(event, currentState)
            }
            is PairSearchViewState.Idle -> {
                reduce(event, currentState)
            }
            is PairSearchViewState.Loading -> {
                reduce(event, currentState)
            }
        }
    }

    private fun reduce(event: PairSearchEvent, state: PairSearchViewState.Idle) {
        when (event) {
            PairSearchEvent.EnterScreen -> {
                loadContent()
            }
            else -> {
                error("Unexpected $event for $state")
            }
        }
    }

    private fun reduce(event: PairSearchEvent, state: PairSearchViewState.Loading) {
        when (event) {
            PairSearchEvent.EnterScreen -> return
            else -> {
                error("Unexpected $event for $state")
            }
        }
    }

    private fun reduce(event: PairSearchEvent, state: PairSearchViewState.CandidateDisplay) {
        when (event) {
            PairSearchEvent.EnterScreen -> return
            PairSearchEvent.OnDislike -> {
                // TODO: 24.12.2021 On dislike event behavior
            }
            PairSearchEvent.OnLike -> {
                // TODO: 24.12.2021 On like event behavior
            }
            else -> {
                error("Unexpected $event for $state")
            }
        }
    }

    private fun reduce(event: PairSearchEvent, state: PairSearchViewState.Error) {
        when (event) {
            PairSearchEvent.EnterScreen -> return
            PairSearchEvent.ReloadContent -> {
                loadContent()
            }
            else -> {
                error("Unexpected $event for $state")
            }
        }
    }

    private fun loadContent() {
        viewModelScope.launch {
            try {
                _viewState.value = PairSearchViewState.Loading
                val candidate = interactor.getUserProfileBy("")
                _viewState.value = PairSearchViewState.CandidateDisplay(candidate)
            } catch (e: Exception) {
                _viewState.value = PairSearchViewState.Error
            }
        }
    }
}