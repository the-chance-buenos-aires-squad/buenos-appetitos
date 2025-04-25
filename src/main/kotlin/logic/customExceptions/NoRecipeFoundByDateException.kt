package org.example.logic.customExceptions

import java.time.LocalDate

class NoRecipeFoundByDateException(
    private val date: LocalDate?  = null,
    message: String = "No recipe found for date $date"
) : RuntimeException(message)