package com.example.calorietracker.navigation

import androidx.navigation.NavController
import com.example.core.util.UiEvent


// helps us to go to passed route
fun NavController.navigate(event: UiEvent.Navigate) {
    this.navigate(event.route)
}