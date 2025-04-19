package org.example.dependencyInjection


import LovePotatoUseCase
import org.example.logic.useCases.*
import org.koin.dsl.module

val useCaseModule = module {
    single { SweetsWithNoEggsUseCase(get()) }
    single { GetHealthyFastFoodMealsUseCase(get()) }
    single { GuessGameUseCase(get()) }
    single { GetSeaFoodRankingByProteinUseCase(get()) }
    single { SuggestItalianMealsForLargeGroupsUseCase(get()) }
    single { SuggestMealsUseCases(get()) }
    single { GetIraqiMealsUseCase(get()) }
    single { GetHighCalorieUseCase(get()) }
    single { ExploreOtherCountriesFoodUseCase(get()) }
    single { LovePotatoUseCase(get()) }
    single { GymMealsUseCase(get()) }
    single { IngredientGameUseCase(get()) }
    single { SearchFoodByAddDateUseCase(get()) }
    single { SearchWithKmpUseCase(get()) }
    single { GetKetoMealsUseCase(get()) }
}