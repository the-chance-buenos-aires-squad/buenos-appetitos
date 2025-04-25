package logic.useCases

import com.google.common.truth.Truth.assertThat
import dummyData.DummyRecipes
import io.mockk.every
import io.mockk.mockk
import org.example.logic.RecipesRepository
import org.example.logic.customExceptions.NoRecipeFoundByDateException
import org.example.logic.useCases.GetRecipesByDateUseCase
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.assertThrows
import java.time.LocalDate
import kotlin.test.Test

class GetRecipesByDateUseCaseTest {
    private var recipesRepository: RecipesRepository = mockk()
    private lateinit var getRecipesByDateUseCase: GetRecipesByDateUseCase

    @BeforeEach
    fun setUp() {
        getRecipesByDateUseCase = GetRecipesByDateUseCase(recipesRepository)
    }

    @Test
    fun `should throwNoRecipeFoundException when no recipes exist`() {
        // Given
        val date = LocalDate.parse("2005-09-11")
        every { recipesRepository.getRecipes() } returns emptyList()

        // When & Then
        assertThrows<NoRecipeFoundByDateException> {
            getRecipesByDateUseCase.getRecipesByDate(date)
        }
    }

    @Test
    fun `should return recipes when date matches`() {
        // Given
        val validFormatDate = LocalDate.parse("2005-09-11")
        every { recipesRepository.getRecipes() } returns DummyRecipes.searchByDateRecipes

        // When
        val filteredRecipes = getRecipesByDateUseCase.getRecipesByDate(validFormatDate)

        // Then
        assertThat(filteredRecipes).hasSize(3)
    }

    @Test
    fun `should return recipe details when valid ID is provided`() {
        // Given
        val validId = "65816"
        val validFormatDate = LocalDate.parse("2005-09-11")
        every { recipesRepository.getRecipes() } returns DummyRecipes.searchByDateRecipes

        // When
        getRecipesByDateUseCase.getRecipesByDate(validFormatDate)
        val filteredRecipes = getRecipesByDateUseCase.getFullRecipeById(validId)

        // Then
        assertThat(requireNotNull(filteredRecipes).id).isEqualTo(validId)
    }

    @Test
    fun `should return null when invalid ID is provided`() {
        // Given
        val inValidId = "invalid_id"
        val validFormatDate = LocalDate.parse("2005-09-11")
        every { recipesRepository.getRecipes() } returns DummyRecipes.searchByDateRecipes

        // When
        getRecipesByDateUseCase.getRecipesByDate(validFormatDate)
        val filteredRecipes = getRecipesByDateUseCase.getFullRecipeById(inValidId)

        // Then
        assertThat(filteredRecipes).isNull()
    }

}