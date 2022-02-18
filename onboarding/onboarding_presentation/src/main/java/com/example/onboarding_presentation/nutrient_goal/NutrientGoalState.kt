package com.example.onboarding_presentation.nutrient_goal


// the default ones, may be changed by the user
// used to only observe one object instead of 3 different fields in the viewModel
data class NutrientGoalState(
    val carbsRatio: String = "40",
    val proteinRatio: String = "30",
    val fatRatio: String = "30"
)