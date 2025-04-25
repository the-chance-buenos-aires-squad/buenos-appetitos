package logic.useCases

import com.google.common.truth.Truth.assertThat
import dummyData.DummyRecipes
import io.mockk.every
import io.mockk.mockk
import org.example.logic.RecipesRepository
import org.example.logic.useCases.SuggestItalianRecipesForLargeGroupsUseCase
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class SuggestItalianRecipesForLargeGroupsUseCaseTest {
    private lateinit var recipesRepository: RecipesRepository
    private lateinit var suggestItalianRecipesForLargeGroupsUseCase: SuggestItalianRecipesForLargeGroupsUseCase

    @BeforeEach
    fun setup() {
        recipesRepository = mockk()
        suggestItalianRecipesForLargeGroupsUseCase = SuggestItalianRecipesForLargeGroupsUseCase(recipesRepository)
    }

    @Test
    fun `should return empty list if recipe is empty`() {
        //give
        every { recipesRepository.getRecipes() } returns emptyList()
        //when
        val result = suggestItalianRecipesForLargeGroupsUseCase.getItalianRecipesForLargeGroups()
        //then
        assertThat(result).isEmpty()
    }

    @Test
    fun `should return empty list if no italian meals and there are meals for large group`() {
        //give
        every { recipesRepository.getRecipes() } returns DummyRecipes.mealsForLargeGroupButNotItalianMeal
        //when
        val result = suggestItalianRecipesForLargeGroupsUseCase.getItalianRecipesForLargeGroups()
        //then
        assertThat(result).isEmpty()
    }

    @Test
    fun `should return only four italian meals for large group if list only have four italian meals for large group`() {
        //given
        every { recipesRepository.getRecipes() } returns DummyRecipes.italianGroupRecipes
        //when
        val result = suggestItalianRecipesForLargeGroupsUseCase.getItalianRecipesForLargeGroups()
        //then
        assertThat(result).hasSize(4)
    }

    @Test
    fun `should return only three italian meals for large group if i pass 3 for function`() {
        //given
        every { recipesRepository.getRecipes() } returns DummyRecipes.italianGroupRecipes
        //when
        val result = suggestItalianRecipesForLargeGroupsUseCase.getItalianRecipesForLargeGroups(3)
        //then
        assertThat(result).hasSize(3)
    }

    @Test
    fun `should return fifty italian meals for large group if i have more than fifty`() {
        //given
        every { recipesRepository.getRecipes() } returns List(55) { DummyRecipes.italianGroupRecipes[it % DummyRecipes.italianGroupRecipes.size] }
        //when
        val result = suggestItalianRecipesForLargeGroupsUseCase.getItalianRecipesForLargeGroups()
        //then
        assertThat(result).hasSize(50)
    }

    @Test
    fun `should be case-insensitive when checking for italian for large groups meals`() {
        //given
        every { recipesRepository.getRecipes() } returns DummyRecipes.casInsensitiveMeal
        //when
        val result = suggestItalianRecipesForLargeGroupsUseCase.getItalianRecipesForLargeGroups()
        //then
        assertThat(result).hasSize(1)
    }
}