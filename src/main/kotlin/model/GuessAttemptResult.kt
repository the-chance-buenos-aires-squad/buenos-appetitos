package org.example.model

sealed class GuessAttemptResult(val message: String) {
    data object Correct : GuessAttemptResult("Congratulations! You guessed the correct preparation time.")
    data object TooLow : GuessAttemptResult("Your guess is too low.")
    data object TooHigh : GuessAttemptResult("Your guess is too high.")
    data class GameOver(val correctTime: Int) : GuessAttemptResult("Game Over! The correct preparation time is $correctTime minutes")
}