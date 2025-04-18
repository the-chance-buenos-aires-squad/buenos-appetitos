package org.example.logic.useCases

import org.example.logic.RecipesRepository
import org.example.model.Recipe

class ExploreOtherCountriesFoodUseCase(private val repository: RecipesRepository) {

    fun searchCountryName(userInput: String): List<Recipe> {
        val country = userInput.trim().lowercase()
        val allRecipes = repository.getRecipes()
        if (country.isEmpty()) throw Exception("Country name cannot be empty")
        val matchedRecipes = allRecipes.countryNamesList(country)
        if (matchedRecipes.isEmpty()) throw Exception("No meals found for '$country")
        return matchedRecipes.shuffled().take(20)
    }
}

private fun List<Recipe>.countryNamesList(country: String): List<Recipe> {
    return this.filter { recipe ->
        recipe.tags.any { it.contains(country, ignoreCase = true) } ||
                (recipe.description?.contains(country, ignoreCase = true) ?: false)
    }
}