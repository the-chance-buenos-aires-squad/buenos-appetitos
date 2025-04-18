package org.example.presentation

import org.example.logic.useCases.GetSeaFoodRankingByProteinUseCase
import org.example.logic.useCases.GuessGameUseCase
import org.example.logic.useCases.UseCaseHolder
import java.util.*

class HolderCLi {

    fun startCLI(useCases: UseCaseHolder){
        val scanner = Scanner(System.`in`)
        while (true) {
            println("\nFood Change Mood - Menu:")
            println("1. Find healthy fast food meals")
            println("2. Search meals by name")
            println("3. Find Iraqi meals")
            println("4. Get easy food suggestions")
            println("5. Play guess game")
            println("6. Find sweets with no eggs")
            println("7. Keto diet helper")
            println("8. Search foods by date")
            println("9. Gym helper")
            println("10. Explore food cultures")
            println("11. Play ingredient game")
            println("12. Find potato dishes")
            println("13. High calorie meals")
            println("14. Seafood by protein content")
            println("15. Italian group meals")
            println("0. Exit")

            println("Enter your choice: ")

            when (scanner.nextLine()) {
                "1" -> showHealthyFastFood(useCases)
                "2" -> searchMealsByName(scanner, useCases)
                "3" -> showIraqiMeals(useCases)
                "4" -> showEasyFoodSuggestions(useCases)
                "5" -> playGuessGame(useCases)
                "6" -> findSweetWithOutEgg(useCases)
                "7" -> ketoDietHelper(useCases)
                "8" -> searchFoodsByDate(useCases)
                "9" -> gymHelper(useCases)
                "10" -> exploreFoodCultures(useCases)
                "11" -> playIngredientGame(useCases)
                "12" -> findPotatoDishes(useCases)
                "13" -> highCalorieMeals(useCases)
                "14" -> seafoodByProteinContent(useCases)
                "15" -> italianGroupMeals(useCases)
                "0" -> {
                    println("Thank you for using Food Change Mood!")
                    return
                }
                else -> println("Invalid option. Please try again.")
            }
        }
    }
    private fun showHealthyFastFood(useCases: UseCaseHolder) {
        /* TODO */
    }

    private fun searchMealsByName(scanner: Scanner, useCases: UseCaseHolder) {
        /* TODO */
    }

    private fun showIraqiMeals(useCases: UseCaseHolder) {
        /* TODO */
    }

    private fun showEasyFoodSuggestions(useCases: UseCaseHolder) {
        /* TODO */
    }

    private fun playGuessGame(useCases: UseCaseHolder) {
        val guessGame = GuessGameUseCase(useCases.repository)

        val recipe = guessGame.startGame()
        if (recipe == null) {
            println("Error: No recipes available")
            return
        }

        println("\nGuess Game: Try to guess the preparation time (in minutes) for this meal!")
        println("Meal: ${recipe.name}")

        while (guessGame.thereIsAttemptsLeft()) {
            print("\nEnter your guess (minutes) [${guessGame.getAttemptsLeft()} attempts left]: ")

            when (val result = guessGame.handleGuess(readLine())) {
                is GuessGameUseCase.GuessAttemptResult.Correct -> {
                    println("🎉 Congratulations! That's correct! The preparation time is ${result.correctTime} minutes.")
                    break
                }
                is GuessGameUseCase.GuessAttemptResult.TooLow -> {
                    println("Too low! Try a higher number.")
                }
                is GuessGameUseCase.GuessAttemptResult.TooHigh -> {
                    println("Too high! Try a lower number.")
                }
                is GuessGameUseCase.GuessAttemptResult.InvalidInput -> {
                    println(result.message)
                }
                is GuessGameUseCase.GuessAttemptResult.GameOver -> {
                    println("\nGame Over! The correct preparation time was ${result.correctTime} minutes.")
                    break
                }
            }
        }

        guessGame.resetGame()
    }

    private fun findSweetWithOutEgg(useCases: UseCaseHolder) {
        /* TODO */
    }

    private fun ketoDietHelper(useCases: UseCaseHolder) {
        /* TODO */
    }

    private fun searchFoodsByDate(useCases: UseCaseHolder) {
        /* TODO */
    }

    private fun gymHelper(useCases: UseCaseHolder) {
        /* TODO */
    }

    private fun exploreFoodCultures(useCases: UseCaseHolder) {
        /* TODO */
    }

    private fun playIngredientGame(useCases: UseCaseHolder) {
        val game = useCases.ingredientGameUseCase
        val scanner = Scanner(System.`in`)
        var score = 0

        println("🎮 Welcome to the INGREDIENT GAME!")
        println("✅ One point per correct answer. ❌ Game ends on wrong answer.")

        repeat(15) {
            val round = game.generateRound()

            println("\n🍽️ Meal: ${round.meal.name}")
            println("Which of the following is an ingredient?")
            round.options.forEachIndexed { index, option ->
                println("${index + 1}. $option")
            }

            print("Your choice (1–3): ")
            val choice = scanner.nextLine().toIntOrNull()
            val selected = round.options.getOrNull((choice ?: 0) - 1)

            if (game.checkAnswer(selected, round.correct)) {
                score += 1000
                println("✅ Correct! Your score: $score")
            } else {
                println("❌ Wrong! The correct answer was: ${round.correct}")
                println("🎯 Final Score: $score")
                return
            }
        }

        println("\n🎉 You completed all rounds! Final Score: $score")
    }


    private fun findPotatoDishes(useCases: UseCaseHolder) {
        /* TODO */
    }

    private fun highCalorieMeals(useCases: UseCaseHolder) {
        /* TODO */
    }

    private fun seafoodByProteinContent(useCases: UseCaseHolder) {
        val seaFoodRankingByProtein= GetSeaFoodRankingByProteinUseCase(useCases.repository)
        seaFoodRankingByProtein.getSeaFoodRanking().forEach {
            println("Rank: ${it.rank} | Name: ${it.name} | Protein: ${it.amountOfProtein}")
        }
    }

    private fun italianGroupMeals(useCases: UseCaseHolder) {
        /* TODO */
    }
}