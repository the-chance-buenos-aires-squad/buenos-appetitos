package org.example.data

import org.example.logic.RecipesRepository
import org.example.model.Recipe

class CsvRecipesRepository(private val csvFileReader: CsvFileReader, private val recipeParser: RecipeParser) :
    RecipesRepository {
    private val memoryDataSource = MemoryDataSource()
    override fun getRecipes(): List<Recipe> {
        if (memoryDataSource.getCachedRecipes()
                .isNotEmpty()
        ) return memoryDataSource.getCachedRecipes()
        val rowsRecord = csvFileReader.readCsvFile()
        return recipeParser.parseRecipes(rowsRecord).also {
            memoryDataSource.cacheRecipes(it)
        }
    }
}