package com.example.tracker_presentation.tracker_overview

import com.example.tracker_domain.model.TrackedFood

// represents all the things that can be triggered by the user and alter the UI

sealed class TrackerOverviewEvent {
    object OnNextDayClick : TrackerOverviewEvent()
    object OnPreviousDayClick : TrackerOverviewEvent()
    data class OnToggleMealClick(val meal: Meal) : TrackerOverviewEvent()
    data class OnDeleteTrackedFoodClick(val trackedFood: TrackedFood) : TrackerOverviewEvent()
}