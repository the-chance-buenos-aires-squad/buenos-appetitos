package org.example.logic.useCases


import org.example.model.Recipe

class IdentifyIraqiMeals {
    fun execute(recipes: List<Recipe>): List<Recipe> {
        return recipes.filter { recipe ->
            val tagsContainIraq = recipe.tags?.any { it.contains("iraqi", ignoreCase = true) } ?: false
            val descriptionContainIraq = recipe.description?.contains("iraq", ignoreCase = true) ?: false
            tagsContainIraq || descriptionContainIraq


        }
    }

}
