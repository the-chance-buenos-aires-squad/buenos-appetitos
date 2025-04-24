package logic.useCases

import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import org.example.logic.RecipesRepository
import org.example.logic.useCases.GymMealsUseCase
import org.junit.jupiter.api.BeforeEach
import kotlin.test.Test
import dummyData.DummyRecipes

class GymMealsUseCaseTest {
    private lateinit var recipesRepository: RecipesRepository
    private lateinit var gymMealsUseCase: GymMealsUseCase

    @BeforeEach
    fun setup() {
        recipesRepository = mockk(relaxed = true)
        gymMealsUseCase = GymMealsUseCase(recipesRepository)
    }

    @Test
    fun `should return empty list if recipes is empty`() {

        every { recipesRepository.getRecipes() } returns emptyList()

        val result = gymMealsUseCase.findMealsByNutrition(400, 50)

        assertThat(result).isEmpty()
    }

    @Test
    fun `should return recipes within tolerance and sort them by the best match`() {
        //given
        val recipes = listOf(
            DummyRecipes.easyRecipes[0],
            DummyRecipes.easyRecipes[1],
            DummyRecipes.easyRecipes[2],
        )
        every { recipesRepository.getRecipes() } returns recipes

        //when
        val result = gymMealsUseCase.findMealsByNutrition(250, 15 )

        //then
        assertThat(result.map { it.name }).containsExactly(
            "Spinach & Feta Omelet",
            "Peanut Butter Banana Toast",
            "Caprese Salad"
        ).inOrder()
    }

    @Test
    fun `should return empty list when no recipes match nutrition criteria`() {

        val recipes = listOf(
            DummyRecipes.easyRecipes[0],
            DummyRecipes.easyRecipes[1]
        )
        every { recipesRepository.getRecipes() } returns recipes

        val result = gymMealsUseCase.findMealsByNutrition(500, 50, 20)

        assertThat(result).isEmpty()
    }

    @Test
    fun `should handle recipes with exact nutrition match`() {
        val recipes = listOf(
            DummyRecipes.easyRecipes[0],
        )
        every { recipesRepository.getRecipes() } returns recipes

        val result = gymMealsUseCase.findMealsByNutrition(250, 18, 0)

        assertThat(result.map { it.name }).containsExactly("Spinach & Feta Omelet")
    }

    @Test
    fun `should exclude recipe if only protein matches but calories do not`() {
        val recipes = listOf(
            DummyRecipes.easyRecipes[0]
        )
        every { recipesRepository.getRecipes() } returns recipes

        val result = gymMealsUseCase.findMealsByNutrition(300, 18, 10)

        assertThat(result).isEmpty()
    }

    @Test
    fun `should exclude recipe if only calories matches but protein do not`() {
        val recipes = listOf(
            DummyRecipes.easyRecipes[0]
        )
        every { recipesRepository.getRecipes() } returns recipes

        val result = gymMealsUseCase.findMealsByNutrition(250, 30, 10)

        assertThat(result).isEmpty()
    }

}