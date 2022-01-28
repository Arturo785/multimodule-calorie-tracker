package com.example.core.util


// manages what happens or can happen in the UI
sealed class UiEvent {
    data class Navigate(val route: String) : UiEvent()
    object NavigateUp : UiEvent()
}