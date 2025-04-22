package org.example.presentation

import org.example.logic.useCases.SuggestItalianRecipesForLargeGroupsUseCase
import org.example.presentation.displyUtils.displayDetails

class GetSuggestItalianRecipesForLargeGroupsCLI(
    private val suggestItalianRecipesForLargeGroupsUseCase: SuggestItalianRecipesForLargeGroupsUseCase
) {
    fun start() {
        println("Do you want a specific number of Recipes? (write the number if yes and if no click 0) ")
        val numOfRecipes = readlnOrNull()?.toIntOrNull().takeIf { it != 0 }
        numOfRecipes?.let {
            suggestItalianRecipesForLargeGroupsUseCase
                .getItalianRecipesForLargeGroups(it)
                .forEach { italianGroupRecipe ->
                    italianGroupRecipe.displayDetails()
                }
        }
    }
}