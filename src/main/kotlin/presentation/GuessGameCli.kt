package org.example.presentation

import org.example.logic.useCases.GuessGameUseCase
import org.example.model.RecipeGuessGame

class GuessGameCli(
    private val guessGameUseCase: GuessGameUseCase
) {

    fun start() {
        val gameRecipe = guessGameUseCase.getGameState()
        try {
            println("The Recipe is \"${gameRecipe.recipe.name}\"")
            runGameLoop(gameRecipe)
        } catch (e: Exception) {
            println("Error on the game start: ${e.message}")
        }
    }

    private fun runGameLoop(gameState: RecipeGuessGame) {
        if (gameState.isFinished) {
            return
        }
        val userGuess = getUserGuess()
        val result = guessGameUseCase.processGuess(userGuess)
        println(result.message)
        val currentState = guessGameUseCase.getGameState()
        runGameLoop(currentState)
    }

    private fun getUserGuess(): Int {
        print("Guess the preparation time (in minutes): ")
        val userGuess = readln().toIntOrNull()
        if (userGuess == null || userGuess < 0) {
            println("Invalid input. Please enter a number.")
        }
        return userGuess ?: getUserGuess()
    }
}