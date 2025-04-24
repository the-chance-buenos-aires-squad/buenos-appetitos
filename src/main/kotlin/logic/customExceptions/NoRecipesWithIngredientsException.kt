package org.example.logic.customExceptions

class NoRecipesWithIngredientsException (
    message: String = "No Ingredients were found.",
) : IllegalStateException(message)