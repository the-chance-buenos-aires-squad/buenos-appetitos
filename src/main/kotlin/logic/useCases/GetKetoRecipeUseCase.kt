package logic.useCases

import logic.customExceptions.NoRecipeFoundException
import org.example.logic.RecipesRepository
import org.example.logic.customExceptions.NoKetoRecipeFoundException
import org.example.model.Recipe
import kotlin.random.Random


class GetKetoRecipeUseCase(
    private val repository: RecipesRepository
) {
    private var ketoRecipes = mutableListOf<Recipe>()

    private fun loadKetoRecipes() {
        val recipes = repository.getRecipes().also { if (it.isEmpty()) throw NoRecipeFoundException() }
        ketoRecipes.addAll(recipes.filterByNutrition()).also {
            if (ketoRecipes.isEmpty()) throw NoKetoRecipeFoundException()
        }
    }

    private fun List<Recipe>.filterByNutrition(): List<Recipe> {
        return this.filter { recipe ->
            recipe.nutrition.fat >= MIN_FAT &&
                    recipe.nutrition.protein >= MIN_PROTEIN &&
                    recipe.nutrition.saturatedFat <= MAX_SAT_FAT &&
                    recipe.nutrition.carbohydrates <= MAX_CARBS
        }

    }

    fun suggestRandomKetoRecipe(): Recipe {
        var randomIndex = 0
        if (ketoRecipes.isEmpty()) {
            loadKetoRecipes()
        } else {
            randomIndex = Random.nextInt(ketoRecipes.size)
        }

        return ketoRecipes.removeAt(randomIndex)
    }


    companion object {
        const val MAX_CARBS = 30.0
        const val MIN_FAT = 40.0
        const val MIN_PROTEIN = 30.0
        const val MAX_SAT_FAT = 15.0
    }

}
