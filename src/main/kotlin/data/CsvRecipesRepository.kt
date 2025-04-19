package org.example.data

import org.example.logic.RecipesRepository
import org.example.model.Recipe

class CsvRecipesRepository(private val csvFileReader: CsvFileReader, private val recipeParser: RecipeParser) :
    RecipesRepository {
    override fun getRecipes(): List<Recipe> {
        val rowsRecord = csvFileReader.readCsvFile()
        return recipeParser.parseRecipes(rowsRecord)
    }
}