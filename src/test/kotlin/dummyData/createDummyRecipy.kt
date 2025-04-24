package dummyData

import model.Nutrition
import org.example.model.Recipe
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

fun createDummyRecipe(
    name: String = "Default Recipe",
    id: String = "RECIPE_${UUID.randomUUID().toString().take(4)}", // Semi-unique ID
    minutes: Int = 30,
    contributorId: String = "user_${(1..100).random()}", // Random user
    submitted: LocalDate = LocalDate.now(),//use
    tags: List<String> = listOf(DummyTags.mealTypes.random()), // Random meal type
    nutrition: Nutrition = DummyNutrition.balanced,
    steps: List<String> = listOf("Default step 1", "Default step 2"),
    description: String? = "Default recipe description",
    ingredients: List<String> = listOf("Ingredient A", "Ingredient B")
): Recipe {
    return Recipe(
        name = name,
        id = id,
        minutes = minutes,
        contributorId = contributorId,
        submitted = submitted,
        tags = tags,
        nutrition = nutrition,
        numberOfSteps = steps.size,
        steps = steps,
        description = description,
        ingredients = ingredients,
        numberOfIngredients = ingredients.size
    )
}