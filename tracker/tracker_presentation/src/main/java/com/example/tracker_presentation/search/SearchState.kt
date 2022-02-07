package com.example.tracker_presentation.search

import com.example.tracker_domain.model.TrackableFood


// the different states the screen can be in
data class SearchState(
    val query: String = "",
    val isHintVisible: Boolean = false,
    val isSearching: Boolean = false,
    // this one is an extension of the trackable food but with fields necessary to the UI
    val trackableFood: List<TrackableFoodUiState> = emptyList()
)