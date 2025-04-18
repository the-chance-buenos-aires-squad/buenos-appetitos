package org.example.data

import org.example.logic.RecipesRepository
import org.example.model.Recipe

class CsvRecipesRepository( val csvFileReader: CsvFileReader, val recipeParser: RecipeParser ):RecipesRepository {
    override fun getRecipes():List<Recipe> {
         val rowsRecord= csvFileReader.readCsvFile()
        return  recipeParser.parseRecipes(rowsRecord)
    }
    fun getAllRecipes(): List<Recipe> {
        val filePath = "src/main/kotlin/data/food.csv"
        return csvFileReader.parseRecipesCsv(filePath)
    }

}