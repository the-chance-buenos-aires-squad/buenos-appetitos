package org.example.presentation

import org.example.logic.useCases.GetHighCalorieUseCase
import org.example.presentation.displyUtils.displayOptionsMenu
import org.example.presentation.uiController.UiController

class GetHighCalorieCli(
    private val uiController: UiController,
    private val getHighCalorieUseCase: GetHighCalorieUseCase
) {
    fun start() {
        displayOptionsMenu(
            uiController = uiController,
            mainMessage = "--------High Calories Recipe----------",
            suggestRecipe = { getHighCalorieUseCase.suggestRandomHighCalorieRecipe() }
        )
    }
}