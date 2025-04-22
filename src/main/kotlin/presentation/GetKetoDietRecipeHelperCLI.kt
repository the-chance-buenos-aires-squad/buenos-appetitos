package org.example.presentation

import org.example.logic.useCases.GetKetoRecipeUseCase
import org.example.model.Recipe
import org.example.presentation.displyUtils.displayDetails
import org.example.presentation.inputHandlingUtils.handleUserInput


class GetKetoDietRecipeHelperCLI(
    private val getKetoRecipeUseCase: GetKetoRecipeUseCase
) {

    private var ketoRecipesList = emptyList<Recipe>()
    private var liveRecipe: Recipe? = null

    fun start() {
        ketoRecipesList = getKetoRecipeUseCase.getKetoRecipes().shuffled()

        when (ketoRecipesList.isEmpty()) {
            true -> {
                println("sorry we didn't find any keto recipes")
                return
            }

            false -> {
                liveRecipe = ketoRecipesList.popupRandomKetoRecipe()
                liveRecipe?.let { recipe ->
                    displayRecipeNameDescription(recipe)
                }
                getUserChoice()

            }
        }
    }

    private fun getUserChoice() {
        while (true) {
            displayKetoOptionsMenu()
            val input = handleUserInput()
            when (input) {
                "1" -> {
                    liveRecipe?.displayDetails()
                    return
                }

                "2" -> {
                    liveRecipe = ketoRecipesList.popupRandomKetoRecipe().apply {
                        displayRecipeNameDescription(this)
                    }
                }

                "3" -> {
                    return
                }

                else -> {
                    println("incorrect input please enter one of the options below")
                    displayKetoOptionsMenu()
                }
            }
        }
    }

    private fun displayKetoOptionsMenu() {
        println("--------------------------------------------------------")
        println("1- show details")
        println("2- get another random keto recipe")
        println("3- exit")
    }


    private fun displayRecipeNameDescription(recipe: Recipe) {
        println("here check this recipe >>>>>>>>>")
        recipe.apply {
            println("name:${this.name}")
            println("description:${this.description}")
        }
    }

    private fun List<Recipe>.popupRandomKetoRecipe(): Recipe {
        val poppedKetoRecipe = ketoRecipesList.first()
        ketoRecipesList = ketoRecipesList.drop(1)
        return poppedKetoRecipe
    }


}

