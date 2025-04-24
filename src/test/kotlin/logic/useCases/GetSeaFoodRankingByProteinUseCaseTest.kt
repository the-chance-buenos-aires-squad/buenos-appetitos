package logic.useCases

import com.google.common.truth.Truth.assertThat
import dummyData.DummyRecipes
import io.mockk.every
import io.mockk.mockk
import org.example.logic.RecipesRepository
import org.example.logic.useCases.GetSeaFoodRankingByProteinUseCase
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class GetSeaFoodRankingByProteinUseCaseTest {
    private lateinit var recipesRepository: RecipesRepository
    private lateinit var getSeaFoodRankingByProteinUseCase: GetSeaFoodRankingByProteinUseCase

    @BeforeEach
    fun setup() {
        recipesRepository = mockk(relaxed = true)
        getSeaFoodRankingByProteinUseCase = GetSeaFoodRankingByProteinUseCase(recipesRepository)
    }

    @Test
    fun `should return empty list if recipes is empty`() {
        //given
        every { recipesRepository.getRecipes() } returns emptyList()
        //when
        val result = getSeaFoodRankingByProteinUseCase.getSeaFoodRanking()
        //then
        assertThat(result).isEmpty()
    }

    @Test
    fun `should return empty list if no seafood meals`() {
        //given
        every { recipesRepository.getRecipes() } returns DummyRecipes.potatoRecipes
        //when
        val result = getSeaFoodRankingByProteinUseCase.getSeaFoodRanking()
        //then
        assertThat(result).isEmpty()
    }

    @Test
    fun `should return only seven seafood meals if list only have seven seafood meals`() {
        //given
        every { recipesRepository.getRecipes() } returns DummyRecipes.seafoodRecipes
        //when
        val result = getSeaFoodRankingByProteinUseCase.getSeaFoodRanking()
        //then
        assertThat(result).hasSize(8)
    }

    @Test
    fun `should return only ten seafood meals if list have more than 10 seafood meals`() {
        //given
        every { recipesRepository.getRecipes() } returns
                DummyRecipes.seafoodRecipes + DummyRecipes.seafoodRecipes

        //when
        val result = getSeaFoodRankingByProteinUseCase.getSeaFoodRanking()
        //then
        assertThat(result).hasSize(10)
    }

    @Test
    fun `should return the biggest seafood meal contain protein at the first`() {
        //given
        every { recipesRepository.getRecipes() } returns DummyRecipes.seafoodRecipes
        //when
        val result = getSeaFoodRankingByProteinUseCase.getSeaFoodRanking()
        //then
        assertThat(result[0].name).isEqualTo("Grilled Salmon Steak")
    }

    @Test
    fun `should return empty list if protein is null`() {
        //given
        every { recipesRepository.getRecipes() } returns listOf(DummyRecipes.seafoodRecipes[0])
        //when
        val result = getSeaFoodRankingByProteinUseCase.getSeaFoodRanking()
        //then
        assertThat(result).hasSize(0)
    }

    @Test
    fun `should be case-insensitive when checking for seafood`() {
        //given
        every { recipesRepository.getRecipes() } returns DummyRecipes.seafoodRecipes
        //when
        val result = getSeaFoodRankingByProteinUseCase.getSeaFoodRanking()
        //then
        assertThat(result).hasSize(8)
    }

    @Test
    fun `should return meal if tags only contain seafood meal`() {
        //given
        every { recipesRepository.getRecipes() } returns listOf(DummyRecipes.seafoodRecipes[7])
        //when
        val result = getSeaFoodRankingByProteinUseCase.getSeaFoodRanking()
        //then
        assertThat(result).hasSize(1)
    }

    @Test
    fun `should return meal if ingredients only contain seafood meal`() {
        //given
        every { recipesRepository.getRecipes() } returns listOf(DummyRecipes.seafoodRecipes[8])
        //when
        val result = getSeaFoodRankingByProteinUseCase.getSeaFoodRanking()
        //then
        assertThat(result).hasSize(1)
    }
}