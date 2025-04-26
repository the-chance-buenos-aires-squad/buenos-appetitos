package di

import IraqiRecipesCli
import org.example.presentation.*
import org.example.presentation.uiController.UiController
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val presentationModule = module {

    single { GetHealthyFoodMealsCLI(get()) }

    single { SeaFoodRankingCLI(get()) }

    single { GetSuggestItalianRecipesForLargeGroupsCLI(get()) }

    single { GetRandomEasyRecipesCLi(get()) }

    single { GetKetoDietRecipeHelperCLI(get(),get()) }

    single { IraqiRecipesCli(get()) }

    single { GetHighCalorieCli(get(),get()) }

    single { ExploreRecipesByCountryCli(get()) }

    single { GetLovePotatoCLI(get()) }

    single { GymMealsCLI(get()) }

    single { SweetsWithNoEggsCLi(get(),get()) }

    single { SearchFoodByDateCLI(get()) }

    single { SearchMealsByNameCLI(get()) }

    single { IngredientGameCLI(get()) }

    single { GuessGameCli(get()) }
    single { UiController() }

    singleOf(::HolderCLi)
}