package com.example.calorietracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.annotation.ExperimentalCoilApi
import com.example.calorietracker.ui.theme.CalorieTrackerTheme
import com.example.core.domain.preferences.Preferences
import com.example.core.navigation.Route
import com.example.onboarding_presentation.activilty.ActivityScreen
import com.example.onboarding_presentation.age.AgeScreen
import com.example.onboarding_presentation.gender.GenderScreen
import com.example.onboarding_presentation.goal.GoalScreen
import com.example.onboarding_presentation.height.HeightScreen
import com.example.onboarding_presentation.nutrient_goal.NutrientGoalScreen
import com.example.onboarding_presentation.weight.WeightScreen
import com.example.onboarding_presentation.welcome.WelcomeScreen
import com.example.tracker_presentation.search.SearchScreen
import com.example.tracker_presentation.tracker_overview.TrackerOverviewScreen
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@ExperimentalComposeUiApi
@ExperimentalCoilApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var preferences: Preferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val shouldShowOnBoarding = preferences.loadShouldShowOnBoarding()

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
                        startDestination = if (shouldShowOnBoarding) Route.WELCOME
                        else Route.TRACKER_OVERVIEW
                    ) {
                        // each of this handle what to show depending the route passed

                        composable(Route.WELCOME) {
                            // we define the event on the composable
                            WelcomeScreen(onNextScreen = {
                                navController.navigate(Route.GENDER)
                            })
                        }

                        composable(Route.AGE) {
                            AgeScreen(
                                onNavigate = {
                                    navController.navigate(Route.HEIGHT)
                                }, scaffoldState = scaffoldState
                            )
                        }

                        composable(Route.GENDER) {
                            GenderScreen(onNavigate = {
                                navController.navigate(Route.AGE)
                            })
                        }

                        composable(Route.HEIGHT) {
                            HeightScreen(
                                scaffoldState = scaffoldState,
                                onNavigate = {
                                    navController.navigate(Route.WEIGHT)
                                }
                            )
                        }

                        composable(Route.WEIGHT) {
                            WeightScreen(
                                onNavigate = {
                                    navController.navigate(Route.ACTIVITY)
                                }, scaffoldState = scaffoldState
                            )
                        }

                        composable(Route.NUTRIENT_GOAL) {
                            NutrientGoalScreen(
                                onNavigate = {
                                    navController.navigate(Route.TRACKER_OVERVIEW)
                                }, scaffoldState = scaffoldState
                            )
                        }

                        composable(Route.ACTIVITY) {
                            ActivityScreen(onNavigate = {
                                navController.navigate(Route.GOAL)
                            })
                        }

                        composable(Route.GOAL) {
                            GoalScreen(onNavigate = {
                                navController.navigate(Route.NUTRIENT_GOAL)
                            })
                        }

                        composable(Route.TRACKER_OVERVIEW) {
                            TrackerOverviewScreen(
                                onNavigate = { mealName, day, month, year ->
                                    navController.navigate(
                                        Route.SEARCH + "/$mealName" +
                                                "/$day" +
                                                "/$month" +
                                                "/$year"
                                    )
                                }
                            )
                        }

                        composable(
                            // how arguments work with navigation compose
                            route = Route.SEARCH + "/{mealName}/{dayOfMonth}/{month}/{year}",
                            arguments = listOf(
                                navArgument("mealName") {
                                    type = NavType.StringType
                                },
                                navArgument("dayOfMonth") {
                                    type = NavType.IntType
                                },
                                navArgument("month") {
                                    type = NavType.IntType
                                },
                                navArgument("year") {
                                    type = NavType.IntType
                                },
                            )
                        ) {
                            val mealName = it.arguments?.getString("mealName")!!
                            val dayOfMonth = it.arguments?.getInt("dayOfMonth")!!
                            val month = it.arguments?.getInt("month")!!
                            val year = it.arguments?.getInt("year")!!

                            SearchScreen(
                                scaffoldState = scaffoldState,
                                mealName = mealName,
                                dayOfMonth = dayOfMonth,
                                month = month,
                                year = year,
                                onNavigateUp = {
                                    navController.navigateUp()
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}