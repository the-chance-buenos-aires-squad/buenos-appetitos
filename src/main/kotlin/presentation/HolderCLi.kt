package org.example.presentation

import org.example.logic.useCases.GetSeaFoodRankingByProteinUseCase
import org.example.logic.useCases.*
import org.example.model.Recipe
import LovePotatoUseCase
import org.example.logic.useCases.GuessGameUseCase
import java.util.*

class HolderCLi(
    private val searchFoodByAddDateClI: SearchFoodByDateCLI,
    private val healthyFoodMealsCLI: GetHealthyFoodMealsCLI,
    private val guessGameCli: GuessGameCli,
    private val getSeaFoodRankingByProteinUseCase: GetSeaFoodRankingByProteinUseCase,
    private val suggestItalianMealsForLargeGroupsUseCase: SuggestItalianMealsForLargeGroupsUseCase,
    private val suggestMealsUseCases: SuggestMealsUseCases,
    private val sweetsWithNoEggsUseCase: SweetsWithNoEggsUseCase,
    private val iraqiMealsUseCase: GetIraqiMealsUseCase,
    private val getHighCalorieUseCase: GetHighCalorieUseCase,
    private val exploreOtherCountriesFoodUseCase: ExploreOtherCountriesFoodUseCase,
    private val lovePotatoUseCase: LovePotatoUseCase,
    private val gymMealsUseCase: GymMealsUseCase,
    private val ingredientGameUseCase: IngredientGameUseCase
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
                "2" -> searchMealsByName(scanner)
                "3" -> showIraqiMeals()
                "4" -> showEasyFoodSuggestions()
                "5" -> guessGameCli.play()
                "6" -> findSweetWithOutEgg()
                "7" -> ketoDietHelper()
                "8" -> searchFoodByAddDateClI.start()
                "9" -> gymHelper()
                "10" -> exploreFoodCultures()
                "11" -> playIngredientGame()
                "12" -> findPotatoDishes()
                "13" -> GetHighCalorieCli(getHighCalorieUseCase).start()
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

    private fun searchFoodsByDate() {
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


    private fun gymHelper() {
        val scanner = Scanner(System.`in`)

        println("🏋️ Welcome to the Gym Helper!")

        try {
            print("Enter desired calories: ")
            val caloriesInput = scanner.nextLine()
            val calories = caloriesInput.toIntOrNull() ?: throw IllegalArgumentException("Invalid calories input.")

            print("Enter desired protein (grams): ")
            val proteinInput = scanner.nextLine()
            val protein = proteinInput.toIntOrNull() ?: throw IllegalArgumentException("Invalid protein input.")

            val meals = gymMealsUseCase.findMealsByNutrition(calories, protein)

            if (meals.isEmpty()) {
                println("❌ No meals found matching your fitness needs.")
            } else {
                println("✅ Meals matching your goals:")
                gymMealsUseCase.printMealList(meals)
            }

        } catch (e: Exception) {
            println("⚠️ Error: ${e.message}")
        }
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

    private fun findPotatoDishes() {
        try {
            val randomPotatoRecipes = lovePotatoUseCase.getRandomPotatoRecipes()
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