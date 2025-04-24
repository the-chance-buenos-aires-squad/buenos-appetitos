package org.example.model

sealed class GuessAttemptState(val message: String) {
    data object Correct : GuessAttemptState("Congratulations! You guessed the correct preparation time.")
    data object TooLow : GuessAttemptState("Your guess is too low.")
    data object TooHigh : GuessAttemptState("Your guess is too high.")
    data class GameOver(val correctTime: Int) : GuessAttemptState("Game Over! The correct preparation time is $correctTime minutes")
}