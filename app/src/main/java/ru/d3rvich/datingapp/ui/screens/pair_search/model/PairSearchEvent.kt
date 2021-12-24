package ru.d3rvich.datingapp.ui.screens.pair_search.model

sealed class PairSearchEvent {
    object EnterScreen : PairSearchEvent()
    object ReloadContent : PairSearchEvent()
    object OnLike : PairSearchEvent()
    object OnDislike : PairSearchEvent()
}
