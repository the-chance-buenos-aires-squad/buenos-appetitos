package org.example.presentation

import org.example.logic.useCases.GuessGame
import org.example.logic.useCases.UseCaseHolder

class HolderCLi {

    // Play Guess Game Function
    fun playGuessGame(useCases: UseCaseHolder) {
        val guessGame = GuessGame(useCases.repository)

        when (val gameResult = guessGame.playGuessGame()) {
            is GuessGame.GameResult.Success -> {
                println("\nGuess Game: Try to guess the preparation time (in minutes) for this meal!")
                println("Meal: ${gameResult.recipeName}")

                var attemptsLeft = 3
                while (attemptsLeft > 0) {
                    print("\nEnter your guess (minutes) [${attemptsLeft} attempts left]: ")
                    val guess = readLine()?.toIntOrNull()

                    if (guess == null) {
                        println("Please enter a valid number!")
                        continue
                    }

                    when (guessGame.checkGuess(guess, gameResult.correctTime)) {
                        GuessGame.GuessResult.CORRECT -> {
                            println("🎉 Congratulations! That's correct! The preparation time is ${gameResult.correctTime} minutes.")
                            return
                        }
                        GuessGame.GuessResult.TOO_LOW -> println("Too low! Try a higher number.")
                        GuessGame.GuessResult.TOO_HIGH -> println("Too high! Try a lower number.")
                    }

                    attemptsLeft--
                }

                println("\nGame Over! The correct preparation time was ${gameResult.correctTime} minutes.")
            }
            is GuessGame.GameResult.Error -> {
                println("Error: ${gameResult.message}")
            }
        }
    }
}

