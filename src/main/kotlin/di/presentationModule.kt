package di

import ExploreRecipesByCountryCli
import IraqiRecipesCli
import org.example.presentation.*
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val presentationModule = module {

    single { GetHealthyFoodMealsCLI(get()) }

    single { SeaFoodRankingCLI(get()) }

    single { GetSuggestItalianRecipesForLargeGroupsCLI(get()) }

    single { GetRandomEasyRecipesCLi(get()) }

    single { GetKetoDietRecipeHelperCLI(get()) }

    single { IraqiRecipesCli(get()) }

    single { GetHighCalorieCli(get()) }

    single { ExploreRecipesByCountryCli(get()) }

    single { GetLovePotatoCLI(get()) }

    single { GymMealsCLI(get()) }

    single { SweetsWithNoEggsCLi(get()) }

    single { SearchFoodByDateCLI(get()) }

    single { SearchMealsByNameCLI(get()) }

    single { IngredientGameCLI(get()) }

    singleOf(::HolderCLi)
}