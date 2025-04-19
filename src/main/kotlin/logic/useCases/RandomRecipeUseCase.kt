package org.example.logic.useCases

import org.example.data.CsvRecipesRepository
import org.example.model.Recipe

class RandomRecipeUseCase(private val csvRecipesRepository: CsvRecipesRepository) {

     fun getRandomRecipe(): Recipe {
        return csvRecipesRepository.getRecipes().random()
    }

}