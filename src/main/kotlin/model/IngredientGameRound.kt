package org.example.model

data class IngredientGameRound(
    val meal: Recipe,
    val options: List<String>,
    val correct: String
)