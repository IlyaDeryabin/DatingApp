package ru.d3rvich.datingapp.ui.screens.pair_search.model

import ru.d3rvich.datingapp.domain.entity.ProfileEntity

sealed class PairSearchViewState {
    object Idle : PairSearchViewState()
    object Loading : PairSearchViewState()
    object Error : PairSearchViewState()
    class CandidateDisplay(val candidate: ProfileEntity) : PairSearchViewState()
}
