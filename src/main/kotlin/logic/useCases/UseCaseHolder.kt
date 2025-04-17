package org.example.logic.useCases

import org.example.logic.RecipesRepository
import java.util.*

class UseCaseHolder(val repository:RecipesRepository) {
    val ingredientGameUseCase = IngredientGameUseCase(repository)
    fun useCaseImplementation() {

    }
}