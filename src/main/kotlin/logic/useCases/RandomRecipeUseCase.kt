package org.example.logic.useCases

import org.example.data.CsvRecipesRepository
import org.example.logic.RecipesRepository
import org.example.model.Recipe

class RandomRecipeUseCase(private val repository: RecipesRepository) {

     fun getRandomRecipe(): Recipe {
        return repository.getRecipes().random()
    }

}