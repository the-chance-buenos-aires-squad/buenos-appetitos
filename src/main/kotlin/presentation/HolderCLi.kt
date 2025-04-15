package org.example.presentation

import org.example.logic.useCases.GuessGameUseCase
import org.example.logic.useCases.UseCaseHolder

class HolderCLi {

    // Play Guess Game Feature
    fun playGuessGame(useCases: UseCaseHolder) {
        val guessGameUseCase = GuessGameUseCase(useCases.repository)

        when (val gameResult = guessGameUseCase.playGuessGame()) {
            is GuessGameUseCase.GameResult.Success -> {
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

                    when (guessGameUseCase.checkGuess(guess, gameResult.correctTime)) {
                        GuessGameUseCase.GuessResult.CORRECT -> {
                            println("🎉 Congratulations! That's correct! The preparation time is ${gameResult.correctTime} minutes.")
                            return
                        }
                        GuessGameUseCase.GuessResult.TOO_LOW -> println("Too low! Try a higher number.")
                        GuessGameUseCase.GuessResult.TOO_HIGH -> println("Too high! Try a lower number.")
                    }

                    attemptsLeft--
                }

                println("\nGame Over! The correct preparation time was ${gameResult.correctTime} minutes.")
            }
            is GuessGameUseCase.GameResult.Error -> {
                println("Error: ${gameResult.message}")
            }
        }
    }
}

