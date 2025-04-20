package org.example


import ExploreRecipesByCountryCli
import ExploreRecipesByCountryUseCase
import GetLovePotatoUseCase
import IdentifyIraqiRecipesUseCase
import IraqiRecipesCli
import org.example.data.CsvFileReader
import org.example.data.CsvRecipesRepository
import org.example.data.RecipeParser
import org.example.logic.useCases.*
import org.example.presentation.GetRandomEasyRecipesCLi
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

    val searchFoodByAddDateUseCase = SearchFoodByAddDateUseCase(repository)
    val searchFoodByDateCLI = SearchFoodByDateCLI(searchFoodByAddDateUseCase)

    val getHealthyFastFoodMealsUseCase = GetHealthyFastFoodMealsUseCase(repository)
    val getHealthyFoodMealsCLI = GetHealthyFoodMealsCLI(getHealthyFastFoodMealsUseCase)

    val kmpSearchUseCase = KmpSearchUseCase()
    val fuzzySearchUseCase = FuzzySearchUseCase()
    val searchMealsByNameUseCase = SearchRecipesByNameUseCase(repository, fuzzySearchUseCase, kmpSearchUseCase)
    val searchMealsByNameCLI = SearchMealsByNameCLI(searchMealsByNameUseCase)

    val guessGameUseCase = GuessGameUseCase(repository)

    val getSeaFoodRankingByProteinUseCase = GetSeaFoodRankingByProteinUseCase(repository)
    val seaFoodRankingCLI = SeaFoodRankingCLI(getSeaFoodRankingByProteinUseCase)

    val suggestItalianMealsForLargeGroupsUseCase = SuggestItalianRecipesForLargeGroupsUseCase(repository)
    val suggestItalianRecipesForLargeGroupsCLI = GetSuggestItalianRecipesForLargeGroupsCLI(suggestItalianMealsForLargeGroupsUseCase)

    val getRandomEasyRecipesUseCase = GetRandomEasyRecipesUseCase(repository)
    val getRandomEasyRecipesCLi = GetRandomEasyRecipesCLi(getRandomEasyRecipesUseCase)

    val sweetsWithNoEggsUseCase = SweetsWithNoEggsUseCase(repository)

    val getHighCalorieUseCase = GetHighCalorieUseCase(repository)
    val highCalorieCli = GetHighCalorieCli(getHighCalorieUseCase)

    val lovePotatoUseCase = GetLovePotatoUseCase(repository)
    val lovePotatoCLI = GetLovePotatoCLI(lovePotatoUseCase)

    val gymMealsUseCase = GymMealsUseCase(repository)
    val gymMealsCLI = GymMealsCLI(gymMealsUseCase)

    val ingredientGameUseCase = IngredientGameUseCase(repository)

    val exploreRecipesByCountryUseCase = ExploreRecipesByCountryUseCase(repository)
    val exploreRecipesByCountryCli = ExploreRecipesByCountryCli(exploreRecipesByCountryUseCase)

    val getIraqiMealsUseCase = IdentifyIraqiRecipesUseCase(repository)
    val iraqiRecipesCli = IraqiRecipesCli(getIraqiMealsUseCase)

    val getKetoRecipeUseCase=GetKetoRecipeUseCase(repository)
    val getKetoDietRecipeHelperCLI = GetKetoDietRecipeHelperCLI(getKetoRecipeUseCase)

    val holderCli = HolderCLi(
        searchFoodByDateCLI,
        getHealthyFoodMealsCLI,
        searchMealsByNameCLI,
        guessGameUseCase,
        seaFoodRankingCLI,
        suggestItalianRecipesForLargeGroupsCLI,
        getRandomEasyRecipesCLi,
        sweetsWithNoEggsUseCase,
        highCalorieCli,
        lovePotatoCLI,
        gymMealsCLI,
        ingredientGameUseCase,
        exploreRecipesByCountryCli,
        iraqiRecipesCli,
        getKetoDietRecipeHelperCLI
    )

    holderCli.startCLI()
}