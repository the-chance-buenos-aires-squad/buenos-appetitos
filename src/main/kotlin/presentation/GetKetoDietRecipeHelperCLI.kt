package org.example.presentation

import logic.useCases.GetKetoRecipeUseCase
import org.example.presentation.displyUtils.displayOptionsMenu


class GetKetoDietRecipeHelperCLI(
    private val getKetoRecipeUseCase: GetKetoRecipeUseCase
) {

    fun start() {
        displayOptionsMenu(
            mainMessage = "------ Dummy Recipes -----",
            suggestRecipe = { getKetoRecipeUseCase.suggestRandomKetoRecipe() }
        )
    }

}

