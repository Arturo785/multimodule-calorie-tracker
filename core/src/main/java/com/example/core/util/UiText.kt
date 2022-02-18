package com.example.core.util

import android.content.Context
import androidx.annotation.StringRes


// helper text class that allow us to access string resources from other non context available parts
sealed class UiText {
    data class DynamicString(val text: String) : UiText()
    data class StringResource(val resId: Int) : UiText()

    fun asString(context: Context): String {
        return when (this) {
            is DynamicString -> text
            is StringResource -> context.getString(resId)
        }
    }
}