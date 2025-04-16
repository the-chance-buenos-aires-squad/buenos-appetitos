package org.example.presentation

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
        /* TODO */
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
        /* TODO */
    }

    private fun findPotatoDishes(useCases: UseCaseHolder) {
        /* TODO */
    }

    private fun highCalorieMeals(useCases: UseCaseHolder) {
        /* TODO */
    }

    private fun seafoodByProteinContent(useCases: UseCaseHolder) {
        /* TODO */
    }

    private fun italianGroupMeals(useCases: UseCaseHolder) {
        /* TODO */
    }
}