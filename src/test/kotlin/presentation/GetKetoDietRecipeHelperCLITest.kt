package org.example.presentation

import dummyData.DummyRecipes
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import logic.customExceptions.NoRecipeFoundException
import logic.useCases.GetKetoRecipeUseCase
import org.example.presentation.displyUtils.displayDetails
import org.example.presentation.displyUtils.displayRecipeNameDescription
import org.example.presentation.uiController.UiController
import org.junit.jupiter.api.Assertions.*
import kotlin.test.Test

class GetKetoDietRecipeHelperCLITest {

    private val uiController: UiController = mockk(relaxed = true)
    private val getKetoRecipeUseCase: GetKetoRecipeUseCase = mockk(relaxed = true)
    private val getKetoDietRecipeHelperCLI: GetKetoDietRecipeHelperCLI =
        GetKetoDietRecipeHelperCLI(uiController, getKetoRecipeUseCase)

    @Test
    fun `should display menu and Keto recipes when CLI starts`() {

        // Given
        every { getKetoRecipeUseCase.suggestRandomKetoRecipe() } returns DummyRecipes.firstKetoRecipe

        // When
        getKetoDietRecipeHelperCLI.start()

        // Then
        verify {
            uiController.printMessage("\n------ Keto Recipes -----")
            DummyRecipes.firstKetoRecipe.displayRecipeNameDescription()
            uiController.printMessage("1 - Like show me recipe details")
            uiController.printMessage("2 - Dislike (show another recipe)")
            uiController.printMessage("3 - Exit")
        }

    }

    @Test
    fun `should display Keto recipes details when user selects like`() {

        // Given
        val userInputLike = "1"
        every { getKetoRecipeUseCase.suggestRandomKetoRecipe() } returns DummyRecipes.firstKetoRecipe
        every { uiController.readInput() } returns userInputLike

        // When
        getKetoDietRecipeHelperCLI.start()

        // Then
        verify {
            uiController.printMessage("good here are the details >>>>>  \n${DummyRecipes.firstKetoRecipe.displayDetails()}")
        }

    }

    @Test
    fun `should suggest new Keto recipes when user selects dislike`() {

        // Given
        val userInputLike = "1"
        val userInputDislike = "2"
        every { getKetoRecipeUseCase.suggestRandomKetoRecipe() } returns DummyRecipes.firstKetoRecipe andThen DummyRecipes.secondKetoRecipe
        every { uiController.readInput() } returns userInputDislike andThen userInputLike

        // When
        getKetoDietRecipeHelperCLI.start()

        // Then
        verify {
            DummyRecipes.firstKetoRecipe.displayRecipeNameDescription()
            uiController.printMessage("Okay, let's try another one!")
            DummyRecipes.secondKetoRecipe.displayRecipeNameDescription()
            uiController.printMessage("----------------------------")
            uiController.printMessage("1 - Like show me recipe details")
            uiController.printMessage("2 - Dislike (show another recipe)")
            uiController.printMessage("3 - Exit")
            uiController.printMessage("Please choose (1-3): ", true)
            uiController.printMessage("good here are the details >>>>>  \n${DummyRecipes.secondKetoRecipe.displayDetails()}")
        }

    }

    @Test
    fun `should call suggestRandomKetoRecipe twice when user selects dislike once`() {

        // Given
        val userInputLike = "1"
        val userInputDislike = "2"
        every { getKetoRecipeUseCase.suggestRandomKetoRecipe() } returns DummyRecipes.firstKetoRecipe andThen DummyRecipes.secondKetoRecipe
        every { uiController.readInput() } returns userInputDislike andThen userInputLike

        // When
        getKetoDietRecipeHelperCLI.start()

        //then
        verify(exactly = 2) { getKetoRecipeUseCase.suggestRandomKetoRecipe() }

    }

    @Test
    fun `should terminate feature when user selects exit`() {

        // Given
        every { getKetoRecipeUseCase.suggestRandomKetoRecipe() } returns DummyRecipes.firstHighCalorieRecipe
        every { uiController.readInput() } returns "invalid"

        // When
        getKetoDietRecipeHelperCLI.start()

        // Then
        verify(exactly = 1) {
            uiController.printMessage("Invalid input...")
            uiController.printMessage("Exiting...")
        }

    }

    @Test
    fun `should display error message when throws NoRecipeFoundException`() {
        // Given
        every { getKetoRecipeUseCase.suggestRandomKetoRecipe() } throws NoRecipeFoundException()

        // When
        getKetoDietRecipeHelperCLI.start()

        // Then
        verify { uiController.printMessage("No recipe found") }
    }

    @Test
    fun `should display error message when throws RuntimeException`() {

        // Given
        every { getKetoRecipeUseCase.suggestRandomKetoRecipe() } throws RuntimeException()

        // When
        getKetoDietRecipeHelperCLI.start()

        // Then
        verify { uiController.printMessage("sorry there is no more recipes") }

    }

}