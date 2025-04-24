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
        recipesRepository = mockk(relaxed = true)
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
        every { recipesRepository.getRecipes() } returns listOf(
            DummyRecipes.italianGroupRecipes[2],
            DummyRecipes.italianGroupRecipes[3]
        )
        //when
        val result = suggestItalianRecipesForLargeGroupsUseCase.getItalianRecipesForLargeGroups()
        //then
        assertThat(result).isEmpty()
    }

    @Test
    fun `should return empty list if there are italian meals but not meals for large group`() {
        //give
        every { recipesRepository.getRecipes() } returns listOf(
            DummyRecipes.italianGroupRecipes[0],
            DummyRecipes.italianGroupRecipes[1]
        )
        //when
        val result = suggestItalianRecipesForLargeGroupsUseCase.getItalianRecipesForLargeGroups()
        //then
        assertThat(result).isEmpty()
    }

    @Test
    fun `should return only five italian meals for large group if list only have five italian meals for large group`() {
        //given
        every { recipesRepository.getRecipes() } returns DummyRecipes.italianGroupRecipes
        //when
        val result = suggestItalianRecipesForLargeGroupsUseCase.getItalianRecipesForLargeGroups()
        //then
        assertThat(result).hasSize(5)
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
        every { recipesRepository.getRecipes() } returns List(110) { DummyRecipes.italianGroupRecipes[it % DummyRecipes.italianGroupRecipes.size] }
        //when
        val result = suggestItalianRecipesForLargeGroupsUseCase.getItalianRecipesForLargeGroups()
        //then
        assertThat(result).hasSize(50)
    }

    @Test
    fun `should be case-insensitive when checking for italian for large groups meals`() {
        //given
        every { recipesRepository.getRecipes() } returns listOf(DummyRecipes.italianGroupRecipes[4])
        //when
        val result = suggestItalianRecipesForLargeGroupsUseCase.getItalianRecipesForLargeGroups()
        //then
        assertThat(result).hasSize(1)
    }
}