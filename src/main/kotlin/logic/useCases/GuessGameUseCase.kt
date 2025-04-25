package org.example.logic.useCases

import org.example.model.GuessAttemptResult
import org.example.model.RecipeGuessGame

class GuessGameUseCase(
    private val randomRecipeUseCase: RandomRecipeUseCase
) {

    lateinit var recipeGuessGame: RecipeGuessGame

    init {
        startGame()
    }

    fun processGuess(userGuess: Int): GuessAttemptResult {

        val newAttemptCount = recipeGuessGame.attemptCount + 1
        val result = evaluateGuess(userGuess, recipeGuessGame.recipe.minutes, newAttemptCount)

        recipeGuessGame = when (result) {
            is GuessAttemptResult.Correct, is GuessAttemptResult.GameOver -> {
                recipeGuessGame.copy(attemptCount = newAttemptCount, isFinished = true)
            }

            else -> recipeGuessGame.copy(attemptCount = newAttemptCount)
        }

        return result
    }

    fun getGameState(): RecipeGuessGame {
        return recipeGuessGame
    }

    private fun startGame() {
        recipeGuessGame = RecipeGuessGame(recipe = randomRecipeUseCase.getRandomRecipe())
    }


    private fun evaluateGuess(userGuess: Int, preparationTime: Int, attemptCount: Int): GuessAttemptResult = when {
        userGuess == preparationTime -> GuessAttemptResult.Correct
        attemptCount >= MAX_ATTEMPTS -> GuessAttemptResult.GameOver(preparationTime)
        userGuess < preparationTime -> GuessAttemptResult.TooLow
        else -> GuessAttemptResult.TooHigh
    }

    private companion object {
        const val MAX_ATTEMPTS = 3
    }
}