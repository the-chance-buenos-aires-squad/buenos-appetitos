package org.example.dependencyInjection


import ExploreRecipesByCountryCli
import ExploreRecipesByCountryUseCase
import GetLovePotatoUseCase
import IdentifyIraqiRecipesUseCase
import IraqiRecipesCli
import org.example.logic.useCases.*
import org.example.presentation.*
import org.koin.dsl.module

val useCaseModule = module {

    single { SweetsWithNoEggsUseCase(get()) }

    single { GetHealthyFastFoodMealsUseCase(get()) }
    single { GetHealthyFoodMealsCLI(get()) }

    single { GuessGameUseCase(get()) }

    single { GetSeaFoodRankingByProteinUseCase(get()) }
    single { SeaFoodRankingCLI(get()) }

    single { SuggestItalianRecipesForLargeGroupsUseCase(get()) }
    single { GetSuggestItalianRecipesForLargeGroupsCLI(get()) }

    single { GetRandomEasyRecipesUseCase(get()) }
    single { GetRandomEasyRecipesCLi(get()) }

    single { GetKetoRecipeUseCase(get()) }
    single { GetKetoDietRecipeHelperCLI(get()) }

    single { IdentifyIraqiRecipesUseCase(get()) }
    single { IraqiRecipesCli(get()) }

    single { GetHighCalorieUseCase(get()) }
    single { GetHighCalorieCli(get()) }

    single { ExploreRecipesByCountryUseCase(get()) }
    single { ExploreRecipesByCountryCli(get()) }

    single { GetLovePotatoUseCase(get()) }
    single { GetLovePotatoCLI(get()) }

    single { GymMealsUseCase(get()) }
    single { GymMealsCLI(get()) }

    single { IngredientGameUseCase(get()) }

    single { SearchFoodByAddDateUseCase(get()) }
    single { SearchFoodByDateCLI(get()) }

    single { KmpSearchUseCase() }
    single { FuzzySearchUseCase() }
    single { SearchRecipesByNameUseCase(get(), get(), get()) }
    single { SearchMealsByNameCLI(get()) }

}