package presentation

import IraqiRecipesCli
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.example.presentation.*
import org.example.presentation.uiController.UiController
import kotlin.test.Test


class HolderCLiTest {

    private val searchFoodByAddDateClI: SearchFoodByDateCLI = mockk(relaxed = true)
    private val healthyFoodMealsCLI: GetHealthyFoodMealsCLI = mockk(relaxed = true)
    private val guessGameCli: GuessGameCli = mockk(relaxed = true)
    private val searchMealsByNameCLI: SearchMealsByNameCLI = mockk(relaxed = true)
    private val seaFoodRankingCLI: SeaFoodRankingCLI = mockk(relaxed = true)
    private val suggestItalianRecipesForLargeGroupsCLI: GetSuggestItalianRecipesForLargeGroupsCLI = mockk(relaxed = true)
    private val getRandomEasyRecipesCli: GetRandomEasyRecipesCLi = mockk(relaxed = true)
    private val sweetsWithNoEggsCLi: SweetsWithNoEggsCLi = mockk(relaxed = true)
    private val highCalorieCli: GetHighCalorieCli = mockk(relaxed = true)
    private val getLovePotatoCLI: GetLovePotatoCLI = mockk(relaxed = true)
    private val gymMealsCLI: GymMealsCLI = mockk(relaxed = true)
    private val ingredientGameCLI: IngredientGameCLI = mockk(relaxed = true)
    private val exploreRecipesByCountryCli: ExploreRecipesByCountryCli = mockk(relaxed = true)
    private val iraqiRecipesCli: IraqiRecipesCli = mockk(relaxed = true)
    private val getKetoDietRecipeHelperCLI: GetKetoDietRecipeHelperCLI = mockk(relaxed = true)


    private val uiController: UiController = mockk(relaxed = true)
    private val cli: HolderCLi = HolderCLi(
        searchFoodByAddDateClI,
        healthyFoodMealsCLI,
        guessGameCli,
        searchMealsByNameCLI,
        seaFoodRankingCLI,
        suggestItalianRecipesForLargeGroupsCLI,
        getRandomEasyRecipesCli,
        sweetsWithNoEggsCLi,
        highCalorieCli,
        getLovePotatoCLI,
        gymMealsCLI,
        ingredientGameCLI,
        exploreRecipesByCountryCli,
        iraqiRecipesCli,
        getKetoDietRecipeHelperCLI,
        uiController
    )


    @Test
    fun `testing holder`() {

        every { uiController.readInput() }.returnsMany("0")  // Simulate choosing "Exit"

        cli.startCLI()


        verify {
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
        }


    }


    @Test
    fun `testing holder2`() {

        every { uiController.readInput() }.returnsMany("1")  // Simulate choosing "Exit"

        cli.startCLI()


        verify {
            healthyFoodMealsCLI.start()
        }


    }
}