package org.example

import HighCalorieUseCase
import LovePotatoUseCase
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
    val suggestMealsUseCases = SuggestMealsUseCases(repository)
    val getIraqiMealsUseCase = GetIraqiMealsUseCase(repository)
    val highCalorieUseCase = HighCalorieUseCase(repository)
    val exploreOtherCountriesFoodUseCase = ExploreOtherCountriesFoodUseCase(repository)
    val lovePotatoUseCase = LovePotatoUseCase(repository)
    val  gymMealsUseCase= GymMealsUseCase(repository)
    val ingredientGameUseCase=IngredientGameUseCase(repository)

    val exploreRecipesByCountryUseCase = ExploreRecipesByCountryUseCase(repository)
    val exploreRecipesByCountryCli = ExploreRecipesByCountryCli(exploreRecipesByCountryUseCase)

    val iraqiRecipesUseCase = IdentifyIraqiRecipesUseCase(repository)
    val iraqiRecipesCli = IraqiRecipesCli(iraqiRecipesUseCase)

    val holderCli = HolderCLi(
        sweetsWithNoEggsUseCase,
        getHealthyFastFoodMealsUseCase,
        guessGameUseCase,
        getSeaFoodRankingByProteinUseCase,
        suggestItalianMealsForLargeGroupsUseCase,
        suggestMealsUseCases,
        getIraqiMealsUseCase,
        highCalorieUseCase,
        exploreOtherCountriesFoodUseCase,
        lovePotatoUseCase,
        gymMealsUseCase,
        ingredientGameUseCase,
        exploreRecipesByCountryCli,
        iraqiRecipesCli
    )
    holderCli.startCLI()

}