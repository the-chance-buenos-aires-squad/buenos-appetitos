package dataHelper

import model.Nutrition
import org.example.model.Recipe
import java.time.LocalDate

fun createRecipe(
    name: String,
    tags: List<String>,
    ingredients: List<String>,
    nutrition: Nutrition
) = Recipe(
    name = name,
    id = "",
    minutes = 0,
    contributorId = "",
    submitted = LocalDate.now(),
    tags = tags,
    nutrition = nutrition,
    numberOfSteps = 0,
    steps = emptyList(),
    description = null,
    ingredients = ingredients,
    numberOfIngredients = 0
)