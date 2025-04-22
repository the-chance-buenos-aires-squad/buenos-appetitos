package org.example.presentation

import ExploreRecipesByCountryCli
import IraqiRecipesCli
import org.example.logic.useCases.GuessGameUseCase
import java.util.*

class HolderCLi(
    private val searchFoodByAddDateClI: SearchFoodByDateCLI,
    private val healthyFoodMealsCLI: GetHealthyFoodMealsCLI,
    private val searchMealsByNameCLI: SearchMealsByNameCLI,
    private val guessGameUseCase: GuessGameUseCase,
    private val seaFoodRankingCLI: SeaFoodRankingCLI,
    private val suggestItalianRecipesForLargeGroupsCLI: GetSuggestItalianRecipesForLargeGroupsCLI,
    private val getRandomEasyRecipesCli: GetRandomEasyRecipesCLi,
    private val sweetsWithNoEggsCLi: SweetsWithNoEggsCLi,
    private val highCalorieCli: GetHighCalorieCli,
    private val getLovePotatoCLI: GetLovePotatoCLI,
    private val gymMealsCLI: GymMealsCLI,
    private val ingredientGameCLI: IngredientGameCLI,
    private val exploreRecipesByCountryCli: ExploreRecipesByCountryCli,
    private val iraqiRecipesCli: IraqiRecipesCli,
    private val getKetoDietRecipeHelperCLI: GetKetoDietRecipeHelperCLI
) {

    fun startCLI() {
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
                "1" -> healthyFoodMealsCLI.start()
                "2" -> searchMealsByNameCLI.start()
                "3" -> iraqiRecipesCli.startCli()
                "4" -> getRandomEasyRecipesCli.suggestRandomRecipes()
                "5" -> playGuessGame()
                "6" -> sweetsWithNoEggsCLi.start()
                "7" -> getKetoDietRecipeHelperCLI.start()
                "8" -> searchFoodByAddDateClI.start()
                "9" -> gymMealsCLI.start()
                "10" -> exploreRecipesByCountryCli.startCli()
                "11" -> ingredientGameCLI.start()
                "12" -> getLovePotatoCLI.start()
                "13" -> highCalorieCli.start()
                "14" -> seaFoodRankingCLI.start()
                "15" -> suggestItalianRecipesForLargeGroupsCLI.start()
                "0" -> {
                    println("Thank you for using Food Change Mood!")
                    return
                }

                else -> println("Invalid option. Please try again.")
            }
        }
    }


    private fun playGuessGame() {

        val recipe = guessGameUseCase.startGame()
        if (recipe == null) {
            println("Error: No recipes available")
            return
        }

        println("\nGuess Game: Try to guess the preparation time (in minutes) for this meal!")
        println("Meal: ${recipe.name}")

        while (guessGameUseCase.thereIsAttemptsLeft()) {
            print("\nEnter your guess (minutes) [${guessGameUseCase.getAttemptsLeft()} attempts left]: ")

            when (val result = guessGameUseCase.handleGuess(readLine())) {
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

        guessGameUseCase.resetGame()
    }


}