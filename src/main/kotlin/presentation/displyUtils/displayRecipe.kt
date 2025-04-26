package org.example.presentation.displyUtils

import logic.customExceptions.NoRecipeFoundException
import org.example.model.Recipe
import org.example.presentation.inputHandlingUtils.handleUserInput
import org.example.presentation.uiController.UiController
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


fun     displayOptionsMenu(
    uiController: UiController,
    mainMessage: String,
    suggestRecipe: () -> Recipe,
) {
    while (true) {
        val newSuggestedRecipe:Recipe?
        try {
            newSuggestedRecipe = suggestRecipe()
        }catch (e: NoRecipeFoundException) {
            uiController.printMessage(e.message.toString())
            break
        }catch (e: RuntimeException){
            uiController.printMessage("sorry there is no more recipes")
            break
        }
        uiController.printMessage("\n$mainMessage")
        newSuggestedRecipe.displayRecipeNameDescription()
        uiController.printMessage("----------------------------")
        uiController.printMessage("1 - Like show me recipe details")
        uiController.printMessage("2 - Dislike (show another recipe)")
        uiController.printMessage("3 - Exit")
        uiController.printMessage("Please choose (1-3): ",true)
        val userChoice = uiController.readInput()
        when (userChoice) {
            "1" -> {
                uiController.printMessage("good here are the details >>>>>  \n${newSuggestedRecipe.displayDetails()}")
                break
            }

            "2" -> {
                uiController.printMessage("Okay, let's try another one!")
                continue
            }

            "3" -> {
                uiController.printMessage("Exiting...")
                break
            }

            else -> {
                uiController.printMessage("Invalid input...")
                uiController.printMessage("Exiting...")
                break
            }
        }
    }


}