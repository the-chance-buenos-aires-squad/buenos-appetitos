package org.example.presentation.customExceptions

class NoRecipeFoundException(
    private val date: String = "",
    message: String = "No recipe found for date $date"
) : RuntimeException(message)