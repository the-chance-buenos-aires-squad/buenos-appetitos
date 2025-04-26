package org.example.presentation

import logic.useCases.GetKetoRecipeUseCase
import org.example.presentation.displyUtils.displayOptionsMenu
import org.example.presentation.uiController.UiController


class GetKetoDietRecipeHelperCLI(
    private val uiController: UiController,
    private val getKetoRecipeUseCase: GetKetoRecipeUseCase
) {

    fun start() {
        displayOptionsMenu(
            uiController =  uiController,
            mainMessage = "------ Keto Recipes -----",
            suggestRecipe = { getKetoRecipeUseCase.suggestRandomKetoRecipe() }
        )
    }

}

