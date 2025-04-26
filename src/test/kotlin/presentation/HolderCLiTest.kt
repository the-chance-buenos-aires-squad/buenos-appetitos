package org.example.presentation

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
        uiController,
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
    )


    @Test
    fun `should print welcome message`() {

        //when
        cli.startCLI(true)

        //then
        verify {
            uiController.printMessage("\nFood Change Mood - Menu:")
        }

    }


    @Test
    fun `should start Healthy Meals CLI on option 1`() {

        //given
        val userInput = "1"
        every { uiController.readInput() }.returns(userInput)

        //when
        cli.startCLI(true)

        //then
        verify {
            healthyFoodMealsCLI.start()
        }

    }


    @Test
    fun `should start Search By Name CLI on option 2`() {

        //given
        val userInput = "2"
        every { uiController.readInput() }.returns(userInput)

        //when
        cli.startCLI(true)

        //then
        verify {
            searchMealsByNameCLI.start()
        }

    }

    @Test
    fun `should start Iraqi Recipes CLI on option 3`() {

        //given
        val userInput = "3"
        every { uiController.readInput() }.returns(userInput)

        //when
        cli.startCLI(true)

        //then
        verify {
            iraqiRecipesCli.start()
        }

    }

    @Test
    fun `should start Random Easy Recipes CLI on option 4`() {

        //given
        val userInput = "4"
        every { uiController.readInput() }.returns(userInput)

        //when
        cli.startCLI(true)

        //then
        verify {
            getRandomEasyRecipesCli.start()
        }

    }

    @Test
    fun `should start Guess Game CLI on option 5`() {

        //given
        val userInput = "5"
        every { uiController.readInput() }.returns(userInput)

        //when
        cli.startCLI(true)

        //then
        verify {
            guessGameCli.start()
        }

    }

    @Test
    fun `should start No Egg Sweets CLI on option 6`() {

        //given
        val userInput = "6"
        every { uiController.readInput() }.returns(userInput)

        //when
        cli.startCLI(true)

        //then
        verify {
            sweetsWithNoEggsCLi.start()
        }

    }

    @Test
    fun `should start Keto Diet CLI on option 7`() {

        //given
        val userInput = "7"
        every { uiController.readInput() }.returns(userInput)

        //when
        cli.startCLI(true)

        //then
        verify {
            getKetoDietRecipeHelperCLI.start()
        }

    }

    @Test
    fun `should start Search By Date CLI on option 8`() {

        //given
        val userInput = "8"
        every { uiController.readInput() }.returns(userInput)

        //when
        cli.startCLI(true)

        //then
        verify {
            searchFoodByAddDateClI.start()
        }

    }

    @Test
    fun `should start Gym Meals CLI on option 9`() {

        //given
        val userInput = "9"
        every { uiController.readInput() }.returns(userInput)

        //when
        cli.startCLI(true)

        //then
        verify {
            gymMealsCLI.start()
        }

    }

    @Test
    fun `should start Recipes By Country CLI on option 10`() {

        //given
        val userInput = "10"
        every { uiController.readInput() }.returns(userInput)

        //when
        cli.startCLI(true)

        //then
        verify {
            exploreRecipesByCountryCli.start()
        }

    }

    @Test
    fun `should start Ingredient Game CLI on option 11`() {

        //given
        val userInput = "11"
        every { uiController.readInput() }.returns(userInput)

        //when
        cli.startCLI(true)

        //then
        verify {
            ingredientGameCLI.start()
        }

    }

    @Test
    fun `should start Potato Lovers CLI on option 12`() {

        //given
        val userInput = "12"
        every { uiController.readInput() }.returns(userInput)

        //when
        cli.startCLI(true)

        //then
        verify {
            getLovePotatoCLI.start()
        }

    }

    @Test
    fun `should start High Calorie CLI on option 13`() {

        //given
        val userInput = "13"
        every { uiController.readInput() }.returns(userInput)

        //when
        cli.startCLI(true)

        //then
        verify {
            highCalorieCli.start()
        }

    }

    @Test
    fun `should start Seafood Ranking CLI on option 14`() {

        //given
        val userInput = "14"
        every { uiController.readInput() }.returns(userInput)

        //when
        cli.startCLI(true)

        //then
        verify {
            seaFoodRankingCLI.start()
        }

    }

    @Test
    fun `should start Italian Recipes CLI on option 15`() {

        //given
        val userInput = "15"
        every { uiController.readInput() }.returns(userInput)

        //when
        cli.startCLI(true)

        //then
        verify {
            suggestItalianRecipesForLargeGroupsCLI.start()
        }

    }

    @Test
    fun `should print goodbye message on option 0`() {

        //given
        val userInput = "0"
        every { uiController.readInput() }.returns(userInput)

        //when
        cli.startCLI(true)

        //then
        verify {
            uiController.printMessage("Thank you for using buenos-appetitos App!")
        }

    }

    @Test
    fun `should print invalid option message on wrong input`() {

        //given
        val userInput = "anyThing"
        every { uiController.readInput() }.returns(userInput)

        //when
        cli.startCLI(true)

        //then
        verify {
            uiController.printMessage("Invalid option. Please try again.")
        }

    }

}