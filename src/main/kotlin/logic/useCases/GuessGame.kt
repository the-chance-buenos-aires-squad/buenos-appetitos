package org.example.logic.useCases

import org.example.logic.RecipesRepository
import kotlin.random.Random

class GuessGame(private val repository: RecipesRepository) {

    fun playGuessGame(): GameResult {
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

    sealed class GameResult {
        data class Success(val recipeName: String, val correctTime: Int) : GameResult()
        data class Error(val message: String) : GameResult()
    }

    enum class GuessResult {
        CORRECT, TOO_LOW, TOO_HIGH
    }
}