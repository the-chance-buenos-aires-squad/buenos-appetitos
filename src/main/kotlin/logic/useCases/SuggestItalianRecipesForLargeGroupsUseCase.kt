package org.example.logic.useCases

import org.example.logic.RecipesRepository
import org.example.logic.utili.CommonUtilizes
import org.example.model.Recipe

class SuggestItalianRecipesForLargeGroupsUseCase(
    private val recipesRepository: RecipesRepository
) {



    fun getItalianRecipesForLargeGroups(count: Int = CommonUtilizes.DEFAULT_NUM_OF_RECIPES): List<Recipe> =
        recipesRepository.getRecipes()
            .filterItalianRecipesForLargeGroups()
            .take(count)

    private fun List<Recipe>.filterItalianRecipesForLargeGroups(): List<Recipe> {
        return this.filter { it.tags.containsItalianFood() && it.tags.containsLargeGroupTags() }
    }

    private fun List<String>.containsItalianFood(): Boolean {
        val italianKeywords = listOf(
            "italian", "pasta", "lasagna", "risotto", "gnocchi", "ravioli",
            "cannelloni", "antipasto", "bruschetta", "tiramisu", "carbonara",
            "bolognese", "margherita", "napoli", "sicilian"
        )
        return this.any { tag ->
            italianKeywords.any { keyword ->
                tag.contains(keyword, ignoreCase = true)
            }
        }
    }

    private fun List<String>.containsLargeGroupTags(): Boolean {
        val groupKeywords = listOf(
            "for-large-groups", "dinner-party", "potluck", "superbowl",
            "family-gathering", "wedding", "birthday-party", "buffet",
            "banquet", "serves-many", "number-of-servings", "feeds-many"
        )
        return this.any { tag ->
            groupKeywords.any { keyword ->
                tag.contains(keyword, ignoreCase = true)
            }
        }
    }
}