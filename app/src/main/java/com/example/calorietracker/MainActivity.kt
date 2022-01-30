package com.example.calorietracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.calorietracker.navigation.navigate
import com.example.calorietracker.ui.theme.CalorieTrackerTheme
import com.example.core.navigation.Route
import com.example.core.util.UiEvent
import com.example.onboarding_presentation.activilty.ActivityScreen
import com.example.onboarding_presentation.age.AgeScreen
import com.example.onboarding_presentation.gender.GenderScreen
import com.example.onboarding_presentation.goal.GoalScreen
import com.example.onboarding_presentation.height.HeightScreen
import com.example.onboarding_presentation.nutrient_goal.NutrientGoalScreen
import com.example.onboarding_presentation.weight.WeightScreen
import com.example.onboarding_presentation.welcome.WelcomeScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalorieTrackerTheme {

                val navController = rememberNavController()
                val scaffoldState = rememberScaffoldState() // to manage UI stuff

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    scaffoldState = scaffoldState
                ) {
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
                            AgeScreen(
                                onNavigate = {
                                    navController.navigate(it)
                                }, scaffoldState = scaffoldState
                            )
                        }

                        composable(Route.GENDER) {
                            GenderScreen(onNavigate = {
                                navController.navigate(it)
                            })
                        }

                        composable(Route.HEIGHT) {
                            HeightScreen(
                                scaffoldState = scaffoldState,
                                onNavigate = navController::navigate
                            )
                        }

                        composable(Route.WEIGHT) {
                            WeightScreen(
                                onNavigate = {
                                    navController.navigate(it)
                                }, scaffoldState = scaffoldState
                            )
                        }

                        composable(Route.NUTRIENT_GOAL) {
                            NutrientGoalScreen(
                                onNavigate = {
                                    navController.navigate(it)
                                }, scaffoldState = scaffoldState
                            )
                        }

                        composable(Route.ACTIVITY) {
                            ActivityScreen(onNavigate = {
                                navController.navigate(it)
                            })
                        }

                        composable(Route.GOAL) {
                            GoalScreen(onNavigate = {
                                navController.navigate(it)
                            })
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
}