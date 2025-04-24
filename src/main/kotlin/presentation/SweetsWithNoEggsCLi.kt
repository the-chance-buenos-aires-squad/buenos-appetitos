package org.example.presentation

import org.example.logic.useCases.SweetsWithNoEggsUseCase
import org.example.presentation.displyUtils.displayOptionsMenu

class SweetsWithNoEggsCLi(private val sweetsWithNoEggsUseCase: SweetsWithNoEggsUseCase) {

    fun start() {
        displayOptionsMenu(
            mainMessage = "------ Sweet without egg -----",
            suggestRecipe = { sweetsWithNoEggsUseCase.suggestSweetNoEggRecipe() }
        )
    }
}
