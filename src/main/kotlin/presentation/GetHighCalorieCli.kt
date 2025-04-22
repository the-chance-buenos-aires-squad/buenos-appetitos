package org.example.presentation

import org.example.logic.useCases.GetHighCalorieUseCase
import org.example.presentation.displyUtils.displayOptionsMenu

class GetHighCalorieCli(
    private val getHighCalorieUseCase: GetHighCalorieUseCase
) {
    fun start() {
        displayOptionsMenu(
            mainMessage = "--------High Calories Recipe----------",
            suggestRecipe = {getHighCalorieUseCase.suggestRandomHighCalorieRecipe()}
        )
    }
}