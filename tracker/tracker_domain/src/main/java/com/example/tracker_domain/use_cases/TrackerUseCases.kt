package com.example.tracker_domain.use_cases

// a wrapper of all the use cases that can be used
// instead of importing all only this data class
data class TrackerUseCases(
    val trackFood: TrackFood,
    val searchFood: SearchFood,
    val getFoodsForDate: GetFoodsForDate,
    val deleteTrackedFood: DeleteTrackedFood,
    val calculateMealNutrients: CalculateMealNutrients
)