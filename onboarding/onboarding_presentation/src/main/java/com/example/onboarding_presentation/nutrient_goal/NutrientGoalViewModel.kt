package com.example.onboarding_presentation.nutrient_goal

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.preferences.Preferences
import com.example.core.domain.use_case.FilterOutDigits
import com.example.core.navigation.Route
import com.example.core.util.UiEvent
import com.example.onboarding_domain.use_case.ValidateNutrients
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch


@HiltViewModel
class NutrientGoalViewModel @Inject constructor(
    private val preferences: Preferences,
    private val filterOutDigits: FilterOutDigits,
    private val validateNutrients: ValidateNutrients
) : ViewModel() {

    // handles the 3 available properties
    var state by mutableStateOf(NutrientGoalState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()


    fun onEvent(event: NutrientGoalEvent) {
        // manages the changes of goals
        // uses copy because we only want to change one single field
        when (event) {
            is NutrientGoalEvent.OnCarbRatioEnter -> {
                state = state.copy(
                    carbsRatio = filterOutDigits(event.ratio)
                )
            }
            is NutrientGoalEvent.OnProteinRatioEnter -> {
                state = state.copy(
                    proteinRatio = filterOutDigits(event.ratio)
                )
            }
            is NutrientGoalEvent.OnFatRatioEnter -> {
                state = state.copy(
                    fatRatio = filterOutDigits(event.ratio)
                )
            }
            is NutrientGoalEvent.OnNextClick -> {
                // returns a success value or an error
                val result = validateNutrients(
                    carbsRatioText = state.carbsRatio,
                    proteinRatioText = state.proteinRatio,
                    fatRatioText = state.fatRatio
                )

                when (result) {
                    is ValidateNutrients.Result.Success -> {
                        preferences.saveCarbRatio(result.carbsRatio)
                        preferences.saveProteinRatio(result.proteinRatio)
                        preferences.saveFatRatio(result.fatRatio)

                        viewModelScope.launch {
                            _uiEvent.send(UiEvent.Navigate)
                        }
                    }
                    is ValidateNutrients.Result.Error -> {
                        viewModelScope.launch {
                            _uiEvent.send(UiEvent.ShowSnackbar(result.message))
                        }
                    }
                }
            }
        }
    }
}