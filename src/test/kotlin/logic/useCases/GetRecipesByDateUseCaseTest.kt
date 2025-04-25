package logic.useCases

import com.google.common.truth.Truth.assertThat
import dummyData.DummyRecipes
import io.mockk.every
import io.mockk.mockk
import logic.customExceptions.NoRecipeFoundException
import org.example.logic.RecipesRepository
import org.example.logic.useCases.GetRecipesByDateUseCase
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.assertThrows
import java.time.format.DateTimeParseException
import kotlin.test.Test

class GetRecipesByDateUseCaseTest {
    private var recipesRepository: RecipesRepository = mockk()
    private lateinit var getRecipesByDateUseCase: GetRecipesByDateUseCase

    @BeforeEach
    fun setUp() {
        getRecipesByDateUseCase = GetRecipesByDateUseCase(recipesRepository)
    }

    @Test
    fun `should throwDateTimeParseException when date format is invalid`() {
        // Given
        val inValidDate = "invalid-date"
        every { recipesRepository.getRecipes() } returns emptyList()

        // When & Then
        assertThrows<DateTimeParseException> {
            getRecipesByDateUseCase.getRecipesByDate(inValidDate)
        }
    }

    @Test
    fun `should throwNoRecipeFoundException when no recipes exist`() {
        // Given
        val date = "2005-09-11"
        every { recipesRepository.getRecipes() } returns emptyList()

        // When & Then
        assertThrows<NoRecipeFoundException> {
            getRecipesByDateUseCase.getRecipesByDate(date)
        }
    }


    @Test
    fun `should return recipes when date matches`() {
        // Given
        val validDate = "2005-09-11"
        every { recipesRepository.getRecipes() } returns DummyRecipes.searchByDateRecipes

        // When
        val filteredRecipes = getRecipesByDateUseCase.getRecipesByDate(validDate)

        // Then
        assertThat(filteredRecipes).hasSize(3)
    }

    @Test
    fun `should return recipe details when valid ID is provided`() {
        // Given
        val validId = "65816"
        val validDate = "2005-09-11"
        every { recipesRepository.getRecipes() } returns DummyRecipes.searchByDateRecipes

        // When
        getRecipesByDateUseCase.getRecipesByDate(validDate)
        val filteredRecipes = getRecipesByDateUseCase.getFullRecipeById(validId)

        // Then
        assertThat(filteredRecipes!!.id).isEqualTo(validId)
    }

    @Test
    fun `should return null when invalid ID is provided`() {
        // Given
        val inValidId = "invalid_id"
        val validDate = "2005-09-11"
        every { recipesRepository.getRecipes() } returns DummyRecipes.searchByDateRecipes

        // When
        getRecipesByDateUseCase.getRecipesByDate(validDate)
        val filteredRecipes = getRecipesByDateUseCase.getFullRecipeById(inValidId)

        // Then
        assertThat(filteredRecipes).isNull()
    }



}