package org.example.presentation

import HighCalorieUseCase
import org.example.logic.useCases.*
import org.example.model.Recipe
import LovePotatoUseCase
import org.example.logic.useCases.GuessGameUseCase
import java.util.*

class HolderCLi(
    private val sweetsWithNoEggsUseCase: SweetsWithNoEggsUseCase,
    private val getHealthyFastFoodMealsUseCase: GetHealthyFastFoodMealsUseCase,
    private val guessGameUseCase: GuessGameUseCase,
    private val getSeaFoodRankingByProteinUseCase: GetSeaFoodRankingByProteinUseCase,
    private val suggestItalianMealsForLargeGroupsUseCase: SuggestItalianMealsForLargeGroupsUseCase,
    private val suggestMealsUseCases: SuggestMealsUseCases,
    private val searchFoodByAddDateClI: SearchFoodByDateCLI,

    private val iraqiMealsUseCase: GetIraqiMealsUseCase,
    private val highCalorieUseCase: HighCalorieUseCase,
    private val exploreOtherCountriesFoodUseCase: ExploreOtherCountriesFoodUseCase,
    private val LovePotatoUseCase: LovePotatoUseCase
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
                "1" -> GetHealthyFoodMealsCLI(getHealthyFastFoodMealsUseCase).start()
                "2" -> searchMealsByName(scanner)
                "3" -> showIraqiMeals()
                "4" -> showEasyFoodSuggestions()
                "5" -> playGuessGame()
                "6" -> findSweetWithOutEgg()
                "7" -> ketoDietHelper()
                "8" -> searchFoodByAddDateClI.start()
                "9" -> gymHelper()
                "10" -> exploreFoodCultures()
                "11" -> playIngredientGame()
                "12" -> findPotatoDishes()
                "13" -> highCalorieMeals()
                "14" -> seafoodByProteinContent()
                "15" -> italianGroupMeals()
                "0" -> {
                    println("Thank you for using Food Change Mood!")
                    return
                }

                else -> println("Invalid option. Please try again.")
            }
        }
    }

    private fun searchMealsByName(scanner: Scanner) {
        /* TODO */
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


    private fun gymHelper() {
        /* TODO */
    }

    private fun exploreFoodCultures() {
        println("\n------ Explore countries food by there name -----")
        println("Enter the country name:")
        val userInput = readlnOrNull()?.trim() ?: ""
        try {
            exploreOtherCountriesFoodUseCase.searchCountryName(userInput).forEach {
                println(it.name)
            }
        } catch (exception: Exception) {
            println("Error: ${exception.message}")
        }
    }


    private fun playIngredientGame() {
        /* TODO */
    }


    private fun findPotatoDishes() {
        try {
            val randomPotatoRecipes = LovePotatoUseCase.getRandomPotatoRecipes()
            println("\nI Love Potato: 10 Random Recipes with Potatoes")
            println("============================================")
            randomPotatoRecipes.forEachIndexed { index, recipe ->
                println("${index + 1}. ${recipe.name}")
                println("   Cooking Time: ${recipe.minutes} minutes")
                println("   Ingredients: ${recipe.ingredients}")
            }

        } catch (exception: Exception) {
            println("Error: ${exception.message}")
            throw exception
        }
    }


    private fun highCalorieMeals() {
        println("\nHigh Calories Recipe")
        println("======================")
        try{
            val highCalorieRecipe = highCalorieUseCase.getRandomHighCalorieRecipe()
            println("Suggested Recipe Name: ${highCalorieRecipe.name} with ${highCalorieRecipe.nutrition.calories} calories")

        }catch (exception: Exception){
            println("Error: ${exception.message}")
            throw exception
        }
    }
    private fun seafoodByProteinContent() {
        getSeaFoodRankingByProteinUseCase.getSeaFoodRanking().forEach {
            println("Rank: ${it.rank} | Name: ${it.name} | Protein: ${it.amountOfProtein}")
        }
    }


    private fun italianGroupMeals() {
        suggestItalianMealsForLargeGroupsUseCase.getItalianMealsForLargeGroups().forEach {
            println(it.name)
        }
    }

    //region method helper for SweetWithNoEggs
    private fun getRandomEggFreeDessert(): Recipe {
        return try {
            val meal = sweetsWithNoEggsUseCase.getRandomSweetsNoEggs()
            println("|| Name: ${meal.name} || Description: ${meal.description}")
            meal
        } catch (exception: Exception) {
            println("Error: ${exception.message}")
            throw exception
        }
    }

    private fun handleDessertUserChoice(dessert: Recipe) {
        print("Please choose (1-3): ")
        while (true) {
            val likeOrNoInput = readln().toIntOrNull()
            when (likeOrNoInput) {
                1 -> {
                    displayLikedDessertDetails(dessert)
                    return
                }

                2 -> findSweetWithOutEgg()
                3 -> {
                    println("Exiting...")
                    return
                }

                else -> println("Invalid input. Please enter 1 = Like , 2 = Dislike, or 3 = Exit.")
            }
        }
    }

    private fun displayLikedDessertDetails(dessert: Recipe) {
        println("Thank you for your choice!")
        println(
            "|| Dessert Name : ${dessert.name} \n|| Dessert Description : ${dessert.description}" +
                    " \n|| Preparation Time: ${dessert.minutes} minutes \n|| Ingredients : ${dessert.ingredients}"
        )
        /*println("Desert ${dessert.name}")*/
    }
}