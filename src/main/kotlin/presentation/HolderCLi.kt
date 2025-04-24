package org.example.presentation

import IraqiRecipesCli
import org.example.logic.useCases.GuessGameUseCase
import java.util.*

class HolderCLi(
    private val searchFoodByAddDateClI: SearchFoodByDateCLI,
    private val healthyFoodMealsCLI: GetHealthyFoodMealsCLI,
    private val guessGameCli: GuessGameCli,
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
                "5" -> guessGameCli.play()
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
                    println("Thank you for using buenos-appetitos App!")
                    return
                }

                else -> println("Invalid option. Please try again.")
            }
        }
    }


    private fun showIraqiMeals() {

        val iraqiMeals = iraqiMealsUseCase.execute()
        println("Iraqi Meals:")
        iraqiMeals.forEach {
            println(it.name)
        }
    }

    private fun showEasyFoodSuggestions() {
        var meals = suggestMealsUseCases.suggestRandomMeals()
        if (meals.isEmpty())
            println("No suitable meals found.")
        else
            println("Suggested meals: $meals")
    }

    private fun findSweetWithOutEgg() {

        println("\n------ Sweet without egg -----")
        val dessert = getRandomEggFreeDessert()
        println("\n1 - Like this dessert")
        println("2 - Dislike (show another option)")
        println("3 - Exit")
        handleDessertUserChoice(dessert)

    }

    private fun ketoDietHelper() {
        /* TODO */
    }

    private fun playIngredientGame() {
        val scanner = Scanner(System.`in`)
        var score = 0

        println("🎮 Welcome to the INGREDIENT GAME!")
        println("✅ One point per correct answer. ❌ Game ends on wrong answer.")

        repeat(15) {
            val round = ingredientGameUseCase.generateRound()

            println("\n🍽️ Meal: ${round.meal.name}")
            println("Which of the following is an ingredient?")
            round.options.forEachIndexed { index, option ->
                println("${index + 1}. $option")
            }

            print("Your choice (1–3): ")
            val choice = scanner.nextLine().toIntOrNull()
            val selected = round.options.getOrNull((choice ?: 0) - 1)

            if (ingredientGameUseCase.checkAnswer(selected, round.correct)) {
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

}