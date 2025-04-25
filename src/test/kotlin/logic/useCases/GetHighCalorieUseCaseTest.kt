package logic.useCases

import com.google.common.truth.Truth.assertThat
import dummyData.DummyNutrition
import dummyData.DummyRecipes
import dummyData.createDummyRecipe
import io.mockk.every
import io.mockk.mockk
import org.example.logic.RecipesRepository
import org.example.logic.useCases.GetHighCalorieUseCase
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.assertThrows
import kotlin.test.Test

class GetHighCalorieUseCaseTest {
    private var repository: RecipesRepository = mockk(relaxed = true)
    private lateinit var getHighCalorieUseCase: GetHighCalorieUseCase

    @BeforeEach
    fun setUp() {
        getHighCalorieUseCase = GetHighCalorieUseCase(repository)
    }

    @Test
    fun `should throw exception when no recipes are found`() {
        // given
        every { repository.getRecipes() } returns emptyList()

        // when and then
        assertThrows<IllegalStateException> {
            getHighCalorieUseCase.suggestRandomHighCalorieRecipe()
        }
    }

    @Test
    fun `should throw exception when all recipes have calories less than 700`() {
        // given
        every { repository.getRecipes() } returns DummyRecipes.lowCalorieRecipes

        // when and then
        assertThrows<IllegalStateException> {
            getHighCalorieUseCase.suggestRandomHighCalorieRecipe()
        }
    }

    @Test
    fun `should return a random high calorie recipe from the list when available`() {

        // given
        every { repository.getRecipes() } returns DummyRecipes.highCalorieRecipes

        // when
        val result = getHighCalorieUseCase.suggestRandomHighCalorieRecipe()

        // then
        assertTrue(result in DummyRecipes.highCalorieRecipes)
    }

    @Test
    fun `should filter out low-calorie recipes`() {
        // given
        every { repository.getRecipes() } returns
                DummyRecipes.lowCalorieRecipes + DummyRecipes.highCalorieRecipes

        // when
        val result = getHighCalorieUseCase.suggestRandomHighCalorieRecipe()

        // then
        assertTrue(result.nutrition.calories > 700)
    }

    @Test
    fun `should remove suggested recipe from available recipes when suggesting it`() {
        // given
       val firstRecipeWithHighCalories =  createDummyRecipe( name = "High Calorie 1", nutrition = DummyNutrition.highCalorie)
       val secondRecipeWithHighCalories = createDummyRecipe( name = "High Calorie 2", nutrition = DummyNutrition.highCalorie)

        val recipesWithHighCalories = listOf(firstRecipeWithHighCalories, secondRecipeWithHighCalories)
        every { repository.getRecipes() } returns recipesWithHighCalories

        // when
        val firstResult = getHighCalorieUseCase.suggestRandomHighCalorieRecipe()
        val secondResult = getHighCalorieUseCase.suggestRandomHighCalorieRecipe()

        // then
        assertNotEquals(firstResult, secondResult)
    }
}