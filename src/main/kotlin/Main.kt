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
    val sweetsWithNoEggsUseCase = SweetsWithNoEggsUseCase(repository)
    val getHealthyFastFoodMealsUseCase = GetHealthyFastFoodMealsUseCase(repository)
    val guessGameUseCase = GuessGameUseCase(repository)
    val getSeaFoodRankingByProteinUseCase = GetSeaFoodRankingByProteinUseCase(repository)
    val suggestItalianMealsForLargeGroupsUseCase = SuggestItalianRecipesForLargeGroupsUseCase(repository)
    val suggestMealsUseCases = SuggestMealsUseCases(repository)
    val searchFoodByAddDateUseCase = SearchFoodByAddDateUseCase(repository)
    val getIraqiMealsUseCase = IdentifyIraqiRecipesUseCase(repository)
    val getHighCalorieUseCase = GetHighCalorieUseCase(repository)
    val getKetoRecipeUseCase=GetKetoRecipeUseCase(repository)
    val lovePotatoUseCase = GetLovePotatoUseCase(repository)
    val gymMealsUseCase = GymMealsUseCase(repository)
    val ingredientGameUseCase = IngredientGameUseCase(repository)
    val kmpSearchUseCase = KmpSearchUseCase()
    val fuzzySearchUseCase = FuzzySearchUseCase()
    val searchMealsByNameUseCase = SearchRecipesByNameUseCase(repository, fuzzySearchUseCase, kmpSearchUseCase)
    val exploreRecipesByCountryUseCase = ExploreRecipesByCountryUseCase(repository)

    val searchFoodByDateCLI = SearchFoodByDateCLI(searchFoodByAddDateUseCase)
    val getHealthyFoodMealsCLI = GetHealthyFoodMealsCLI(getHealthyFastFoodMealsUseCase)
    val highCalorieCli = GetHighCalorieCli(getHighCalorieUseCase)
    val lovePotatoCLI = GetLovePotatoCLI(lovePotatoUseCase)
    val searchMealsByNameCLI = SearchMealsByNameCLI(searchMealsByNameUseCase)
    val getKetoDietRecipeHelperCLI = GetKetoDietRecipeHelperCLI(getKetoRecipeUseCase)
    val exploreRecipesByCountryCli = ExploreRecipesByCountryCli(exploreRecipesByCountryUseCase)
    val iraqiRecipesCli = IraqiRecipesCli(getIraqiMealsUseCase)
    val suggestItalianMealsForLargeGroupsUseCase = SuggestItalianMealsForLargeGroupsUseCase(repository)

    val getRandomEasyRecipesUseCase = GetRandomEasyRecipesUseCase(repository)
    val getRandomEasyRecipesCLi = GetRandomEasyRecipesCLi(getRandomEasyRecipesUseCase)

    val getIraqiMealsUseCase = GetIraqiMealsUseCase(repository)
    val highCalorieUseCase = HighCalorieUseCase(repository)
    val exploreOtherCountriesFoodUseCase = ExploreOtherCountriesFoodUseCase(repository)
    val lovePotatoUseCase = LovePotatoUseCase(repository)
    val  gymMealsUseCase= GymMealsUseCase(repository)
    val ingredientGameUseCase=IngredientGameUseCase(repository)

    val holderCli = HolderCLi(
        searchFoodByDateCLI,
        getHealthyFoodMealsCLI,
        searchMealsByNameCLI,
        guessGameUseCase,
        getSeaFoodRankingByProteinUseCase,
        suggestItalianMealsForLargeGroupsUseCase,

        getRandomEasyRecipesCLi,

        getIraqiMealsUseCase,
        highCalorieUseCase,
        exploreOtherCountriesFoodUseCase,
        lovePotatoUseCase,
        suggestMealsUseCases,
        sweetsWithNoEggsUseCase,
        highCalorieCli,
        lovePotatoCLI,
        gymMealsUseCase,
        ingredientGameUseCase,
        exploreRecipesByCountryCli,
        iraqiRecipesCli,
        getKetoDietRecipeHelperCLI

    )

    holderCli.startCLI()
}