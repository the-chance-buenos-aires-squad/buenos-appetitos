package org.example.logic.useCases

import org.example.logic.RecipesRepository
import org.example.model.Recipe

class SearchWithKmpUseCase(
    val repository: RecipesRepository,
    private val fuzzySearchUseCase: FuzzySearchUseCase,
    private val kmpSearchUseCase: KmpSearchUseCase
) {
    private val allRecipes = repository.getRecipes()

    fun searchRecipes(
        recipes: List<Recipe> = allRecipes,
        query: String,
        useFuzzy: Boolean = false,
        maxTypos: Int = 3
    ): List<Recipe> {
        return recipes.filter { recipe ->
            // Exact match first
            kmpSearchUseCase.kmp(recipe.name.lowercase(), query.lowercase()) != -1 ||
                    (useFuzzy && fuzzySearchUseCase.isFuzzyMatch(recipe.name.lowercase(), query.lowercase(), maxTypos))
        }
    }


}


