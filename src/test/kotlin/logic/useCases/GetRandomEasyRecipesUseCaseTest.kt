package logic.useCases

import com.google.common.truth.Truth.assertThat
import dummyData.createDummyRecipe
import io.mockk.every
import io.mockk.mockk
import org.example.logic.RecipesRepository
import org.example.logic.useCases.GetRandomEasyRecipesUseCase
import org.junit.jupiter.api.Test

class GetRandomEasyRecipesUseCaseTest {

    @Test
    fun `should return correct recipes when all recipes having less than or equal max-minutes 30, max-ingredients 5 and max-steps 6`() {
        // Given
        val testRecipes = listOf(
            createDummyRecipe(
                minutes = 25,
                ingredients = List(2) { "ingredient$it" },
                steps = List(3) { "step$it" }
            ),
            createDummyRecipe(
                minutes = 30,
                ingredients = List(2) { "ingredient$it" },
                steps = List(4) { "step$it" }
            ),
            createDummyRecipe(
                minutes = 25,
                ingredients = List(5) { "ingredient$it" },
                steps = List(2) { "step$it" }
            ),
            createDummyRecipe(
                minutes = 20,
                ingredients = List(3) { "ingredient$it" },
                steps = List(6) { "step$it" }
            ),
            createDummyRecipe(
                minutes = 30,
                ingredients = List(5) { "ingredient$it" },
                steps = List(6) { "step$it" }
            )
        )
        val recipesRepository = mockk<RecipesRepository>()
        every { recipesRepository.getRecipes() } returns testRecipes
        val getRandomEasyRecipesUseCase = GetRandomEasyRecipesUseCase(recipesRepository)
        // When
        val resultRecipes = getRandomEasyRecipesUseCase.suggestEasyRecipes()

        // Then
        assertThat(resultRecipes.size).isEqualTo(5)
        resultRecipes.forEach { recipe ->
            assertThat(recipe.minutes).isAtMost(GetRandomEasyRecipesUseCase.MINUTES_MAX)
            assertThat(recipe.numberOfIngredients).isAtMost(GetRandomEasyRecipesUseCase.INGREDIENTS_MAX_NUMBER)
            assertThat(recipe.numberOfSteps).isAtMost(GetRandomEasyRecipesUseCase.STEPS)
        }
    }

    @Test
    fun `should return correct number of recipes when there is more than 10 recipes follows the condition`() {
        // Given
        val testRecipes = List(15) {
            createDummyRecipe(
                minutes = 25,
                ingredients = List(2) { "ingredient$it" },
                steps = List(3) { "step$it" }
            )
        }
        val recipesRepository = mockk<RecipesRepository>()
        every { recipesRepository.getRecipes() } returns testRecipes
        val getRandomEasyRecipesUseCase = GetRandomEasyRecipesUseCase(recipesRepository)

        // When
        val resultRecipes = getRandomEasyRecipesUseCase.suggestEasyRecipes()

        // Then
        assertThat(resultRecipes.size).isEqualTo(GetRandomEasyRecipesUseCase.TEN_RANDOM_EASY_RECIPES)
        resultRecipes.forEach { recipe ->
            assertThat(recipe.minutes).isAtMost(GetRandomEasyRecipesUseCase.MINUTES_MAX)
            assertThat(recipe.numberOfIngredients).isAtMost(GetRandomEasyRecipesUseCase.INGREDIENTS_MAX_NUMBER)
            assertThat(recipe.numberOfSteps).isAtMost(GetRandomEasyRecipesUseCase.STEPS)
        }
    }

    @Test
    fun `should return empty list of recipes when there is no recipe meet the conditions`() {
        // Given
        val testRecipes = listOf(
            createDummyRecipe(
                minutes = 32,
                ingredients = List(2) { "ingredient$it" },
                steps = List(3) { "step$it" }
            ),
            createDummyRecipe(
                minutes = 15,
                ingredients = List(8) { "ingredient$it" },
                steps = List(4) { "step$it" }
            ),
            createDummyRecipe(
                minutes = 25,
                ingredients = List(4) { "ingredient$it" },
                steps = List(9) { "step$it" }
            ),
            createDummyRecipe(
                minutes = 35,
                ingredients = List(8) { "ingredient$it" },
                steps = List(8) { "step$it" }
            )
        )
        val recipesRepository = mockk<RecipesRepository>()
        every { recipesRepository.getRecipes() } returns testRecipes
        val getRandomEasyRecipesUseCase = GetRandomEasyRecipesUseCase(recipesRepository)

        // When
        val resultRecipes = getRandomEasyRecipesUseCase.suggestEasyRecipes()

        // Then
        assertThat(resultRecipes).isEmpty()
    }
} 
