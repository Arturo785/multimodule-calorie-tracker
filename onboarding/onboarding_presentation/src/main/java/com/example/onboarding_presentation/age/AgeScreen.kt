package com.example.onboarding_presentation.age

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.R
import com.example.core.util.UiEvent
import com.example.core_ui.LocalSpacing
import com.example.onboarding_presentation.components.ActionButton
import com.example.onboarding_presentation.components.UnitTextField
import kotlinx.coroutines.flow.collect


@Composable
fun AgeScreen(
    scaffoldState: ScaffoldState, // for the snackBar messages
    onNavigate: () -> Unit,
    viewModel: AgeViewModel = hiltViewModel()
) {
    val spacing = LocalSpacing.current
    val context = LocalContext.current // for the UI messages

    // used to collect the events
    // reacts to the UI event on the viewModel
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Navigate -> onNavigate()
                is UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message.asString(context)
                    )
                }
                else -> Unit
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(spacing.spaceLarge)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.whats_your_age),
                style = MaterialTheme.typography.h3,
                color = MaterialTheme.colors.onSecondary
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))

            UnitTextField(
                value = viewModel.age,
                onValueChange = viewModel::onAgeEnter, // the one that validates the age
                unit = stringResource(id = R.string.years)
            )
        }

        ActionButton(
            text = stringResource(id = R.string.next),
            onClick = viewModel::onNextClick,
            modifier = Modifier.align(Alignment.BottomEnd)
        )
    }
}