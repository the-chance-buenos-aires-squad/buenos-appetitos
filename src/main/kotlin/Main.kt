package org.example

import org.example.data.CsvFileReader
import org.example.data.CsvRecipesRepository
import org.example.data.RecipeParser
import org.example.logic.useCases.UseCaseHolder
import org.example.presentation.HolderCLi

fun main() {
    val csvFileReader = CsvFileReader()
    val recipeParser = RecipeParser()
    val repository = CsvRecipesRepository(csvFileReader, recipeParser)
}