package org.example.presentation

import org.example.logic.useCases.GetKetoRecipeUseCase
import org.example.presentation.displyUtils.displayOptionsMenu


class GetKetoDietRecipeHelperCLI(
    private val getKetoRecipeUseCase: GetKetoRecipeUseCase
) {

    fun start() {
        displayOptionsMenu(
            mainMessage = "------ Keto Recipes -----",
            suggestRecipe = { getKetoRecipeUseCase.suggestRandomKetoRecipe() }
        )
    }

}

