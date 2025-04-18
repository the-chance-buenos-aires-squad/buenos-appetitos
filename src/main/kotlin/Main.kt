package org.example

import org.example.data.CsvFileReader
import org.example.data.CsvRecipesRepository
import org.example.data.RecipeParser
import org.example.logic.useCases.*
import org.example.presentation.HolderCLi

fun main() {
    val filePath = "src/main/kotlin/data/food.csv"
    val csvFileReader = CsvFileReader(filePath)
    val recipeParser = RecipeParser()
    val repository = CsvRecipesRepository(csvFileReader, recipeParser)
    val sweetsWithNoEggsUseCase = SweetsWithNoEggsUseCase(repository)
    val getHealthyFastFoodMealsUseCase = GetHealthyFastFoodMealsUseCase(repository)
    val guessGameUseCase = GuessGameUseCase(repository)
    val getSeaFoodRankingByProteinUseCase = GetSeaFoodRankingByProteinUseCase(repository)
    val suggestItalianMealsForLargeGroupsUseCase = SuggestItalianMealsForLargeGroupsUseCase(repository)
    val holderCli = HolderCLi(
        sweetsWithNoEggsUseCase,
        getHealthyFastFoodMealsUseCase,
        guessGameUseCase,
        getSeaFoodRankingByProteinUseCase,
        suggestItalianMealsForLargeGroupsUseCase
    )
    holderCli.startCLI()
    val useCaseHolder = UseCaseHolder(repository)
    val cli = HolderCLi().startCLI(useCaseHolder)
}