package org.example.presentation

import logic.useCases.SweetsWithNoEggsUseCase
import org.example.presentation.displyUtils.displayOptionsMenu
import org.example.presentation.uiController.UiController

class SweetsWithNoEggsCLi(
    private val uiController: UiController,
    private val sweetsWithNoEggsUseCase: SweetsWithNoEggsUseCase
) {

    fun start() {
        displayOptionsMenu(
            uiController = uiController,
            mainMessage = "------ Sweet without egg -----",
            suggestRecipe = { sweetsWithNoEggsUseCase.suggestSweetNoEggRecipe() }
        )
    }
}
