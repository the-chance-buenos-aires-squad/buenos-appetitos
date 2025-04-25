package org.example.model

data class RecipeGuessGame(
    val recipe: Recipe,
    val attemptCount: Int = 0,
    val isFinished: Boolean = false
)