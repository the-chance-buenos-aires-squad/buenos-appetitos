package logic.useCases

import com.google.common.truth.Truth.assertThat
import dummyData.DummyRecipes
import io.mockk.every
import io.mockk.mockk
import logic.customExceptions.NoRecipeFoundException
import org.example.logic.RecipesRepository
import org.example.logic.customExceptions.NoKetoRecipeFoundException
import org.example.logic.useCases.GetKetoRecipeUseCase
import org.junit.jupiter.api.*

class GetKetoRecipeUseCaseTest{
    private lateinit var repository: RecipesRepository
    private lateinit var getKetoRecipeUseCase: GetKetoRecipeUseCase
    @BeforeEach
    fun setUp(){
        repository = mockk(relaxed = true)
        getKetoRecipeUseCase = GetKetoRecipeUseCase(repository)
    }



    @Test
    fun `should return keto recipe`(){
        //given
        every { repository.getRecipes() } returns DummyRecipes.allRecipes

        //when
        val ketoResultRecipe = getKetoRecipeUseCase.suggestRandomKetoRecipe()

        // then
        assertThat(ketoResultRecipe).isIn(DummyRecipes.passingKetoRecipes)
    }


    @Test
    fun ` repeated calls to suggestRandomKetoRecipe returns different keto recipes`(){
        //given
        every { repository.getRecipes() } returns DummyRecipes.passingKetoRecipes

        //when
        val firstKetoRecipe = getKetoRecipeUseCase.suggestRandomKetoRecipe()
        val secondKetoRecipe = getKetoRecipeUseCase.suggestRandomKetoRecipe()

        // then
        assertAll(
            { Assertions.assertNotEquals(firstKetoRecipe, secondKetoRecipe) },
            { Assertions.assertTrue(firstKetoRecipe in DummyRecipes.passingKetoRecipes) },
            { Assertions.assertTrue(secondKetoRecipe in DummyRecipes.passingKetoRecipes) }
        )
    }


    @Test
    fun `return keto recipe with carbs less than 30 `(){
        //given
        every { repository.getRecipes() } returns DummyRecipes.passingKetoRecipes

        //when
        val ketoRecipe = getKetoRecipeUseCase.suggestRandomKetoRecipe()

        //then
        assertThat(ketoRecipe.nutrition.carbohydrates).isLessThan(30.0)
    }

    @Test
    fun `return keto recipe with fat greater than 40 `(){
        //given
        every { repository.getRecipes() } returns DummyRecipes.passingKetoRecipes

        //when
        val ketoRecipe = getKetoRecipeUseCase.suggestRandomKetoRecipe()

        //then
        assertThat(ketoRecipe.nutrition.fat).isGreaterThan(40.0)
    }



    @Test
    fun `return keto recipe with protein greater than 30 `(){
        //given
        every { repository.getRecipes() } returns DummyRecipes.passingKetoRecipes

        //when
        val ketoRecipe = getKetoRecipeUseCase.suggestRandomKetoRecipe()

        //then
        assertThat(ketoRecipe.nutrition.protein).isGreaterThan(30.0)
    }

    @Test
    fun `return keto recipe with saturatedFat less than 15 `(){
        //given
        every { repository.getRecipes() } returns DummyRecipes.passingKetoRecipes

        //when
        val ketoRecipe = getKetoRecipeUseCase.suggestRandomKetoRecipe()

        //then
        assertThat(ketoRecipe.nutrition.saturatedFat).isLessThan(15.0)
    }


    @Test
    fun `should through NoRecipeFoundException when repository getRecipes() returns empty list`(){
        //give
        every { repository.getRecipes() } returns emptyList()

        //when && then
        assertThrows<NoRecipeFoundException> { getKetoRecipeUseCase.suggestRandomKetoRecipe() }

    }

    @Test
    fun `should through NoKetoRecipeFoundException when no keto recipes are at the list from repository`(){
        //given
        every { repository.getRecipes() } returns DummyRecipes.failingKetoRecipes
        //when && then
        assertThrows<NoKetoRecipeFoundException> { getKetoRecipeUseCase.suggestRandomKetoRecipe() }
    }


}