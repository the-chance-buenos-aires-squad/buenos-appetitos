package org.example.logic.useCases

import org.example.logic.RecipesRepository
import org.example.model.Recipe

class GetIraqiMealsUseCase(private val repository: RecipesRepository) {

    fun execute(): List<Recipe> {
        val recipes = repository.getRecipes()

        return recipes.filter { recipe ->
            val tagsContainIraq = recipe.tags.any { it.contains("iraqi", ignoreCase = true) }
            val descriptionContainIraq = recipe.description?.contains("iraq", ignoreCase = true) ?: false
            tagsContainIraq || descriptionContainIraq
        }
    }
}
