package logic.useCases

import dummyData.DummyRecipes
import io.mockk.every
import io.mockk.mockk
import org.example.logic.RecipesRepository
import org.example.logic.customExceptions.NoRecipesWithIngredientsException
import org.example.logic.useCases.IngredientGameUseCase
import org.example.model.IngredientGameRound
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.assertThrows
import kotlin.test.Test

class IngredientGameUseCaseTest {

    private lateinit var recipesRepository: RecipesRepository
    private lateinit var ingredientGameUseCase: IngredientGameUseCase

    @BeforeEach
    fun setUp() {
        recipesRepository = mockk(relaxed = true)
        ingredientGameUseCase = IngredientGameUseCase(recipesRepository)
    }

    @Test
    fun `should reset score when reset game`() {
        //given
        every { recipesRepository.getRecipes() } returns emptyList()

        //when
        ingredientGameUseCase.startNewGame()

        //then
        assertEquals(0, ingredientGameUseCase.getScore())
    }

    @Test
    fun `should hasGameEnded be false when reset game`() {
        //given
        every { recipesRepository.getRecipes() } returns emptyList()

        //when
        ingredientGameUseCase.startNewGame()

        //then
        assertFalse(ingredientGameUseCase.isGameEnd())
    }

    @Test
    fun `should currentRound be 0 when reset game`() {
        //given
        every { recipesRepository.getRecipes() } returns emptyList()

        //when
        ingredientGameUseCase.startNewGame()

        //then
        assertEquals(0, ingredientGameUseCase.getCurrentRound())
    }

    @Test
    fun `should score be 0 when player answers incorrectly in first round`() {
        //given
        every { recipesRepository.getRecipes() } returns DummyRecipes.ingredientGameRecipes

        //when
        ingredientGameUseCase.startNewGame()
        playOneWrongAnswerRound()

        //then
        assertEquals(0, ingredientGameUseCase.getScore())
    }

    @Test
    fun `should game end when player loses first round`() {
        //given
        every { recipesRepository.getRecipes() } returns DummyRecipes.ingredientGameRecipes

        //when
        ingredientGameUseCase.startNewGame()
        playOneWrongAnswerRound()

        //then
        assertTrue(ingredientGameUseCase.isGameEnd())
    }

    @Test
    fun `should score be 5000 when player answers 5 question then fail`() {
        //given
        every { recipesRepository.getRecipes() } returns DummyRecipes.ingredientGameRecipes

        //when
        PlayFiveCorrectOneWrongRounds()

        //then
        assertEquals(5000, ingredientGameUseCase.getScore())
    }

    @Test
    fun `should current round be 6 when player fails 6 round`() {
        //given
        every { recipesRepository.getRecipes() } returns DummyRecipes.ingredientGameRecipes

        //when
        PlayFiveCorrectOneWrongRounds()

        //then
        assertEquals(6, ingredientGameUseCase.getCurrentRound())
    }

    @Test
    fun `should game end when pass all rounds`() {
        //given
        every { recipesRepository.getRecipes() } returns DummyRecipes.ingredientGameRecipes

        //when
        playFifteenWinningRounds()

        //then
        assertTrue(ingredientGameUseCase.isGameEnd())
    }

    @Test
    fun `should isGameOver equal true when user pass fifteen round`() {
        //given
        every { recipesRepository.getRecipes() } returns DummyRecipes.ingredientGameRecipes

        //when
        playFifteenWinningRounds()

        //then
        assertTrue(ingredientGameUseCase.isGameEnd())
    }

    @Test
    fun `score should be 15000 when user pass fifteen round`() {
        //given
        every { recipesRepository.getRecipes() } returns DummyRecipes.ingredientGameRecipes

        //when
        playFifteenWinningRounds()

        //then
        assertEquals(15000, ingredientGameUseCase.getScore())
    }

    @Test
    fun `should through NoRecipeFoundException when filter with Ingredient`() {
        //give
        every { recipesRepository.getRecipes() } returns DummyRecipes.ingredientGameWithNoIngredientsRecipes

        //when && then
        assertThrows<NoRecipesWithIngredientsException> { ingredientGameUseCase.createNewRound() }
    }

    private fun playFifteenWinningRounds() {
        ingredientGameUseCase.startNewGame()
        playCorrectRounds(15)
    }

    private fun PlayFiveCorrectOneWrongRounds() {
        ingredientGameUseCase.startNewGame()
        playCorrectRounds(5)
        playOneWrongAnswerRound()
    }

    private fun playCorrectRounds(times: Int) {
        repeat(times) {
            playOneCorrectAnswerRound()
        }
    }

    private fun playOneWrongAnswerRound(): IngredientGameRound {
        val round = ingredientGameUseCase.createNewRound()
        ingredientGameUseCase.checkAnswer(round.questionChoices.filter { it != round.correct }.get(0))
        return round
    }

    private fun playOneCorrectAnswerRound(): IngredientGameRound {
        val round = ingredientGameUseCase.createNewRound()
        ingredientGameUseCase.checkAnswer(round.correct)
        return round
    }

}