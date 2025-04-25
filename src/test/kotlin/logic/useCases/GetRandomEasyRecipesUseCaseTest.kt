package logic.useCases

import com.google.common.truth.Truth.assertThat
import dummyData.DummyRecipes.allRecipesAccepted
import dummyData.DummyRecipes.nonRecipesAccepted
import dummyData.DummyRecipes.testRecipesCorrectCase
import io.mockk.every
import io.mockk.mockk
import org.example.logic.RecipesRepository
import org.example.logic.useCases.GetRandomEasyRecipesUseCase
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class GetRandomEasyRecipesUseCaseTest {
    private lateinit var repository: RecipesRepository
    private lateinit var getRandomEasyRecipesUseCase: GetRandomEasyRecipesUseCase

    @BeforeEach
    fun setUp(){
        repository = mockk(relaxed = true)
        getRandomEasyRecipesUseCase = GetRandomEasyRecipesUseCase(repository)
    }

    @Test
    fun `should return correct recipes when all recipes having less than or equal max-minutes 30, max-ingredients 5 and max-steps 6`() {
        // Given
        every { repository.getRecipes() } returns testRecipesCorrectCase
        // When
        val resultRecipes = getRandomEasyRecipesUseCase.suggestEasyRecipes()
        // Then
        assertThat(resultRecipes.size).isEqualTo(5)
    }

    @Test
    fun `should return correct number of recipes when there is more than 10 recipes follows the condition`() {
        // Given
        every { repository.getRecipes() } returns allRecipesAccepted
        // When
        val resultRecipes = getRandomEasyRecipesUseCase.suggestEasyRecipes()
        // Then
        assertThat(resultRecipes.size).isEqualTo(GetRandomEasyRecipesUseCase.TEN_RANDOM_EASY_RECIPES)
    }

    @Test
    fun `should return empty list of recipes when there is no recipe meet the conditions`() {
        // Given
        every { repository.getRecipes() } returns nonRecipesAccepted
        // When
        val resultRecipes = getRandomEasyRecipesUseCase.suggestEasyRecipes()
        // Then
        assertThat(resultRecipes.size).isEqualTo(0)
    }
} 
