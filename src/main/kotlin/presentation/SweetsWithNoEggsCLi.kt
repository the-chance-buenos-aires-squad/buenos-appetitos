package org.example.presentation

import org.example.logic.useCases.SweetsWithNoEggsUseCase
import org.example.model.Recipe
import org.example.presentation.displyUtils.displayDetails

class SweetsWithNoEggsCLi(private val sweetsWithNoEggsUseCase: SweetsWithNoEggsUseCase) {

    fun start() {
        println("\n------ Sweet without egg -----")
        val dessert = getRandomEggFreeDessert()
        println("\n1 - Like this dessert")
        println("2 - Dislike (show another option)")
        println("3 - Exit")
        handleDessertUserChoice(dessert)
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
                2 -> {
                    val dessert2 = getRandomEggFreeDessert()
                    displayLikedDessertDetails(dessert2)
                    return
                }
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
        dessert.displayDetails()
    }
}