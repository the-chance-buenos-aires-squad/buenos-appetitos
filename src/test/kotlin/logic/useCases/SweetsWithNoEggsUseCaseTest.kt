package logic.useCases

import com.google.common.truth.Truth.assertThat
import dummyData.DummyRecipes
import io.mockk.every
import io.mockk.mockk
import logic.customExceptions.NoRecipeFoundException
import org.example.logic.RecipesRepository
import org.example.logic.customExceptions.NoSweetFreeEggRecipeFoundException
import org.example.logic.useCases.SweetsWithNoEggsUseCase
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class SweetsWithNoEggsUseCaseTest {
    private var repository: RecipesRepository = mockk()
    private lateinit var sweetsWithNoEggsUseCase: SweetsWithNoEggsUseCase

    @BeforeEach
    fun setUp() {
        sweetsWithNoEggsUseCase = SweetsWithNoEggsUseCase(repository)
    }


    @Test
    fun `should return only sweets with no egg recipes when filtering from all recipes`() {
        //given
        every { repository.getRecipes() } returns DummyRecipes.allRecipes

        //When
        val sweetResultRecipe = sweetsWithNoEggsUseCase.suggestSweetNoEggRecipe()

        //then
        assertThat(sweetResultRecipe).isIn(DummyRecipes.eggFreeSweets)

    }


    @Test
    fun `repeated calls to suggestSweetNoEggRecipe returns different sweets with no egg recipes`() {
        //given
        every { repository.getRecipes() } returns DummyRecipes.eggFreeSweets

        //when
        val firstSweetNoEggRecipe = sweetsWithNoEggsUseCase.suggestSweetNoEggRecipe()
        val secondSweetNoEggRecipe = sweetsWithNoEggsUseCase.suggestSweetNoEggRecipe()

        // then
        assertAll(
            { assertNotEquals(firstSweetNoEggRecipe, secondSweetNoEggRecipe) },
            { assertTrue(firstSweetNoEggRecipe in DummyRecipes.eggFreeSweets) },
            { assertTrue(secondSweetNoEggRecipe in DummyRecipes.eggFreeSweets) }
        )
    }


    @Test
    fun `return sweet with dessert word in the recipe tags `() {
        //given
        every { repository.getRecipes() } returns DummyRecipes.allRecipes

        //when
        val sweetNoEggResultRecipe = sweetsWithNoEggsUseCase.suggestSweetNoEggRecipe()

        //then
        assertThat(sweetNoEggResultRecipe.tags).contains("dessert")
    }

    @Test
    fun `return sweet does not contain egg in it's ingredients `() {
        //given
        every { repository.getRecipes() } returns DummyRecipes.allRecipes

        //when
        val sweetNoEggResultRecipe = sweetsWithNoEggsUseCase.suggestSweetNoEggRecipe()

        //then
        assertThat(sweetNoEggResultRecipe.ingredients).doesNotContain("egg")
    }


    @Test
    fun `throws NoRecipeFoundException when no recipes are available`() {
        //give
        every { repository.getRecipes() } returns emptyList()

        //when && then
        assertThrows<NoRecipeFoundException> { sweetsWithNoEggsUseCase.suggestSweetNoEggRecipe() }

    }


    @Test
    fun `throws NoSweetFreeEggRecipeFoundException when repository contains no keto recipes`() {
        //given
        every { repository.getRecipes() } returns DummyRecipes.failingSweetsNoEgg
        //when && then
        assertThrows<NoSweetFreeEggRecipeFoundException> { sweetsWithNoEggsUseCase.suggestSweetNoEggRecipe() }
    }

}