package org.example.data

import org.example.logic.RecipesRepository
import org.example.model.Recipe

class CsvRecipesRepository(private val csvFileReader: CsvFileReader, private val recipeParser: RecipeParser) :
    RecipesRepository {
    private var recipes: List<Recipe> = emptyList()
    override fun getRecipes(): List<Recipe> {
        if (recipes.isNotEmpty()) return recipes
        val rowsRecord = csvFileReader.readCsvFile()
        return recipeParser.parseRecipes(rowsRecord).also {
            recipes = it
        }
    }
}