package org.example.presentation

import dummyData.DummyRecipes
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import logic.customExceptions.NoRecipeFoundException
import logic.useCases.SweetsWithNoEggsUseCase
import org.example.presentation.displyUtils.displayDetails
import org.example.presentation.displyUtils.displayRecipeNameDescription
import org.example.presentation.uiController.UiController
import org.junit.jupiter.api.Assertions.*
import kotlin.test.Test

class SweetsWithNoEggsCLiTest {

    private val uiController: UiController = mockk(relaxed = true)
    private val sweetsWithNoEggsUseCase: SweetsWithNoEggsUseCase = mockk(relaxed = true)
    private val sweetsWithNoEggsCLi: SweetsWithNoEggsCLi = SweetsWithNoEggsCLi(uiController, sweetsWithNoEggsUseCase)

    @Test
    fun `should display menu and Sweet without egg when CLI starts`() {

        // Given
        every { sweetsWithNoEggsUseCase.suggestSweetNoEggRecipe() } returns DummyRecipes.firstEggFreeSweet

        // When
        sweetsWithNoEggsCLi.start()

        // Then
        verify {
            uiController.printMessage("\n------ Sweet without egg -----")
            DummyRecipes.firstEggFreeSweet.displayRecipeNameDescription()
            uiController.printMessage("1 - Like show me recipe details")
            uiController.printMessage("2 - Dislike (show another recipe)")
            uiController.printMessage("3 - Exit")
        }

    }

    @Test
    fun `should display recipe details when user selects like`() {

        // Given
        val userInputLike = "1"
        every { sweetsWithNoEggsUseCase.suggestSweetNoEggRecipe() } returns DummyRecipes.firstEggFreeSweet
        every { uiController.readInput() } returns userInputLike

        // When
        sweetsWithNoEggsCLi.start()

        // Then
        verify {
            uiController.printMessage("good here are the details >>>>>  \n${DummyRecipes.firstEggFreeSweet.displayDetails()}")
        }

    }

    @Test
    fun `should suggest new dessert without egg when user selects dislike`() {

        // Given
        val userInputLike = "1"
        val userInputDislike = "2"
        every { sweetsWithNoEggsUseCase.suggestSweetNoEggRecipe() } returns DummyRecipes.firstEggFreeSweet andThen DummyRecipes.secondEggFreeSweet
        every { uiController.readInput() } returns userInputDislike andThen userInputLike

        // When
        sweetsWithNoEggsCLi.start()

        // Then
        verify {
            DummyRecipes.firstEggFreeSweet.displayRecipeNameDescription()
            uiController.printMessage("Okay, let's try another one!")
            DummyRecipes.secondEggFreeSweet.displayRecipeNameDescription()
            uiController.printMessage("----------------------------")
            uiController.printMessage("1 - Like show me recipe details")
            uiController.printMessage("2 - Dislike (show another recipe)")
            uiController.printMessage("3 - Exit")
            uiController.printMessage("Please choose (1-3): ", true)
            uiController.printMessage("good here are the details >>>>>  \n${DummyRecipes.secondEggFreeSweet.displayDetails()}")
        }

    }

    @Test
    fun `should call suggestSweetNoEggRecipe twice when user selects dislike once`() {

        // Given
        val userInputLike = "1"
        val userInputDislike = "2"
        every { sweetsWithNoEggsUseCase.suggestSweetNoEggRecipe() } returns DummyRecipes.firstEggFreeSweet andThen DummyRecipes.secondEggFreeSweet
        every { uiController.readInput() } returns userInputDislike andThen userInputLike

        // When
        sweetsWithNoEggsCLi.start()

        //then
        verify(exactly = 2) { sweetsWithNoEggsUseCase.suggestSweetNoEggRecipe() }

    }

    @Test
    fun `should terminate feature when user selects exit`() {

        // Given
        every { sweetsWithNoEggsUseCase.suggestSweetNoEggRecipe() } returns DummyRecipes.firstHighCalorieRecipe
        every { uiController.readInput() } returns "invalid"

        // When
        sweetsWithNoEggsCLi.start()

        // Then
        verify(exactly = 1) {
            uiController.printMessage("Invalid input...")
            uiController.printMessage("Exiting...")
        }

    }

    @Test
    fun `should display error message when throws NoRecipeFoundException`() {
        // Given
        every { sweetsWithNoEggsUseCase.suggestSweetNoEggRecipe() } throws NoRecipeFoundException()

        // When
        sweetsWithNoEggsCLi.start()

        // Then
        verify { uiController.printMessage("No recipe found") }
    }

    @Test
    fun `should display error message when throws RuntimeException`() {

        // Given
        every { sweetsWithNoEggsUseCase.suggestSweetNoEggRecipe() } throws RuntimeException()

        // When
        sweetsWithNoEggsCLi.start()

        // Then
        verify { uiController.printMessage("sorry there is no more recipes") }

    }

}