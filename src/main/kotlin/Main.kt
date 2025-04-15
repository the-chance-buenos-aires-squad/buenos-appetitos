package org.example

import org.example.data.CsvFileReader
import org.example.data.CsvRecipesRepository
import org.example.logic.useCases.UseCaseHolder
import org.example.presentation.HolderCLi
import java.util.Scanner

fun main() {
    val csvFileReader = CsvFileReader()
    val repository = CsvRecipesRepository(csvFileReader)
    val useCases = UseCaseHolder(repository)
    val scanner = Scanner(System.`in`)
    val holderCli = HolderCLi()

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
            "1" -> holderCli.showHealthyFastFood(useCases)
            "2" -> holderCli.searchMealsByName(scanner, useCases)
            "3" -> holderCli.showIraqiMeals(useCases)
            "4" -> holderCli.showEasyFoodSuggestions(useCases)
            "5" -> holderCli.playGuessGame(useCases)
            "6" -> holderCli.findSweetWithOutEgg(useCases)
            "7" -> holderCli.ketoDietHelper(useCases)
            "8" -> holderCli.searchFoodsByDate(useCases)
            "9" -> holderCli.gymHelper(useCases)
            "10" -> holderCli.exploreFoodCultures(useCases)
            "11" -> holderCli.playIngredientGame(useCases)
            "12" -> holderCli.findPotatoDishes(useCases)
            "13" -> holderCli.highCalorieMeals(useCases)
            "14" -> holderCli.seafoodByProteinContent(useCases)
            "15" -> holderCli.italianGroupMeals(useCases)
            "0" -> {
                println("Thank you for using Food Change Mood!")
                return
            }
            else -> println("Invalid option. Please try again.")
        }
    }
}
