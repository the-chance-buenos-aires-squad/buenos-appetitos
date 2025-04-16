package org.example.logic.useCases

import org.example.logic.RecipesRepository
import org.example.model.Recipe

class GuessGameUseCase(private val repository: RecipesRepository) {

    private var attemptsLeft = MAX_ATTEMPTS
    private var currentRecipe: Recipe? = null
    private var currentCorrectTime: Int? = null

    fun getRandomRecipe(): GameResult {
        val recipes = repository.getRecipes()
        if (recipes.isEmpty()) {
            return GameResult.Error("No recipes available")
        }

        val randomRecipe = recipes.random()
        currentRecipe = randomRecipe
        currentCorrectTime = randomRecipe.minutes

        return GameResult.Success(
            recipeName = randomRecipe.name,
            correctTime = randomRecipe.minutes
        )
    }

    fun handleGuess(input: String?): GuessAttemptResult {
        if (!thereIsAttemptsLeft()) {
            return GuessAttemptResult.GameOver(currentCorrectTime ?: 0)
        }

        try {
            val validationResult = validateGuess(input)
            val correctTime = currentCorrectTime ?: throw IllegalStateException("Game not properly initialized")

            when (validationResult) {
                is GuessValidationResult.Valid -> {
                    when (checkGuess(validationResult.value, correctTime)) {
                        GuessResult.CORRECT -> return GuessAttemptResult.Correct(correctTime)
                        GuessResult.TOO_LOW -> {
                            decrementAttempts()
                            return GuessAttemptResult.TooLow(attemptsLeft)
                        }
                        GuessResult.TOO_HIGH -> {
                            decrementAttempts()
                            return GuessAttemptResult.TooHigh(attemptsLeft)
                        }
                    }
                }
            }
        } catch (e: IllegalArgumentException) {
            return GuessAttemptResult.InvalidInput(e.message ?: "Invalid input", attemptsLeft)
        }
    }

    private fun checkGuess(guessedTime: Int, correctTime: Int): GuessResult {
        return when {
            guessedTime == correctTime -> GuessResult.CORRECT
            guessedTime < correctTime -> GuessResult.TOO_LOW
            else -> GuessResult.TOO_HIGH
        }
    }

    private fun validateGuess(input: String?): GuessValidationResult {
        if (input == null) {
            throw IllegalArgumentException("Input cannot be null")
        }

        return try {
            val number = input.toInt()
            if (number < 0) {
                throw IllegalArgumentException("Preparation time cannot be negative")
            } else {
                GuessValidationResult.Valid(number)
            }
        } catch (e: NumberFormatException) {
            throw IllegalArgumentException("Please enter a valid number")
        }
    }

    fun getAttemptsLeft(): Int = attemptsLeft

    private fun decrementAttempts() {
        attemptsLeft--
    }

    fun thereIsAttemptsLeft(): Boolean = attemptsLeft > 0

    fun resetGame() {
        attemptsLeft = MAX_ATTEMPTS
        currentRecipe = null
        currentCorrectTime = null
    }

    sealed class GameResult {
        data class Success(val recipeName: String, val correctTime: Int) : GameResult()
        data class Error(val message: String) : GameResult()
    }

    private enum class GuessResult {
        CORRECT, TOO_LOW, TOO_HIGH
    }

    private sealed class GuessValidationResult {
        data class Valid(val value: Int) : GuessValidationResult()
    }

    sealed class GuessAttemptResult {
        data class Correct(val correctTime: Int) : GuessAttemptResult()
        data class TooLow(val attemptsLeft: Int) : GuessAttemptResult()
        data class TooHigh(val attemptsLeft: Int) : GuessAttemptResult()
        data class InvalidInput(val message: String, val attemptsLeft: Int) : GuessAttemptResult()
        data class GameOver(val correctTime: Int) : GuessAttemptResult()
    }

    companion object {
        const val MAX_ATTEMPTS = 3
    }
}