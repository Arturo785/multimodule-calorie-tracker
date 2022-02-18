package com.example.calorietracker.repository

import com.example.tracker_domain.model.TrackableFood
import com.example.tracker_domain.model.TrackedFood
import com.example.tracker_domain.repository.TrackerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import java.time.LocalDate
import kotlin.random.Random


// supposed to handle and fake all the network calls and make it quicker
class TrackerRepositoryFake : TrackerRepository {

    // handles from outside if return error
    var shouldReturnError = false

    // our simulated DB
    private val trackedFood = mutableListOf<TrackedFood>()

    // public in order to add our own results from outside
    // our simulated network result
    var searchResults = listOf<TrackableFood>()

    // the one that can be observed from outside and has our fake db data
    // share flow to be able to have multiple observers
    private val getFoodsForDateFlow =
        MutableSharedFlow<List<TrackedFood>>(replay = 1) // in case collectors are not fast enough
    // replay parameter. This indicates how many objects should be cached by the flow and delivered
    // to late subscribers. The default is 0, so nothing gets cached this way.

    override suspend fun searchFood(
        query: String,
        page: Int,
        pageSize: Int
    ): Result<List<TrackableFood>> {
        return if (shouldReturnError) {
            Result.failure(Throwable())
        } else {
            Result.success(searchResults) // the one we passed before
        }
    }

    override suspend fun insertTrackedFood(food: TrackedFood) {
        // our simulated DB
        trackedFood.add(food.copy(id = Random.nextInt()))
        getFoodsForDateFlow.emit(trackedFood)
    }

    override suspend fun deleteTrackedFood(food: TrackedFood) {
        // our simulated DB
        trackedFood.remove(food)
        getFoodsForDateFlow.emit(trackedFood)
    }

    override fun getFoodsForDate(localDate: LocalDate): Flow<List<TrackedFood>> {
        return getFoodsForDateFlow
    }
}