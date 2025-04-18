package org.example.data

import model.Nutrition
import model.NutritionIndices
import org.example.model.Recipe
import org.example.model.RecipeIndices
import java.time.LocalDate

class RecipeParser {
      fun parseRecipes(records: List<List<String>>): List<Recipe> =
        records
            .filter { it.size >= 12 }
            .mapNotNull { fields -> 
                runCatching {
                    Recipe(
                        name = fields[RecipeIndices.NAME_INDEX],
                        id = fields[RecipeIndices.ID_INDEX],
                        minutes = fields[RecipeIndices.MINUTES_INDEX].toIntOrNull() ?: 0,
                        contributorId = fields[RecipeIndices.CONTRIBUTOR_ID_INDEX],
                        submitted = LocalDate.parse(fields[RecipeIndices.SUBMITTED_INDEX]),
                        tags = parseTags(fields[RecipeIndices.TAGS_INDEX]),
                        nutrition = parseNutrition(fields[RecipeIndices.NUTRITION_INDEX]),
                        numberOfSteps = fields[RecipeIndices.NUMBER_OF_STEPS_INDEX].toIntOrNull() ?: 0,
                        steps = parseSteps(fields[RecipeIndices.STEPS_INDEX]),
                        description = parseNullableField(fields[RecipeIndices.DESCRIPTION_INDEX]),
                        ingredients = parseIngredients(fields[RecipeIndices.INGREDIENTS_INDEX]),
                        numberOfIngredients = fields[RecipeIndices.NUMBER_OF_INGREDIENTS_INDEX].toIntOrNull() ?: 0
                    )
                }.onFailure { 
                    println("Error parsing record: ${it.message}") 
                }.getOrNull()
            }


    private fun parseNullableField(input: String): String? =
        input.takeUnless { it.isBlank() || it.equals("null", ignoreCase = true) }


    private fun parseList(input: String): List<String> {
        if (input.isBlank()) return emptyList()

        val cleanInput = input.trim()
        if (!cleanInput.startsWith("[") || !cleanInput.endsWith("]")) {
            return listOf(cleanInput)
        }

        val content = cleanInput.substring(1, cleanInput.length - 1).trim()
        if (content.isBlank()) return emptyList()

        val items = mutableListOf<String>()
        var currentItem = StringBuilder()
        var inQuotes = false
        var i = 0

        while (i < content.length) {
            val char = content[i]

            when {
                (isQuoteCharacter(char)) -> {
                    inQuotes = !inQuotes
                }
                char == ',' && !inQuotes -> {
                    items.add(currentItem.toString().trim().trim('\'', '"'))
                    currentItem = StringBuilder()
                }
                else -> {
                    currentItem.append(char)
                }
            }

            i++
        }

        if (currentItem.isNotEmpty()) {
            items.add(currentItem.toString().trim().trim('\'', '"'))
        }

        return items
    }


    private fun isQuoteCharacter(char: Char) = char == '\'' || char == '"'

    private fun parseTags(input: String): List<String> =
        runCatching { parseList(input) }.getOrDefault(emptyList())

    private fun parseSteps(input: String): List<String> =
        runCatching { parseList(input) }.getOrDefault(emptyList())

    private fun parseIngredients(input: String): List<String> =
        runCatching { parseList(input) }.getOrDefault(emptyList())

    private fun parseNutrition(input: String): Nutrition =
        runCatching {
            parseList(input)
                .mapNotNull { str ->
                    str.toDoubleOrNull()
                }
                .let { values ->
                    Nutrition(
                        calories = values[NutritionIndices.CALORIES_INDEX],
                        fat = values[NutritionIndices.FAT_INDEX],
                        sugar = values[NutritionIndices.SUGAR_INDEX],
                        sodium = values[NutritionIndices.SODIUM_INDEX],
                        protein = values[NutritionIndices.PROTEIN_INDEX],
                        saturatedFat = values[NutritionIndices.SATURATED_FAT_INDEX],
                        carbohydrates = values[NutritionIndices.CARBOHYDRATES_INDEX]
                    )
                }
        }.getOrDefault(Nutrition(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,))
}