package org.example

import LovePotatoUseCase
import org.example.data.CsvFileReader
import org.example.data.CsvRecipesRepository
import org.example.data.RecipeParser
import org.example.logic.useCases.*
import org.example.presentation.GetHealthyFoodMealsCLI
import org.example.presentation.HolderCLi
import org.example.presentation.SearchFoodByDateCLI
import org.example.presentation.SearchMealsByNameCLI

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
    val suggestMealsUseCases = SuggestMealsUseCases(repository)
    val searchFoodByAddDateUseCase = SearchFoodByAddDateUseCase(repository)
    val getIraqiMealsUseCase = GetIraqiMealsUseCase(repository)
    val getHighCalorieUseCase = GetHighCalorieUseCase(repository)
    val exploreOtherCountriesFoodUseCase = ExploreOtherCountriesFoodUseCase(repository)
    val lovePotatoUseCase = LovePotatoUseCase(repository)
    val gymMealsUseCase = GymMealsUseCase(repository)
    val ingredientGameUseCase = IngredientGameUseCase(repository)
    val kmpSearchUseCase = KmpSearchUseCase()
    val fuzzySearchUseCase = FuzzySearchUseCase(kmpSearchUseCase)
    val searchMealsByNameUseCase = SearchRecipesByNameUseCase(repository, fuzzySearchUseCase,kmpSearchUseCase)


    val searchFoodByDateCLI = SearchFoodByDateCLI(searchFoodByAddDateUseCase)
    val getHealthyFoodMealsCLI = GetHealthyFoodMealsCLI(getHealthyFastFoodMealsUseCase)
    val searchMealsByNameCLI = SearchMealsByNameCLI(searchMealsByNameUseCase)

    val holderCli = HolderCLi(
        searchFoodByDateCLI,
        getHealthyFoodMealsCLI,
        searchMealsByNameCLI,
        guessGameUseCase,
        getSeaFoodRankingByProteinUseCase,
        suggestItalianMealsForLargeGroupsUseCase,
        suggestMealsUseCases,
        sweetsWithNoEggsUseCase,
        getIraqiMealsUseCase,
        getHighCalorieUseCase,
        exploreOtherCountriesFoodUseCase,
        lovePotatoUseCase,
        gymMealsUseCase,
        ingredientGameUseCase
    )
    holderCli.startCLI()

}