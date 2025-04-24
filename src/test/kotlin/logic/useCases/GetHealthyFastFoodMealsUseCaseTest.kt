package logic.useCases

import com.google.common.truth.Truth.assertThat
import dummyData.DummyRecipes
import io.mockk.every
import io.mockk.mockk
import logic.customExceptions.NoRecipeFoundException
import org.example.logic.RecipesRepository
import org.example.logic.useCases.GetHealthyFastFoodMealsUseCase
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class GetHealthyFastFoodMealsUseCaseTest {
    private lateinit var recipesRepository: RecipesRepository
    private lateinit var getHealthyFastFoodMealsUseCase: GetHealthyFastFoodMealsUseCase

    @BeforeEach
    fun setup() {
        recipesRepository = mockk(relaxed = true)
        getHealthyFastFoodMealsUseCase = GetHealthyFastFoodMealsUseCase(recipesRepository)
    }

    @Test
    fun `should return empty list if recipes is empty`() {
        //given
        every { recipesRepository.getRecipes() } returns emptyList()
        // when and then
        assertThrows<NoRecipeFoundException> {
            getHealthyFastFoodMealsUseCase.getHealthyFastFood()
        }

    }

    @Test
    fun `should return empty list if all recipes take time greater than 15 minutes`() {
        //given
        every { recipesRepository.getRecipes() } returns DummyRecipes.healthyFastFoodRecipesTakeTimeGreaterThanFifty
        //when
        val result = getHealthyFastFoodMealsUseCase.getHealthyFastFood()
        //then
        assertThat(result).isEmpty()
    }

    @Test
    fun `should return empty list if all recipes hav total fat is null`() {
        //given
        every { recipesRepository.getRecipes() } returns DummyRecipes.healthyFastFoodRecipesHaveFatEqualNull
        //when
        val result = getHealthyFastFoodMealsUseCase.getHealthyFastFood()
        //then
        assertThat(result).isEmpty()
    }

    @Test
    fun `should return empty list if all recipes hav saturated fat is null`() {
        //given
        every { recipesRepository.getRecipes() } returns DummyRecipes.healthyFastFoodRecipesHaveSaturatedFatEqualNull
        //when
        val result = getHealthyFastFoodMealsUseCase.getHealthyFastFood()
        //then
        assertThat(result).isEmpty()
    }

    @Test
    fun `should return empty list if all recipes hav carbs is null`() {
        //given
        every { recipesRepository.getRecipes() } returns DummyRecipes.healthyFastFoodRecipesHaveCarbsEqualNull
        //when
        val result = getHealthyFastFoodMealsUseCase.getHealthyFastFood()
        //then
        assertThat(result).isEmpty()
    }

    @Test
    fun `should return five meals if list have only five meals`() {
        //given
        every { recipesRepository.getRecipes() } returns DummyRecipes.healthyFastFoodRecipes
        //when
        val result = getHealthyFastFoodMealsUseCase.getHealthyFastFood()
        //then
        assertThat(result).hasSize(5)
    }

    @Test
    fun `should return three meals when i pass three for function`() {
        //then
        every { recipesRepository.getRecipes() } returns DummyRecipes.healthyFastFoodRecipes
        //when
        val result = getHealthyFastFoodMealsUseCase.getHealthyFastFood(3)
        //then
        assertThat(result).hasSize(3)
    }

    @Test
    fun `should return low fat low saturated fat low carbs at the first`() {
        //then
        every { recipesRepository.getRecipes() } returns DummyRecipes.healthyFastFoodRecipes
        //when
        val result = getHealthyFastFoodMealsUseCase.getHealthyFastFood()
        //then
        assertThat(result[0].name).isEqualTo("Spicy Tuna Lettuce Wraps")
    }

    @Test
    fun `should return normalization equal zero if min equal max then sorted with index`() {
        every { recipesRepository.getRecipes() } returns DummyRecipes.healthyFastFoodRecipesNormalizationEqualZero
        val result = getHealthyFastFoodMealsUseCase.getHealthyFastFood()
        assertThat(result).hasSize(2)
    }
}