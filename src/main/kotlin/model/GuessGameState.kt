package org.example.model

data class GuessGameState(
    val recipe: Recipe,
    val attemptCount: Int = 0,
    val isFinished: Boolean = false
)