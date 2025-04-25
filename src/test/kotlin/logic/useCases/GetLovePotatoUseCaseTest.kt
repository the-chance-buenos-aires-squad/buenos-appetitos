package logic.useCases

import io.mockk.every
import io.mockk.mockk
import org.example.logic.RecipesRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import com.google.common.truth.Truth.assertThat
import dummyData.DummyRecipes


class GetLovePotatoUseCaseTest{

      private var repository: RecipesRepository = mockk()
      private lateinit var getLovePotatoUseCase: GetLovePotatoUseCase

      @BeforeEach
      fun setUp() {
       getLovePotatoUseCase = GetLovePotatoUseCase(repository)
      }

    @Test
    fun `should return empty list when the recipes list is empty`(){
        // given
        every { repository.getRecipes() } returns emptyList()

        // when
        val result = getLovePotatoUseCase.getRandomPotatoRecipes()

        // then
        assertThat(result).isEmpty()
    }

    @Test
    fun `should return all potato recipes when count is less than or equal to max`(){
        // given
        val potatoRecipes = DummyRecipes.potatoRecipes.take(7)
        every { repository.getRecipes() } returns potatoRecipes

        // when
        val result = getLovePotatoUseCase.getRandomPotatoRecipes()

        // then
        assertThat(result).hasSize(7)
    }

    @Test
    fun `should return max 10 potato recipes when more than max exists`() {
        // given
        val potatoRecipes = DummyRecipes.potatoRecipes.take(20)
        every { repository.getRecipes() } returns potatoRecipes

        // when
        val result = getLovePotatoUseCase.getRandomPotatoRecipes()

        // then
        assertThat(result).hasSize(10)
    }

    @Test
    fun `should return all potato recipes regardless of case sensitivity`() {
        // given
        val caseSensitivePotatoRecipes = DummyRecipes.potatoRecipes.take(4)
        every { repository.getRecipes() } returns caseSensitivePotatoRecipes

        // when
        val result = getLovePotatoUseCase.getRandomPotatoRecipes()

        // then
        assertThat(result).hasSize(4)
    }

    @Test
    fun `should return empty list when no potato recipes found`() {
        // given
        val nonPotatoRecipes = DummyRecipes.nonPotatoRecipes
        every { repository.getRecipes() } returns nonPotatoRecipes

        // when
        val result = getLovePotatoUseCase.getRandomPotatoRecipes()

        // then
        assertThat(result).isEmpty()
    }

}
