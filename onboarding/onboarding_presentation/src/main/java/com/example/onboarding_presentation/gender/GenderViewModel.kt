package com.example.onboarding_presentation.gender

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.model.Gender
import com.example.core.domain.preferences.Preferences
import com.example.core.navigation.Route
import com.example.core.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class GenderViewModel @Inject constructor(
    private val preferences: Preferences
) : ViewModel() {

    // used by the composables
    var selectedGender by mutableStateOf<Gender>(Gender.Male)
        private set

    // the ones that should only happen one time
    // channels are hot and may receive multiple values
    // https://elizarov.medium.com/shared-flows-broadcast-channels-899b675e805c
    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow() // the one observed in the UI


    fun onGenderClick(gender: Gender) {
        selectedGender = gender
    }

    // saves in shared preferences and sends the signal of change of screen
    fun onNextClick() {
        viewModelScope.launch {
            preferences.saveGender(selectedGender)
            // sets the event but does not navigate
            _uiEvent.send(UiEvent.Navigate)
        }
    }
}