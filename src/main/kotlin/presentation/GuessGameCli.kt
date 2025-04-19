package org.example.presentation

import org.example.logic.useCases.GuessGameUseCase
import org.example.logic.useCases.RandomRecipeUseCase
import org.example.model.GuessAttemptState
import org.example.model.Recipe


class GuessGameCli (private val guessGameUseCase: GuessGameUseCase,
                    private val randomRecipeUseCase: RandomRecipeUseCase
) {
    private var attemptsCount = 0
    private lateinit var recipe: Recipe
    private  var isGameFinished = false
     fun play(  ) {

         try {
             recipe = randomRecipeUseCase.getRandomRecipe()
             println("The Recipe is \"${recipe.name}\"")
             isGameFinished = false
             runGameLoop()
         }catch (e: Exception){
             println("Error on the game start: ${e.message}")

         }


     }

    private fun runGameLoop() {
        try {
            while (!isGameFinished) {
                val userGuess = getUserGuess()
                if (userGuess == null) {
                    println("Invalid input. Please enter a number.")
                    continue
                }
                val result  = guessGameUseCase.submit(userGuess, recipe.minutes, ++attemptsCount)
                handelUserGuessResult(result)
            }
        } catch (e: Throwable) {
            handleGuessFailure(e)
        }
    }

    private fun handelUserGuessResult(result : GuessAttemptState){
        when(result){
            GuessAttemptState.CORRECT -> {
                println("Congratulations! You guessed the correct preparation time.")
                endTheGame()
            }
            GuessAttemptState.TOO_LOW -> println("The preparation time is too low.")
            GuessAttemptState.TOO_HIGH -> println("The preparation time is too high")
        }
    }

    private fun getUserGuess(): Int? {
        print("Guess the preparation time (in minutes): ")
        return readlnOrNull()?.toIntOrNull()
    }

    private fun handleGuessFailure(error: Throwable) {
        println(error.message)
        endTheGame()
    }
    private fun endTheGame(){
        isGameFinished = true
        attemptsCount = 0
    }


}