package org.example.presentation

import org.example.logic.useCases.SweetsWithNoEggsUseCase
import org.example.model.Recipe
import org.example.presentation.displyUtils.displayDetails

class SweetsWithNoEggsCLi(private val sweetsWithNoEggsUseCase: SweetsWithNoEggsUseCase) {

    fun start() {
        println("\n------ Sweet without egg -----")
        showMenuSweetNoEggsLoop()
    }

    private fun showMenuSweetNoEggsLoop() {

        while (true) {
            val dessert = getRandomEggFreeDessert()

            if (dessert == null) {
                println("No dessert found")
                break
            }

            println("\n1 - Like this dessert")
            println("2 - Dislike (show another option)")
            println("3 - Exit")
            print("Please choose (1-3): ")
            val userChoice = readln().trim().toIntOrNull()

            when (userChoice) {
                1 -> {
                    println("Thank you for your choice!  \n${dessert.displayDetails()}")
                    break
                }

                2 -> {
                    println("Okay, let's try another dessert!")
                    continue
                }

                3 -> {
                    println("Exiting...")
                    break
                }

                else -> println("Invalid input. Please enter 1 = Like , 2 = Dislike, or 3 = Exit.")
            }
        }
    }

    private fun getRandomEggFreeDessert(): Recipe? {
        return try {
            val meal = sweetsWithNoEggsUseCase.getRandomSweetsNoEggs()
            println("|| Name: ${meal.name} || Description: ${meal.description}")
            meal
        } catch (exception: Exception) {
            println("Error: ${exception.message}")
            null
        }
    }
}
