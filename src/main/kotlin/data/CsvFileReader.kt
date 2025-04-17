package org.example.data

import org.example.model.Recipe
import java.io.BufferedReader
import java.io.FileReader

class CsvFileReader {

    fun parseRecipesCsv(filePath: String): List<Recipe> {
        val recipes = mutableListOf<Recipe>()

        BufferedReader(FileReader(filePath)).use { reader ->
            val header = reader.readLine()

            val rawContent = reader.readText()
            val parsedRecords = parseCSVWithMultilineFields(rawContent)

            parsedRecords.forEach { fields ->
                try {
                    if (fields.size >= 12) {
                        val recipe = Recipe(
                            name = fields[0],
                            id = fields[1],
                            minutes = fields[2].toIntOrNull() ?: 0,
                            contributorId = fields[3],
                            submitted = fields[4],
                            tags = parseTags(fields[5]),
                            nutrition = parseNutrition(fields[6]),
                            numberOfSteps = fields[7].toIntOrNull() ?: 0,
                            steps = parseSteps(fields[8]),
                            description = parseNullableField(fields[9]),
                            ingredients = parseIngredients(fields[10]),
                            numberOfIngredients = fields[11].toIntOrNull() ?: 0
                        )
                        recipes.add(recipe)
                    }
                } catch (e: Exception) {
                    println("Error parsing record: ${e.message}")
                }
            }
        }

        return recipes
    }

    private fun parseCSVWithMultilineFields(content: String): List<List<String>> {
        val records = mutableListOf<List<String>>()
        val fields = mutableListOf<String>()
        val currentField = StringBuilder()

        var inQuotes = false
        var i = 0

        while (i < content.length) {
            val char = content[i]

            when {
                char == '"' -> {
                    if (i + 1 < content.length && content[i + 1] == '"') {
                        currentField.append('"')
                        i++
                    } else {
                        inQuotes = !inQuotes
                    }
                }
                char == ',' && !inQuotes -> {
                    fields.add(currentField.toString().trim())
                    currentField.clear()
                }
                char == '\n' && !inQuotes -> {
                    fields.add(currentField.toString().trim())
                    if (fields.isNotEmpty()) {
                        records.add(fields.toList())
                        fields.clear()
                    }
                    currentField.clear()
                }
                else -> {
                    currentField.append(char)
                }
            }

            i++
        }

        if (currentField.isNotEmpty() || fields.isNotEmpty()) {
            fields.add(currentField.toString().trim())
            records.add(fields.toList())
        }

        return records
    }

   private fun parseNullableField(input: String): String? {
        return when {
            input.isBlank() -> null
            input.equals("null", ignoreCase = true) -> null
            else -> input
        }
    }

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
                (char == '\'' || char == '"') -> {
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

    private fun parseTags(input: String): List<String> =
        try { parseList(input) } catch (e: Exception) { emptyList() }

    private  fun parseSteps(input: String): List<String> =
        try { parseList(input) } catch (e: Exception) { emptyList() }

    private fun parseIngredients(input: String): List<String> =
        try { parseList(input) } catch (e: Exception) { emptyList() }

private fun parseNutrition(input: String): Nutrition {
    return try {
        val stringList = parseList(input)
        val values = stringList.mapNotNull {
            it.toDoubleOrNull() ?: run {
                val cleaned = it.trim().replace("[^0-9.]".toRegex(), "")
                if (cleaned.isNotEmpty()) cleaned.toDoubleOrNull() else null
            }
        }
        Nutrition(
            calories = values[NutritionIndices.CALORIES_INDEX],
            fat = values[NutritionIndices.FAT_INDEX],
            sugar = values[NutritionIndices.SUGAR_INDEX],
            sodium = values[NutritionIndices.SODIUM_INDEX],
            protein = values[NutritionIndices.PROTEIN_INDEX],
            saturatedFat = values[NutritionIndices.SATURATED_FAT_INDEX],
            carbohydrates = values[NutritionIndices.CARBOHYDRATES_INDEX]
        )
    } catch (e: Exception) {
        Nutrition(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0)
    }
}

}