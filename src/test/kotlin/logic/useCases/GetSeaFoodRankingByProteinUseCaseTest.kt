package logic.useCases

import com.google.common.truth.Truth.assertThat
import dataHelper.createRecipe
import io.mockk.every
import io.mockk.mockk
import model.Nutrition
import org.example.logic.RecipesRepository
import org.example.logic.useCases.GetSeaFoodRankingByProteinUseCase
import org.example.model.Recipe
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class GetSeaFoodRankingByProteinUseCaseTest {
    private lateinit var recipesRepository: RecipesRepository
    private lateinit var getSeaFoodRankingByProteinUseCase: GetSeaFoodRankingByProteinUseCase

    @BeforeEach
    fun setup() {
        recipesRepository = mockk()
        getSeaFoodRankingByProteinUseCase = GetSeaFoodRankingByProteinUseCase(recipesRepository)
    }
    
    @Test
    fun `should return empty list if no seafood meals`() {
        //given
        every { recipesRepository.getRecipes() } returns emptyList<Recipe>()
        //when
        val result = getSeaFoodRankingByProteinUseCase.getSeaFoodRanking()
        //then
        assertThat(result).isEmpty()
    }

    @Test
    fun `should return only three seafood meals if list only have three seafood meals`() {
        //given
        every { recipesRepository.getRecipes() } returns listOf(
            createRecipe(
                "Tuna Delight", listOf("tuna", "healthy", "seafood"), listOf("tuna", "salt"),
                Nutrition(0.0, 0.0, 0.0, 0.0, 20.0, 0.0, 0.0)
            ),

            createRecipe(
                "Shrimp Pasta", listOf("pasts", "healthy", "seafood"), listOf("shrimp", "salt"),
                Nutrition(0.0, 0.0, 0.0, 0.0, 30.0, 0.0, 0.0)
            ),

            createRecipe(
                "fish Plate", listOf("pasts", "healthy", "seafood"), listOf("fish", "salt"),
                Nutrition(0.0, 0.0, 0.0, 0.0, 10.0, 0.0, 0.0)
            ),

            createRecipe(
                "rice Plate", listOf("healthy", "rice"), listOf("rice", "salt"),
                Nutrition(0.0, 0.0, 0.0, 0.0, 2.0, 0.0, 0.0)
            )
        )
        //when
        val result = getSeaFoodRankingByProteinUseCase.getSeaFoodRanking()
        //then
        assertThat(result).hasSize(3)
    }

    @Test
    fun `should return only ten seafood meals if list have more than 10 seafood meals`() {
        //given
        every { recipesRepository.getRecipes() } returns listOf(
            createRecipe(
                "Tuna Delight", listOf("tuna", "healthy", "seafood"), listOf("tuna", "salt"),
                Nutrition(0.0, 0.0, 0.0, 0.0, 20.0, 0.0, 0.0)
            ),

            createRecipe(
                "Shrimp Pasta", listOf("pasts", "healthy", "seafood"), listOf("shrimp", "salt"),
                Nutrition(0.0, 0.0, 0.0, 0.0, 30.0, 0.0, 0.0)
            ),

            createRecipe(
                "fish Plate", listOf("pasts", "healthy", "seafood"), listOf("fish", "salt"),
                Nutrition(0.0, 0.0, 0.0, 0.0, 10.0, 0.0, 0.0)
            ),

            createRecipe(
                "rice Plate", listOf("healthy", "rice"), listOf("rice", "salt"),
                Nutrition(0.0, 0.0, 0.0, 0.0, 2.0, 0.0, 0.0)
            ),

            createRecipe(
                "Tuna Delight", listOf("tuna", "healthy", "seafood"), listOf("tuna", "salt"),
                Nutrition(0.0, 0.0, 0.0, 0.0, 20.0, 0.0, 0.0)
            ),

            createRecipe(
                "Shrimp Pasta", listOf("pasts", "healthy", "seafood"), listOf("shrimp", "salt"),
                Nutrition(0.0, 0.0, 0.0, 0.0, 30.0, 0.0, 0.0)
            ),

            createRecipe(
                "fish Plate", listOf("pasts", "healthy", "seafood"), listOf("fish", "salt"),
                Nutrition(0.0, 0.0, 0.0, 0.0, 10.0, 0.0, 0.0)
            ),
            createRecipe(
                "Tuna Delight", listOf("tuna", "healthy", "seafood"), listOf("tuna", "salt"),
                Nutrition(0.0, 0.0, 0.0, 0.0, 20.0, 0.0, 0.0)
            ),

            createRecipe(
                "Shrimp Pasta", listOf("pasts", "healthy", "seafood"), listOf("shrimp", "salt"),
                Nutrition(0.0, 0.0, 0.0, 0.0, 30.0, 0.0, 0.0)
            ),

            createRecipe(
                "fish Plate", listOf("pasts", "healthy", "seafood"), listOf("fish", "salt"),
                Nutrition(0.0, 0.0, 0.0, 0.0, 10.0, 0.0, 0.0)
            ),
            createRecipe(
                "Tuna Delight", listOf("tuna", "healthy", "seafood"), listOf("tuna", "salt"),
                Nutrition(0.0, 0.0, 0.0, 0.0, 20.0, 0.0, 0.0)
            ),

            createRecipe(
                "Shrimp Pasta", listOf("pasts", "healthy", "seafood"), listOf("shrimp", "salt"),
                Nutrition(0.0, 0.0, 0.0, 0.0, 30.0, 0.0, 0.0)
            ),

            createRecipe(
                "fish Plate", listOf("pasts", "healthy", "seafood"), listOf("fish", "salt"),
                Nutrition(0.0, 0.0, 0.0, 0.0, 10.0, 0.0, 0.0)
            )
        )
        //when
        val result = getSeaFoodRankingByProteinUseCase.getSeaFoodRanking()
        //then
        assertThat(result).hasSize(10)
    }

    @Test
    fun `should return the biggest seafood meal at the first`() {
        //given
        every { recipesRepository.getRecipes() } returns listOf(
            createRecipe(
                "Tuna Delight", listOf("tuna", "healthy", "seafood"), listOf("tuna", "salt"),
                Nutrition(0.0, 0.0, 0.0, 0.0, 20.0, 0.0, 0.0)
            ),

            createRecipe(
                "Shrimp Pasta", listOf("pasts", "healthy", "seafood"), listOf("shrimp", "salt"),
                Nutrition(0.0, 0.0, 0.0, 0.0, 30.0, 0.0, 0.0)
            ),

            createRecipe(
                "fish Plate", listOf("pasts", "healthy", "seafood"), listOf("fish", "salt"),
                Nutrition(0.0, 0.0, 0.0, 0.0, 10.0, 0.0, 0.0)
            ),

            createRecipe(
                "rice Plate", listOf("healthy", "rice"), listOf("rice", "salt"),
                Nutrition(0.0, 0.0, 0.0, 0.0, 2.0, 0.0, 0.0)
            )
        )
        //when
        val result = getSeaFoodRankingByProteinUseCase.getSeaFoodRanking()
        //then
        assertThat(result[0].name).isEqualTo("Shrimp Pasta")
    }
}