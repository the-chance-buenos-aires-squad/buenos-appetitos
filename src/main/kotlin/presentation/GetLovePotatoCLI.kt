package org.example.presentation

import GetLovePotatoUseCase


class GetLovePotatoCLI (private val getLovePotatoUseCase: GetLovePotatoUseCase) {
    fun start () {
        try {
            val randomPotatoRecipes = getLovePotatoUseCase.getRandomPotatoRecipes()
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


}
