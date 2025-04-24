package org.example.logic.useCases

import org.example.model.GuessGameState
import org.example.model.GuessAttemptState

class GuessGameUseCase(
    private val randomRecipeUseCase: RandomRecipeUseCase
) {

    fun startGame(): GuessGameState {
      return GuessGameState(recipe = randomRecipeUseCase.getRandomRecipe())
    }

    fun processGuess(state: GuessGameState, userGuess: Int?): Pair<GuessGameState, GuessAttemptState?> {
        if (userGuess == null) {
            return state to null
        }

        val newAttemptCount = state.attemptCount + 1
        val result = evaluateGuess(userGuess, state.recipe.minutes, newAttemptCount)

        val newState = when (result) {
            is GuessAttemptState.Correct, is GuessAttemptState.GameOver -> {
                state.copy(attemptCount = newAttemptCount, isFinished = true)
            }

            else -> state.copy(attemptCount = newAttemptCount)
        }

        return newState to result
    }

    private fun evaluateGuess(userGuess: Int, preparationTime: Int, attemptCount: Int): GuessAttemptState =
        when {
            userGuess == preparationTime -> GuessAttemptState.Correct
            attemptCount >= MAX_ATTEMPTS -> GuessAttemptState.GameOver(preparationTime)
            userGuess < preparationTime -> GuessAttemptState.TooLow
            else -> GuessAttemptState.TooHigh
        }

    companion object {
        const val MAX_ATTEMPTS = 3
    }
}