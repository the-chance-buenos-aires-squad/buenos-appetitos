package org.example.presentation

import org.example.logic.useCases.GuessGameUseCase
import org.example.model.GuessGameState

class GuessGameCli(
    private val guessGameUseCase: GuessGameUseCase
) {

    fun start() {
        val gameState=  guessGameUseCase.startGame()
            try {
                println("The Recipe is \"${gameState.recipe.name}\"")
                runGameLoop(gameState)
            }catch (e: Exception) {
                println("Error on the game start: ${e.message}")
            }
    }

    private fun runGameLoop(gameState: GuessGameState) {
        if (gameState.isFinished) {
            return
        }

        val userGuess = getUserGuess()
        val (newState, result) = guessGameUseCase.processGuess(gameState, userGuess)

        if (result == null) {
            println("Invalid input. Please enter a number.")
            runGameLoop(newState)
            return
        }

        println(result.message)

        runGameLoop(newState)
    }

    private fun getUserGuess(): Int? {
        print("Guess the preparation time (in minutes): ")
        return readlnOrNull()?.toIntOrNull()
    }
}