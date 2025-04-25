package org.example.presentation.displyUtils

import logic.customExceptions.NoRecipeFoundException
import org.example.model.Recipe
import org.example.presentation.inputHandlingUtils.handleUserInput
import java.util.NoSuchElementException

fun Recipe.displayDetails() {
    println("=== Recipe Details ===")
    println("Name: ${this.name}")
    println("ID: ${this.id}")
    println("Preparation Time: ${this.minutes} minutes")
    println("Contributor ID: ${this.contributorId}")
    println("Submitted On: ${this.submitted}")
    println("Tags: ${this.tags.joinToString(", ")}")
    println("Nutrition Info:")
    println("  - Fat: ${this.nutrition.fat}")
    println("  - Saturated Fat: ${this.nutrition.saturatedFat}")
    println("  - Carbohydrates: ${this.nutrition.carbohydrates}")
    println("Number of Steps: ${this.numberOfSteps}")
    println("Steps:")
    this.steps.forEachIndexed { index, step ->
        println("  ${index + 1}. $step")
    }
    println("Description: ${this.description ?: "No description"}")
    println("Ingredients (${this.numberOfIngredients}):")
    this.ingredients.forEach { ingredient ->
        println("  - $ingredient")
    }
    println("======================")
}


fun Recipe.displayRecipeNameDescription() {
    println("here check this recipe >>>>>>>>>")
    println("name:${this.name}")
    println("description:${this.description}")
}


fun displayOptionsMenu(
    mainMessage: String,
    suggestRecipe: () -> Recipe,
) {
    while (true) {
        val newSuggestedRecipe:Recipe?
        try {
            newSuggestedRecipe = suggestRecipe()
        }catch (e: NoRecipeFoundException) {
            println(e.message)
            break
        }catch (e: RuntimeException){
            println("sorry there is no more recipes")
            break
        }
        println("\n$mainMessage")
        newSuggestedRecipe.displayRecipeNameDescription()
        println("----------------------------")
        println("1 - Like show me recipe details")
        println("2 - Dislike (show another recipe)")
        println("3 - Exit")
        print("Please choose (1-3): ")
        val userChoice = handleUserInput()
        when (userChoice) {
            "1" -> {
                println("good here are the details >>>>>  \n${newSuggestedRecipe.displayDetails()}")
                break
            }

            "2" -> {
                println("Okay, let's try another one!")
                continue
            }

            "3" -> {
                println("Exiting...")
                break
            }

            else -> {
                println("Invalid input...")
                println("Exiting...")
                break
            }
        }
    }


}