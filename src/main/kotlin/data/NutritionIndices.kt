package org.example.data

object NutritionIndices {
    const val CALORIES_INDEX = 0
    const val FAT_INDEX = 1
    const val SUGAR_INDEX = 2
    const val SODIUM_INDEX = 3
    const val PROTEIN_INDEX = 4
    const val SATURATED_FAT_INDEX = 5
    const val CARBOHYDRATES_INDEX = 6
}




//package org.example.presentation
//
//import org.example.logic.useCases.SweetsWithNoEggsUseCase
//import org.example.model.Recipe
//import org.example.presentation.displyUtils.displayDetails
//
//class SweetsWithNoEggsCLi(private val sweetsWithNoEggsUseCase: SweetsWithNoEggsUseCase) {
//
//    fun start() {
//        println("\n------ Sweet without egg -----")
//        showMenuLoop()
//    }
//
//    private fun showMenuLoop() {
//        var continueLoop = true
//
//        while (continueLoop) {
//            val dessert = getRandomEggFreeDessert()
//
//            println("\n1 - Like this dessert")
//            println("2 - Dislike (show another option)")
//            println("3 - Exit")
//
//            print("Please choose (1-3): ")
//            val userChoice = readln().toIntOrNull()
//
//            when (userChoice) {
//                1 -> {
//                    println("Thank you for your choice!  \n${dessert.displayDetails()}")
//                    continueLoop = false
//                }
//
//                2 ->{}
//                3 -> {
//                    println("Exiting...")
//                    continueLoop = false
//                }
//
//                else -> println("Invalid input. Please enter 1 = Like , 2 = Dislike, or 3 = Exit.")
//            }
//        }
//    }
//
//    //region method helper for SweetWithNoEggs
//    private fun getRandomEggFreeDessert(): Recipe {
//        return try {
//            val meal = sweetsWithNoEggsUseCase.getRandomSweetsNoEggs()
//            println("|| Name: ${meal.name} || Description: ${meal.description}")
//            meal
//        } catch (exception: Exception) {
//            println("Error: ${exception.message}")
//            throw exception
//        }
//    }
//
//}