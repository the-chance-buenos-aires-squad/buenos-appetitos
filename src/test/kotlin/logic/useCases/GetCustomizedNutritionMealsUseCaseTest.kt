package logic.useCases

import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import org.example.logic.RecipesRepository
import org.example.logic.useCases.GetCustomizedNutritionMealsUseCase
import org.junit.jupiter.api.BeforeEach
import kotlin.test.Test
import dummyData.DummyRecipes

class GetCustomizedNutritionMealsUseCaseTest {
    private lateinit var recipesRepository: RecipesRepository
    private lateinit var getCustomizedNutritionMealsUseCase: GetCustomizedNutritionMealsUseCase

    @BeforeEach
    fun setup() {
        recipesRepository = mockk(relaxed = true)
        getCustomizedNutritionMealsUseCase = GetCustomizedNutritionMealsUseCase(recipesRepository)
    }

    @Test
    fun `should return empty list if recipes is empty`() {
        //given
        every { recipesRepository.getRecipes() } returns emptyList()
        //when
        val result = getCustomizedNutritionMealsUseCase.findMealsByNutrition(400, 50)
        //then
        assertThat(result).isEmpty()
    }

    @Test
    fun `should return recipes within tolerance and sort them by the best match`() {
        //given
        val recipes = listOf(
            DummyRecipes.customizedNutritionMeals[0],
            DummyRecipes.customizedNutritionMeals[1],
            DummyRecipes.customizedNutritionMeals[2],
        )
        every { recipesRepository.getRecipes() } returns recipes
        //when
        val result = getCustomizedNutritionMealsUseCase.findMealsByNutrition(250, 15 )
        val actual = result.map { it.name }
        //then
        assertThat(actual).containsExactly(
            "Spinach & Feta Omelet",
            "Peanut Butter Banana Toast",
            "Caprese Salad"
        ).inOrder()
    }

    @Test
    fun `should return empty list when no recipes match nutrition criteria`() {
        //given
        val recipes = listOf(
            DummyRecipes.customizedNutritionMeals[0],
            DummyRecipes.customizedNutritionMeals[1]
        )
        every { recipesRepository.getRecipes() } returns recipes
        //when
        val result = getCustomizedNutritionMealsUseCase.findMealsByNutrition(500, 50, 20)
        //then
        assertThat(result).isEmpty()
    }

    @Test
    fun `should handle recipes with exact nutrition match`() {
        //given
        val recipes = listOf(
            DummyRecipes.customizedNutritionMeals[0],
        )
        every { recipesRepository.getRecipes() } returns recipes
        //when
        val result = getCustomizedNutritionMealsUseCase.findMealsByNutrition(250, 18, 0)
        val actual = result.map { it.name }
        val expected = "Spinach & Feta Omelet"
        //then
        assertThat(actual).containsExactly(expected)
    }

    @Test
    fun `should exclude recipe if only protein matches but calories do not`() {
        //given
        val recipes = listOf(
            DummyRecipes.customizedNutritionMeals[0]
        )
        //when
        every { recipesRepository.getRecipes() } returns recipes
        val result = getCustomizedNutritionMealsUseCase.findMealsByNutrition(300, 18, 10)
        //then
        assertThat(result).isEmpty()
    }

    @Test
    fun `should exclude recipe if only calories matches but protein do not`() {
        //given
        val recipes = listOf(
            DummyRecipes.customizedNutritionMeals[0]
        )
        //when
        every { recipesRepository.getRecipes() } returns recipes
        val result = getCustomizedNutritionMealsUseCase.findMealsByNutrition(250, 30, 10)
        //then
        assertThat(result).isEmpty()
    }

}