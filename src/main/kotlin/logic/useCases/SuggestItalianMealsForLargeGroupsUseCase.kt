package org.example.logic.useCases

import org.example.logic.RecipesRepository
import org.example.model.Recipe

class SuggestItalianMealsForLargeGroupsUseCase(
    private val recipesRepository: RecipesRepository
) {
    fun getItalianMealsForLargeGroups(): List<Recipe> {
        val allRecipes = recipesRepository.getRecipes()
        val italianMealsForLargeGroup = allRecipes.filter {
            it.tags.isContainsItalianFoodForLargeGroups()
        }.take(10)
        return italianMealsForLargeGroup
    }

    private fun List<String>.isContainsItalianFoodForLargeGroups(): Boolean {
        val italianKeywords = listOf(
            "italian", "pasta", "lasagna", "risotto", "gnocchi", "ravioli",
            "cannelloni", "antipasto", "bruschetta", "tiramisu", "carbonara",
            "bolognese", "margherita", "napoli", "sicilian"
        )

        val groupKeywords = listOf(
            "for-large-groups", "dinner-party", "potluck", "superbowl",
            "family-gathering", "wedding", "birthday-party", "buffet",
            "banquet", "serves-many", "number-of-servings", "feeds-many"
        )
        return this.any { tag ->
            italianKeywords.any {
                tag.contains(it, ignoreCase = true)
            }
        } && this.any { tag ->
            groupKeywords.any {
                tag.contains(it, ignoreCase = true)
            }
        }
    }
}