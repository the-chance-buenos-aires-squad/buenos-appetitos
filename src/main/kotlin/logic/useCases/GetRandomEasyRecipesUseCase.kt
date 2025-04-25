package org.example.logic.useCases

import org.example.logic.RecipesRepository
import org.example.model.Recipe


class GetRandomEasyRecipesUseCase(private val repository: RecipesRepository) {

    fun suggestEasyRecipes(): List<Recipe> {
        return repository.getRecipes().getEasyRecipeList()
    }

    companion object {
        const val MINUTES_MAX = 30
        const val INGREDIENTS_MAX_NUMBER = 5
        const val STEPS = 6
        const val TEN_RANDOM_EASY_RECIPES = 10
    }

    private fun List<Recipe>.getEasyRecipeList(): List<Recipe> {
        return this.filter { recipe ->
            recipe.minutes <= MINUTES_MAX
                    && recipe.numberOfIngredients <= INGREDIENTS_MAX_NUMBER
                    && recipe.numberOfSteps <= STEPS
        }.shuffled().take(TEN_RANDOM_EASY_RECIPES)
    }
}
