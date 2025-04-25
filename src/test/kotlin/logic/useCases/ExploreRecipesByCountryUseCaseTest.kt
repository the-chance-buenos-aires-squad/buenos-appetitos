package logic.useCases

import com.google.common.truth.Truth.assertThat
import dummyData.DummyRecipes
import io.mockk.every
import io.mockk.mockk
import org.example.logic.RecipesRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ExploreRecipesByCountryUseCaseTest{
    private lateinit var recipesRepository:RecipesRepository
    private lateinit var exploreRecipesByCountryUseCase:ExploreRecipesByCountryUseCase
    @BeforeEach
    fun setup(){
        recipesRepository= mockk(relaxed = true)
        exploreRecipesByCountryUseCase= ExploreRecipesByCountryUseCase(recipesRepository)
    }
    @Test
    fun `should return list if country recipes is matched`() {
        //given
        every { recipesRepository.getRecipes() } returns DummyRecipes.countryRecipes
        //when
        val result = exploreRecipesByCountryUseCase.searchCountryName("Japanese")
        //then
        assertThat(result.all { it.tags.any { tag -> tag.equals("Japanese") } }).isTrue()
    }
    @Test
    fun `should return empty list if country recipes is not matched`() {
        // given
        every { recipesRepository.getRecipes() } returns DummyRecipes.countryRecipes
        // when
        val result = exploreRecipesByCountryUseCase.searchCountryName("Norway")
        // then
        assertThat(result).isEmpty()
    }


    @Test
    fun `should return exception if user input empty`(){
        //give
        every { recipesRepository.getRecipes() } returns DummyRecipes.countryRecipes
        //when & then
        val exception = assertThrows<Exception> {
            exploreRecipesByCountryUseCase.searchCountryName(" ")
        }

        assertThat(exception.message).isEqualTo("Country name cannot be empty")


    }
    @Test
    fun `should return at most 20 recipes if more exist`() {
        // given: create a large list of 50 recipes all from France
        val largeList = List(50) {
            DummyRecipes.countryRecipes.first().copy(
                tags = listOf("French")
            )
        }
        every { recipesRepository.getRecipes() } returns largeList
        // when
        val result = exploreRecipesByCountryUseCase.searchCountryName("French")

        // then
        assertThat(result.size).isAtMost(ExploreRecipesByCountryUseCase.NUMBER_OF_SEARCH_RECIPES)
    }

}