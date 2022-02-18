package com.example.onboarding_domain.di

import com.example.onboarding_domain.use_case.ValidateNutrients
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

// this is a module that only is part of the onBoarding module and
// should not be in the app module which contains dependencies for the whole app
@Module
@InstallIn(ViewModelComponent::class) // because is only used in a viewModel
object OnboardingDomainModule {

    @Provides
    @ViewModelScoped // because is only used in a viewModel
    fun provideValidateNutrientsUseCase(): ValidateNutrients {
        return ValidateNutrients()
    }
}