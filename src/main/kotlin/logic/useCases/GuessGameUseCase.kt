package org.example.logic.useCases

import org.example.logic.RecipesRepository
import kotlin.random.Random

class GuessGameUseCase(private val repository: RecipesRepository) {

    companion object {
        const val MAX_ATTEMPTS = 3
    }

    fun getRandomRecipe(): GameResult {
        val recipes = repository.getRecipes()
        if (recipes.isEmpty()) {
            return GameResult.Error("No recipes available")
        }

        val randomRecipe = recipes[Random.nextInt(recipes.size)]
        val correctTime = randomRecipe.minutes

        return GameResult.Success(
            recipeName = randomRecipe.name,
            correctTime = correctTime
        )
    }

    fun checkGuess(guessedTime: Int, correctTime: Int): GuessResult {
        return when {
            guessedTime == correctTime -> GuessResult.CORRECT
            guessedTime < correctTime -> GuessResult.TOO_LOW
            else -> GuessResult.TOO_HIGH
        }
    }

    fun validateGuess(input: String?): GuessValidationResult {
        if (input == null) {
            return GuessValidationResult.Invalid("Input cannot be null")
        }

        return try {
            val number = input.toInt()
            if (number < 0) {
                GuessValidationResult.Invalid("Preparation time cannot be negative")
            } else {
                GuessValidationResult.Valid(number)
            }
        } catch (e: NumberFormatException) {
            GuessValidationResult.Invalid("Please enter a valid number")
        }
    }

    sealed class GameResult {
        data class Success(val recipeName: String, val correctTime: Int) : GameResult()
        data class Error(val message: String) : GameResult()
    }

    enum class GuessResult {
        CORRECT, TOO_LOW, TOO_HIGH
    }

    sealed class GuessValidationResult {
        data class Valid(val value: Int) : GuessValidationResult()
        data class Invalid(val message: String) : GuessValidationResult()
    }
}