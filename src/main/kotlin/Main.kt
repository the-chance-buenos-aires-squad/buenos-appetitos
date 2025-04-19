package org.example


import GetLovePotatoUseCase
import org.example.data.CsvFileReader
import org.example.data.CsvRecipesRepository
import org.example.data.RecipeParser
import org.example.logic.useCases.*
import org.example.presentation.GetHealthyFoodMealsCLI
import org.example.presentation.HolderCLi
import org.example.presentation.SearchFoodByDateCLI


import org.example.presentation.*

import java.io.File

fun main() {
    val filePath = "src/main/kotlin/data/food.csv"
    val file = File(filePath)
    val csvFileReader = CsvFileReader(file)
    val recipeParser = RecipeParser()
    val repository = CsvRecipesRepository(csvFileReader, recipeParser)
    val sweetsWithNoEggsUseCase = SweetsWithNoEggsUseCase(repository)
    val getHealthyFastFoodMealsUseCase = GetHealthyFastFoodMealsUseCase(repository)
    val guessGameUseCase = GuessGameUseCase(repository)
    val getSeaFoodRankingByProteinUseCase = GetSeaFoodRankingByProteinUseCase(repository)
    val suggestItalianMealsForLargeGroupsUseCase = SuggestItalianRecipesForLargeGroupsUseCase(repository)
    val suggestMealsUseCases = SuggestMealsUseCases(repository)
    val searchFoodByAddDateUseCase = SearchFoodByAddDateUseCase(repository)
    val getIraqiMealsUseCase = GetIraqiMealsUseCase(repository)
    val getHighCalorieUseCase = GetHighCalorieUseCase(repository)
    val exploreOtherCountriesFoodUseCase = ExploreOtherCountriesFoodUseCase(repository)
    val lovePotatoUseCase = GetLovePotatoUseCase(repository)
    val gymMealsUseCase = GymMealsUseCase(repository)
    val ingredientGameUseCase = IngredientGameUseCase(repository)
    val kmpSearchUseCase = KmpSearchUseCase()
    val fuzzySearchUseCase = FuzzySearchUseCase()

    val searchMealsByNameUseCase = SearchRecipesByNameUseCase(repository, fuzzySearchUseCase, kmpSearchUseCase)

    val searchFoodByDateCLI = SearchFoodByDateCLI(searchFoodByAddDateUseCase)
    val getHealthyFoodMealsCLI = GetHealthyFoodMealsCLI(getHealthyFastFoodMealsUseCase)
    val highCalorieCli = GetHighCalorieCli(getHighCalorieUseCase)
    val lovePotatoCLI = GetLovePotatoCLI(lovePotatoUseCase)
    val searchMealsByNameCLI = SearchMealsByNameCLI(searchMealsByNameUseCase)
    val seaFoodRankingCLI = SeaFoodRankingCLI(getSeaFoodRankingByProteinUseCase)
    val suggestItalianRecipesForLargeGroupsCLI = GetSuggestItalianRecipesForLargeGroupsCLI(suggestItalianMealsForLargeGroupsUseCase)

    val holderCli = HolderCLi(
        searchFoodByDateCLI,
        getHealthyFoodMealsCLI,
        searchMealsByNameCLI,
        guessGameUseCase,
        seaFoodRankingCLI,
        suggestItalianRecipesForLargeGroupsCLI,
        suggestMealsUseCases,
        sweetsWithNoEggsUseCase,
        getIraqiMealsUseCase,
        highCalorieCli,
        exploreOtherCountriesFoodUseCase,
        lovePotatoCLI,
        gymMealsUseCase,
        ingredientGameUseCase
    )
    holderCli.startCLI()

}