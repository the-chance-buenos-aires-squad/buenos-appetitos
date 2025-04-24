package org.example.model

import model.Nutrition
import java.time.LocalDate

data class Recipe(
    val name: String,
    val id: String,
    val minutes: Int,
    val contributorId: String,
    val submitted: LocalDate,
    val tags: List<String>,
    val nutrition: Nutrition,
    val numberOfSteps: Int,
    val steps: List<String>,
    val description: String?,
    val ingredients: List<String>,
    val numberOfIngredients: Int
)