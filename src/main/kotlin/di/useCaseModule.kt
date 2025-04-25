package di

import logic.useCases.ExploreRecipesByCountryUseCase
import logic.useCases.GetLovePotatoUseCase
import logic.useCases.IdentifyIraqiRecipesUseCase
import org.example.logic.useCases.*
import org.koin.dsl.module

val useCaseModule = module {

    single { SweetsWithNoEggsUseCase(get()) }

    single { GetHealthyFastFoodMealsUseCase(get()) }

    single { GuessGameUseCase(get()) }

    single { GetSeaFoodRankingByProteinUseCase(get()) }

    single { SuggestItalianRecipesForLargeGroupsUseCase(get()) }

    single { GetRandomEasyRecipesUseCase(get()) }

    single { GetKetoRecipeUseCase(get()) }

    single { IdentifyIraqiRecipesUseCase(get()) }

    single { GetHighCalorieUseCase(get()) }

    single { ExploreRecipesByCountryUseCase(get()) }

    single { GetLovePotatoUseCase(get()) }

    single { GymMealsUseCase(get()) }

    single { IngredientGameUseCase(get()) }

    single { GetRecipesByDateUseCase(get()) }

    single { KmpSearchUseCase() }
    single { FuzzySearchUseCase() }
    single { SearchRecipesByNameUseCase(get(), get(), get()) }

}