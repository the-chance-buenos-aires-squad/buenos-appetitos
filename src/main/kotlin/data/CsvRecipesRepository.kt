package org.example.data

import org.example.logic.RecipesRepository
import org.example.model.Recipe

class CsvRecipesRepository(
    private val csvFileReader: CsvFileReader,
    private val recipeParser: RecipeParser,
    private val memoryDataSource: MemoryDataSource
) :
    RecipesRepository {
    override fun getRecipes(): List<Recipe> {
        if (memoryDataSource.getRecipes().isEmpty()){
            val recipe = loadRecipesFromCsv()
            memoryDataSource.setRecipesList(recipe)
        }
        return memoryDataSource.getRecipes()
    }


    private fun loadRecipesFromCsv(): List<Recipe> {
        val rowsRecord = csvFileReader.readCsvFile()
        return recipeParser.parseRecipes(rowsRecord)
    }
}