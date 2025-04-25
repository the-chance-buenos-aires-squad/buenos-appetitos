package org.example.presentation

import IraqiRecipesCli
import org.example.presentation.uiController.UiController

class HolderCLi(
    private val searchFoodByAddDateClI: SearchFoodByDateCLI,
    private val healthyFoodMealsCLI: GetHealthyFoodMealsCLI,
    private val guessGameCli: GuessGameCli,
    private val searchMealsByNameCLI: SearchMealsByNameCLI,
    private val seaFoodRankingCLI: SeaFoodRankingCLI,
    private val suggestItalianRecipesForLargeGroupsCLI: GetSuggestItalianRecipesForLargeGroupsCLI,
    private val getRandomEasyRecipesCli: GetRandomEasyRecipesCLi,
    private val sweetsWithNoEggsCLi: SweetsWithNoEggsCLi,
    private val highCalorieCli: GetHighCalorieCli,
    private val getLovePotatoCLI: GetLovePotatoCLI,
    private val gymMealsCLI: GymMealsCLI,
    private val ingredientGameCLI: IngredientGameCLI,
    private val exploreRecipesByCountryCli: ExploreRecipesByCountryCli,
    private val iraqiRecipesCli: IraqiRecipesCli,
    private val getKetoDietRecipeHelperCLI: GetKetoDietRecipeHelperCLI,
    private val uiController: UiController
) {
    fun startCLI() {

        while (true) {
            uiController.printMessage("\nFood Change Mood - Menu:")
            uiController.printMessage("1. Find healthy fast food meals")
            uiController.printMessage("2. Search meals by name")
            uiController.printMessage("3. Find Iraqi meals")
            uiController.printMessage("4. Get easy food suggestions")
            uiController.printMessage("5. Play guess game")
            uiController.printMessage("6. Find sweets with no eggs")
            uiController.printMessage("7. Keto diet helper")
            uiController.printMessage("8. Search foods by date")
            uiController.printMessage("9. Gym helper")
            uiController.printMessage("10. Explore food cultures")
            uiController.printMessage("11. Play ingredient game")
            uiController.printMessage("12. Find potato dishes")
            uiController.printMessage("13. High calorie meals")
            uiController.printMessage("14. Seafood by protein content")
            uiController.printMessage("15. Italian group meals")
            uiController.printMessage("0. Exit")

            uiController.printMessage("Enter your choice: ")

            val scanner = uiController.readInput()

            when (scanner) {
                "1" -> healthyFoodMealsCLI.start()
                "2" -> searchMealsByNameCLI.start()
                "3" -> iraqiRecipesCli.startCli()
                "4" -> getRandomEasyRecipesCli.suggestRandomRecipes()
                "5" -> guessGameCli.start()
                "6" -> sweetsWithNoEggsCLi.start()
                "7" -> getKetoDietRecipeHelperCLI.start()
                "8" -> searchFoodByAddDateClI.start()
                "9" -> gymMealsCLI.start()
                "10" -> exploreRecipesByCountryCli.startCli()
                "11" -> ingredientGameCLI.start()
                "12" -> getLovePotatoCLI.start()
                "13" -> highCalorieCli.start()
                "14" -> seaFoodRankingCLI.start()
                "15" -> suggestItalianRecipesForLargeGroupsCLI.start()
                "0" -> {
                    uiController.printMessage("Thank you for using buenos-appetitos App!")
                    return
                }

                else -> uiController.printMessage("Invalid option. Please try again.")
            }
        }
    }

    private fun runOnce(): Boolean {
        uiController.printMessage("\nFood Change Mood - Menu:")
        uiController.printMessage("1. Find healthy fast food meals")
        uiController.printMessage("2. Search meals by name")
        uiController.printMessage("3. Find Iraqi meals")
        uiController.printMessage("4. Get easy food suggestions")
        uiController.printMessage("5. Play guess game")
        uiController.printMessage("6. Find sweets with no eggs")
        uiController.printMessage("7. Keto diet helper")
        uiController.printMessage("8. Search foods by date")
        uiController.printMessage("9. Gym helper")
        uiController.printMessage("10. Explore food cultures")
        uiController.printMessage("11. Play ingredient game")
        uiController.printMessage("12. Find potato dishes")
        uiController.printMessage("13. High calorie meals")
        uiController.printMessage("14. Seafood by protein content")
        uiController.printMessage("15. Italian group meals")
        uiController.printMessage("0. Exit")

        uiController.printMessage("Enter your choice: ")
        val input = uiController.readInput()

        when (input) {
            "1" -> healthyFoodMealsCLI.start()
            "2" -> searchMealsByNameCLI.start()
            "3" -> iraqiRecipesCli.startCli()
            "4" -> getRandomEasyRecipesCli.suggestRandomRecipes()
            "5" -> guessGameCli.start()
            "6" -> sweetsWithNoEggsCLi.start()
            "7" -> getKetoDietRecipeHelperCLI.start()
            "8" -> searchFoodByAddDateClI.start()
            "9" -> gymMealsCLI.start()
            "10" -> exploreRecipesByCountryCli.startCli()
            "11" -> ingredientGameCLI.start()
            "12" -> getLovePotatoCLI.start()
            "13" -> highCalorieCli.start()
            "14" -> seaFoodRankingCLI.start()
            "15" -> suggestItalianRecipesForLargeGroupsCLI.start()
            "0" -> {
                uiController.printMessage("Thank you for using buenos-appetitos App!")
                return false // signal to stop
            }

            else -> uiController.printMessage("Invalid option. Please try again.")
        }

        return true // signal to continue
    }


}