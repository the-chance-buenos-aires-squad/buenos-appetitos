package logic.useCases

import com.google.common.truth.Truth.assertThat
import dummyData.DummyRecipes
import io.mockk.every
import io.mockk.mockk
import org.example.logic.RecipesRepository
import org.example.logic.customExceptions.NoRecipesWithIngredientsException
import org.example.logic.useCases.IngredientGameUseCase
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.assertThrows
import kotlin.test.Test

class IngredientGameUseCaseTest {

    private var recipesRepository: RecipesRepository = mockk(relaxed = true)
    private lateinit var ingredientGameUseCase: IngredientGameUseCase

    @BeforeEach
    fun setUp() {
        ingredientGameUseCase = IngredientGameUseCase(recipesRepository)
    }

    @Test
    fun `should reset score when reset game`() {
        //when
        startNewGame()

        //then
        assertThat(ingredientGameUseCase.getScore()).isEqualTo(0)
    }

    @Test
    fun `hasGameEnded should return false when reset game`() {
        //when
        startNewGame()

        //then
        assertThat(ingredientGameUseCase.isGameEnd()).isFalse()
    }

    @Test
    fun `currentRound should be zero when reset game`() {
        //when
        startNewGame()

        //then
        assertThat(ingredientGameUseCase.getCurrentRound()).isEqualTo(0)
    }

    @Test
    fun `score should return zero when player answers incorrectly in first round`() {
        //given
        every { recipesRepository.getRecipes() } returns DummyRecipes.ingredientGameRecipes

        //when
        loseInFirstRound()

        //then
        assertThat(ingredientGameUseCase.getScore()).isEqualTo(0)
    }

    @Test
    fun `game should end when player lose first round`() {
        //given
        every { recipesRepository.getRecipes() } returns DummyRecipes.ingredientGameRecipes

        //when
        loseInFirstRound()

        //then
        assertThat(ingredientGameUseCase.isGameEnd()).isTrue()
    }

    @Test
    fun `score should return 5000 when player answers 5 question then fail`() {
        //given
        every { recipesRepository.getRecipes() } returns DummyRecipes.ingredientGameRecipes

        //when
        playFiveCorrectOneWrongRounds()

        //then
        assertThat(ingredientGameUseCase.getScore()).isEqualTo(5000)
    }

    @Test
    fun `current round should be 6 when player fails 6 round`() {
        //given
        every { recipesRepository.getRecipes() } returns DummyRecipes.ingredientGameRecipes

        //when
        playFiveCorrectOneWrongRounds()

        //then
        assertThat(ingredientGameUseCase.getCurrentRound()).isEqualTo(6)
    }

    @Test
    fun `game terminates after final round completion`() {
        //given
        every { recipesRepository.getRecipes() } returns DummyRecipes.ingredientGameRecipes

        //when
        completeFifteenRoundsSuccessfully()

        //then
        assertThat(ingredientGameUseCase.isGameEnd()).isTrue()
    }

    @Test
    fun `score should be 15000 when user pass fifteen round`() {
        //given
        every { recipesRepository.getRecipes() } returns DummyRecipes.ingredientGameRecipes

        //when
        completeFifteenRoundsSuccessfully()

        //then
        assertThat(ingredientGameUseCase.getScore()).isEqualTo(15000)
    }

    @Test
    fun `should throwNoRecipeFoundException when filter by ingredient returns nothing`() {
        //give
        every { recipesRepository.getRecipes() } returns DummyRecipes.ingredientGameWithNoIngredientsRecipes

        //when && then
        assertThrows<NoRecipesWithIngredientsException> { ingredientGameUseCase.createNewRound() }
    }

    private fun startNewGame() {
        ingredientGameUseCase.startNewGame()
    }

    private fun loseInFirstRound() {
        startNewGame()
        playOneWrongAnswerRound()
    }

    private fun completeFifteenRoundsSuccessfully() {
        startNewGame()
        playCorrectRounds(15)
    }

    private fun playFiveCorrectOneWrongRounds() {
        startNewGame()
        playCorrectRounds(5)
        playOneWrongAnswerRound()
    }

    private fun playCorrectRounds(times: Int) {
        repeat(times) {
            playCorrectRound()
        }
    }

    private fun playCorrectRound() {
        val round = ingredientGameUseCase.createNewRound()
        ingredientGameUseCase.checkAnswer(round.correct)
    }

    private fun playOneWrongAnswerRound() {
        val round = ingredientGameUseCase.createNewRound()
        val wrongAnswer = round.questionChoices.first { it != round.correct }
        ingredientGameUseCase.checkAnswer(wrongAnswer)
    }

}