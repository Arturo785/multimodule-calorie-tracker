package com.example.calorietracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.calorietracker.navigation.navigate
import com.example.calorietracker.ui.theme.CalorieTrackerTheme
import com.example.core.navigation.Route
import com.example.core.util.UiEvent
import com.example.onboarding_presentation.welcome.WelcomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalorieTrackerTheme {

                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = Route.WELCOME
                ) {
                    // each of this handle what to show depending the route passed

                    composable(Route.WELCOME) {
                        // we define the event on the composable
                        WelcomeScreen(onNavigate = {
                            navController.navigate(it)
                        })
                    }

                    composable(Route.AGE) {

                    }

                    composable(Route.GENDER) {

                    }

                    composable(Route.HEIGHT) {

                    }

                    composable(Route.WEIGHT) {

                    }

                    composable(Route.NUTRIENT_GOAL) {

                    }

                    composable(Route.ACTIVITY) {

                    }

                    composable(Route.GOAL) {

                    }

                    composable(Route.TRACKER_OVERVIEW) {

                    }

                    composable(Route.SEARCH) {

                    }
                }
            }
        }
    }
}