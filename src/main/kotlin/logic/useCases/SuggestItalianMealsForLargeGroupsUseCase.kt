package org.example.logic.useCases

import org.example.logic.RecipesRepository
import org.example.model.Recipe

class SuggestItalianMealsForLargeGroupsUseCase(
    private val recipesRepository: RecipesRepository
) {
    fun getItalianMealsForLargeGroups():List<Recipe>{
        val allRecipes=recipesRepository.getRecipes()
        val italianMealsForLargeGroup=allRecipes.filter {
            it.tags.containsItalianFoodForLargeGroups()
        }.take(10)
        return italianMealsForLargeGroup
    }

    private fun List<String>.containsItalianFoodForLargeGroups():Boolean{
        return this.any {
            it.contains("italian", ignoreCase = true)||
                    it.contains("pasta",ignoreCase = true)||
                    it.contains("lasagna",ignoreCase = true)||
                    it.contains("risotto",ignoreCase = true)||
                    it.contains("gnocchi",ignoreCase = true)||
                    it.contains("ravioli",ignoreCase = true)||
                    it.contains("cannelloni", ignoreCase = true) ||
                    it.contains("antipasto", ignoreCase = true) ||
                    it.contains("bruschetta", ignoreCase = true) ||
                    it.contains("tiramisu", ignoreCase = true)||
                    it.contains("carbonara",ignoreCase = true)||
                    it.contains("bolognese", ignoreCase = true) ||
                    it.contains("margherita", ignoreCase = true) ||
                    it.contains("napoli", ignoreCase = true) ||
                    it.contains("sicilian", ignoreCase = true)
        }&& this.any {
            it.contains("for-large-groups", ignoreCase = true)||
                    it.contains("dinner-party",ignoreCase = true)||
                    it.contains("potluck",ignoreCase = true)||
                    it.contains("superbowl",ignoreCase = true)||
                    it.contains("family-gathering",ignoreCase = true)||
                    it.contains("wedding",ignoreCase = true)||
                    it.contains("birthday-party", ignoreCase = true) ||
                    it.contains("buffet", ignoreCase = true) ||
                    it.contains("banquet", ignoreCase = true) ||
                    it.contains("serves-many", ignoreCase = true)||
                    it.contains("number-of-servings",ignoreCase = true)||
                    it.contains("feeds-many", ignoreCase = true)
        }
    }
}