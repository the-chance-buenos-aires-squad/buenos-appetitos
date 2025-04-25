package logic.useCases

import dummyData.DummyRecipes
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.example.logic.useCases.GuessGameUseCase
import org.example.logic.useCases.RandomRecipeUseCase
import org.example.model.GuessAttemptResult
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class GuessGameUseCaseTest {
    private lateinit var randomRecipeUseCase: RandomRecipeUseCase
    private lateinit var guessGameUseCase: GuessGameUseCase

    private val testRecipe = DummyRecipes.countryRecipes.first()

    @BeforeEach
    fun setup() {
        randomRecipeUseCase = mockk()
        every { randomRecipeUseCase.getRandomRecipe() } returns testRecipe
        guessGameUseCase = GuessGameUseCase(randomRecipeUseCase)
    }

    @Test
    fun `should call random recipe use case when game is initialized`() {
        verify(exactly = 1) { randomRecipeUseCase.getRandomRecipe() }
    }

    @Test
    fun `should set correct recipe when game is initialized`() {
        val gameState = guessGameUseCase.getGameState()
        assertEquals(testRecipe, gameState.recipe)
    }

    @Test
    fun `should set attempt count to zero when game is initialized`() {
        val gameState = guessGameUseCase.getGameState()
        assertEquals(0, gameState.attemptCount)
    }

    @Test
    fun `should set game as not finished when game is initialized`() {
        val gameState = guessGameUseCase.getGameState()
        assertFalse(gameState.isFinished)
    }

    @Test
    fun `should return Correct result when guess matches preparation time`() {
        val result = guessGameUseCase.processGuess(30)
        assertTrue(result is GuessAttemptResult.Correct)
    }

    @Test
    fun `should increase attempt count when correct guess is made`() {
        guessGameUseCase.processGuess(30)
        assertEquals(1, guessGameUseCase.getGameState().attemptCount)
    }

    @Test
    fun `should finish the game when correct guess is made`() {
        guessGameUseCase.processGuess(30)
        assertTrue(guessGameUseCase.getGameState().isFinished)
    }

    @Test
    fun `should return TooLow result when guess is below preparation time`() {
        val result = guessGameUseCase.processGuess(20)
        assertTrue(result is GuessAttemptResult.TooLow)
    }

    @Test
    fun `should increase attempt count when low guess is made`() {
        guessGameUseCase.processGuess(20)
        assertEquals(1, guessGameUseCase.getGameState().attemptCount)
    }

    @Test
    fun `should not finish the game when low guess is made`() {
        guessGameUseCase.processGuess(20)
        assertFalse(guessGameUseCase.getGameState().isFinished)
    }

    @Test
    fun `should return TooHigh result when guess is above preparation time`() {
        val result = guessGameUseCase.processGuess(40)
        assertTrue(result is GuessAttemptResult.TooHigh)
    }

    @Test
    fun `should increase attempt count when high guess is made`() {
        guessGameUseCase.processGuess(40)
        assertEquals(1, guessGameUseCase.getGameState().attemptCount)
    }

    @Test
    fun `should not finish the game when high guess is made`() {
        guessGameUseCase.processGuess(40)
        assertFalse(guessGameUseCase.getGameState().isFinished)
    }

    @Test
    fun `should return GameOver result when maximum attempts are reached`() {
        guessGameUseCase.processGuess(20) // First attempt
        guessGameUseCase.processGuess(40) // Second attempt
        val result = guessGameUseCase.processGuess(25) // Third attempt
        assertTrue(result is GuessAttemptResult.GameOver)
    }

    @Test
    fun `should include correct answer when game is over`() {
        guessGameUseCase.processGuess(20)
        guessGameUseCase.processGuess(40)
        val result = guessGameUseCase.processGuess(25) as GuessAttemptResult.GameOver
        assertEquals(30, result.correctTime)
    }

    @Test
    fun `should set attempt count to maximum when game is over`() {
        guessGameUseCase.processGuess(20)
        guessGameUseCase.processGuess(40)
        guessGameUseCase.processGuess(25)
        assertEquals(3, guessGameUseCase.getGameState().attemptCount)
    }

    @Test
    fun `should finish the game when maximum attempts are reached`() {
        guessGameUseCase.processGuess(20)
        guessGameUseCase.processGuess(40)
        guessGameUseCase.processGuess(25)
        assertTrue(guessGameUseCase.getGameState().isFinished)
    }

    @Test
    fun `should return Correct result when correct guess is made on last attempt`() {
        guessGameUseCase.processGuess(20)
        guessGameUseCase.processGuess(40)
        val result = guessGameUseCase.processGuess(30)
        assertTrue(result is GuessAttemptResult.Correct)
    }

    @Test
    fun `should set attempt count to maximum when correct guess is made on last attempt`() {
        guessGameUseCase.processGuess(20)
        guessGameUseCase.processGuess(40)
        guessGameUseCase.processGuess(30)
        assertEquals(3, guessGameUseCase.getGameState().attemptCount)
    }

    @Test
    fun `should finish the game when correct guess is made on last attempt`() {
        guessGameUseCase.processGuess(20)
        guessGameUseCase.processGuess(40)
        guessGameUseCase.processGuess(30)
        assertTrue(guessGameUseCase.getGameState().isFinished)
    }

    @Test
    fun `should update attempt count to 1 when first guess is made`() {
        guessGameUseCase.processGuess(20)
        assertEquals(1, guessGameUseCase.getGameState().attemptCount)
    }

    @Test
    fun `should update attempt count to 2 when second guess is made`() {
        guessGameUseCase.processGuess(20)
        guessGameUseCase.processGuess(25)
        assertEquals(2, guessGameUseCase.getGameState().attemptCount)
    }

    @Test
    fun `should update attempt count to 3 when third guess is made`() {
        guessGameUseCase.processGuess(20)
        guessGameUseCase.processGuess(25)
        guessGameUseCase.processGuess(35)
        assertEquals(3, guessGameUseCase.getGameState().attemptCount)
    }
}
