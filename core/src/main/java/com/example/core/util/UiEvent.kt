package com.example.core.util


// manages what happens or can happen in the UI
sealed class UiEvent {
    object Navigate : UiEvent()
    object NavigateUp : UiEvent()
    data class ShowSnackbar(val message: UiText) : UiEvent()
}