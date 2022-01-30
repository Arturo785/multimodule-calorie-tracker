package com.example.core.domain.use_case


// may seem unnecessary but in clean architecture the viewModel should not contain
// business logic

// and should be accessible for all the modules in this case to be reused
class FilterOutDigits {

    operator fun invoke(text: String): String {
        // returns only the digits from a string
        return text.filter { it.isDigit() }
    }
}