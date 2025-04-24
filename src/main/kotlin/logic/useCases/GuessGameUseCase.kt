package org.example.logic.useCases

import org.example.logic.RecipesRepository
import org.example.model.GuessAttemptState

class GuessGameUseCase(private val repository: RecipesRepository ) {


     fun submit(userGuess: Int, preparationTime: Int, attempts: Int): GuessAttemptState {
        return if (userGuess == preparationTime)
            GuessAttemptState.CORRECT
        else if (attempts == MAX_ATTEMPTS)
            throw  Exception("Game Over! The correct preparation time is $preparationTime minutes")
        else if (userGuess < preparationTime)
            GuessAttemptState.TOO_LOW
        else
            GuessAttemptState.TOO_HIGH
    }


    companion object {
        const val MAX_ATTEMPTS = 3
    }
}

