package logic.useCases

import com.google.common.truth.Truth.assertThat
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
    fun `should currentRound be zero when reset game`() {
        //given
        every { recipesRepository.getRecipes() } returns emptyList()

        //when
        ingredientGameUseCase.startNewGame()

        //then
        assertThat(ingredientGameUseCase.getCurrentRound()).isEqualTo(0)
    }

    @Test
    fun `should score be zero when player answers incorrectly in first round`() {
        //given
        every { recipesRepository.getRecipes() } returns DummyRecipes.ingredientGameRecipes

        //when
        ingredientGameUseCase.startNewGame()
        playOneWrongAnswerRound()

        //then
        assertThat(ingredientGameUseCase.getScore()).isEqualTo(0)
    }

    @Test
    fun `should end game when player lose first round`() {
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
        assertThat(ingredientGameUseCase.getScore()).isEqualTo(5000)
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
    fun `game terminates after final round completion`() {
        //given
        every { recipesRepository.getRecipes() } returns DummyRecipes.ingredientGameRecipes

        //when
        playFifteenWinningRounds()

        //then
        assertTrue(ingredientGameUseCase.isGameEnd())
    }

    @Test
    fun `game is over after user completes fifteen rounds`() {
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
    fun `should throwNoRecipeFoundException when filter by ingredient returns nothing`() {
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