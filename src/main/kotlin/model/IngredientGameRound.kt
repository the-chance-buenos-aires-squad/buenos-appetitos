package org.example.model

data class IngredientGameRound(
    val meal: Recipe,
    val questionChoices: List<String>,
    val correct: String
)