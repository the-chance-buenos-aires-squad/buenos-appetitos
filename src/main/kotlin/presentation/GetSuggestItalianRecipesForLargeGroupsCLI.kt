package org.example.presentation

import org.example.logic.useCases.SuggestItalianRecipesForLargeGroupsUseCase

class GetSuggestItalianRecipesForLargeGroupsCLI(
    private val suggestItalianRecipesForLargeGroupsUseCase: SuggestItalianRecipesForLargeGroupsUseCase
) {
    fun start() {
        val recipesForm = RecipesForm()
        println("Do you want a specific number of Recipes? (write the number if yes and if no click 0) ")
        val numOfRecipes = readlnOrNull()?.toInt()
        if (numOfRecipes == null || numOfRecipes > 0) {
            suggestItalianRecipesForLargeGroupsUseCase.getItalianRecipesForLargeGroups().forEach {
                recipesForm.printingRecipes(it)
            }
        } else {
            suggestItalianRecipesForLargeGroupsUseCase.getItalianRecipesForLargeGroups(numOfRecipes)
        }
    }
}