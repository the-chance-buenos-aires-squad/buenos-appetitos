package org.example.presentation

import dummyData.DummyRecipes
import dummyData.createDummyRecipe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import logic.customExceptions.NoRecipeFoundException
import org.example.logic.useCases.GetHighCalorieUseCase
import org.example.presentation.displyUtils.displayDetails
import org.example.presentation.displyUtils.displayRecipeNameDescription
import org.example.presentation.uiController.UiController
import java.time.LocalDate

import kotlin.test.Test

class GetHighCalorieCliTest {

    private val uiController: UiController = mockk(relaxed = true)
    private val getHighCalorieUseCase: GetHighCalorieUseCase = mockk(relaxed = true)

    private val highCalorieCli: GetHighCalorieCli = GetHighCalorieCli(uiController, getHighCalorieUseCase)


    @Test
    fun `should display menu and high calorie recipe when CLI starts`() {

        // Given
        every { getHighCalorieUseCase.suggestRandomHighCalorieRecipe() } returns DummyRecipes.firstHighCalorieRecipe

        // When
        highCalorieCli.start()

        // Then
        verify {
            uiController.printMessage("\n--------High Calories Recipe----------")
            DummyRecipes.firstHighCalorieRecipe.displayRecipeNameDescription()
            uiController.printMessage("1 - Like show me recipe details")
            uiController.printMessage("2 - Dislike (show another recipe)")
            uiController.printMessage("3 - Exit")
        }

    }

    @Test
    fun `should display recipe details when user selects like`() {

        // Given
        val userInputLike = "1"
        every { getHighCalorieUseCase.suggestRandomHighCalorieRecipe() } returns DummyRecipes.firstHighCalorieRecipe
        every { uiController.readInput() } returns userInputLike

        // When
        highCalorieCli.start()

        // Then
        verify {
            uiController.printMessage("good here are the details >>>>>  \n${DummyRecipes.firstHighCalorieRecipe.displayDetails()}")
        }

    }

    @Test
    fun `should suggest new high calorie recipe when user selects dislike`() {

        // Given
        val userInputLike = "1"
        val userInputDislike = "2"
        every { getHighCalorieUseCase.suggestRandomHighCalorieRecipe() } returns DummyRecipes.firstHighCalorieRecipe andThen DummyRecipes.secondHighCalorieRecipe
        every { uiController.readInput() } returns userInputDislike andThen userInputLike

        // When
        highCalorieCli.start()

        // Then
        verify {
            DummyRecipes.firstHighCalorieRecipe.displayRecipeNameDescription()
            uiController.printMessage("Okay, let's try another one!")
            DummyRecipes.secondHighCalorieRecipe.displayRecipeNameDescription()
            uiController.printMessage("----------------------------")
            uiController.printMessage("1 - Like show me recipe details")
            uiController.printMessage("2 - Dislike (show another recipe)")
            uiController.printMessage("3 - Exit")
            uiController.printMessage("Please choose (1-3): ",true)
            uiController.printMessage("good here are the details >>>>>  \n${DummyRecipes.secondHighCalorieRecipe.displayDetails()}")
        }
    }

    @Test
    fun `should call getHighCalorieRecipe twice when user selects dislike once`() {

        // Given
        val userInputLike = "1"
        val userInputDislike = "2"
        every { getHighCalorieUseCase.suggestRandomHighCalorieRecipe() } returns DummyRecipes.firstHighCalorieRecipe andThen DummyRecipes.secondHighCalorieRecipe
        every { uiController.readInput() } returns userInputDislike andThen userInputLike

        // When
        highCalorieCli.start()

        //then
        verify(exactly = 2) { getHighCalorieUseCase.suggestRandomHighCalorieRecipe() }
    }

    @Test
    fun `should terminate feature when user selects exit`() {
        // Given
        every { getHighCalorieUseCase.suggestRandomHighCalorieRecipe() } returns DummyRecipes.firstHighCalorieRecipe
        every { uiController.readInput() } returns "invalid"

        // When
        highCalorieCli.start()

        // Then
        verify(exactly = 1) {
            uiController.printMessage("Invalid input...")
            uiController.printMessage("Exiting...")
        }
    }

    @Test
    fun `should display error message when throws NoRecipeFoundException`() {
        // Given
        every { getHighCalorieUseCase.suggestRandomHighCalorieRecipe() } throws NoRecipeFoundException()

        // When
        highCalorieCli.start()

        // Then
        verify { uiController.printMessage("No recipe found") }
    }

    @Test
    fun `should display error message when throws RuntimeException`() {
        // Given
        every { getHighCalorieUseCase.suggestRandomHighCalorieRecipe() } throws RuntimeException()

        // When
        highCalorieCli.start()

        // Then
        verify { uiController.printMessage("sorry there is no more recipes") }
    }
}