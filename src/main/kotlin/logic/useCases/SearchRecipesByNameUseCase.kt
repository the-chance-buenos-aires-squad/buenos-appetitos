package org.example.logic.useCases

import logic.useCases.FuzzySearchUseCase
import org.example.logic.RecipesRepository
import org.example.logic.utili.CommonUtilizes
import org.example.model.Recipe

class SearchRecipesByNameUseCase(
    val repository: RecipesRepository,
    private val fuzzySearch: FuzzySearchUseCase,
    private val kmpSearch: KmpSearchUseCase,
    private val maxRecipesToShare: Int = CommonUtilizes.DEFAULT_NUM_OF_RECIPES
) {
    private val allRecipes = repository.getRecipes()

    fun searchRecipeName(
        recipes: List<Recipe> = allRecipes,
        query: String,
        useFuzzy: Boolean = false,
        maxTypos: Int = 3
    ): List<Recipe> {
        return recipes.getListOfMealsByName(query, useFuzzy, maxTypos, fuzzySearch, kmpSearch, maxRecipesToShare)
    }

}

private fun List<Recipe>.getListOfMealsByName(
    query: String,
    useFuzzy: Boolean,
    maxTypos: Int,
    fuzzySearch: FuzzySearchUseCase,
    kmpSearch: KmpSearchUseCase,
    maxRecipesToShare: Int,
): List<Recipe> {
    return this.filter { recipe ->
        kmpSearch.kmp(recipe.name.lowercase(), query.lowercase()) != -1 ||
                (useFuzzy && fuzzySearch.isFuzzyMatch(recipe.name.lowercase(), query.lowercase(), maxTypos))
    }.take(maxRecipesToShare)
}

