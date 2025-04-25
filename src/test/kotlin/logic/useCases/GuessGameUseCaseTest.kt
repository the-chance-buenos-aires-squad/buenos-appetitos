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
        // Given
        randomRecipeUseCase = mockk()
        every { randomRecipeUseCase.getRandomRecipe() } returns testRecipe
        
        // When
        guessGameUseCase = GuessGameUseCase(randomRecipeUseCase)
    }

    @Test
    fun `should call random recipe use case when game is initialized`() {
        // Then
        verify(exactly = 1) { randomRecipeUseCase.getRandomRecipe() }
    }

    @Test
    fun `should set correct recipe when game is initialized`() {
        // When
        val gameState = guessGameUseCase.getGameState()
        
        // Then
        assertEquals(testRecipe, gameState.recipe)
    }

    @Test
    fun `should set attempt count to zero when game is initialized`() {
        // When
        val gameState = guessGameUseCase.getGameState()
        
        // Then
        assertEquals(0, gameState.attemptCount)
    }

    @Test
    fun `should set game as not finished when game is initialized`() {
        // When
        val gameState = guessGameUseCase.getGameState()
        
        // Then
        assertFalse(gameState.isFinished)
    }

    @Test
    fun `should return Correct result when guess matches preparation time`() {
        // When
        val result = guessGameUseCase.processGuess(30)
        
        // Then
        assertTrue(result is GuessAttemptResult.Correct)
    }

    @Test
    fun `should increase attempt count when correct guess is made`() {
        // When
        guessGameUseCase.processGuess(30)
        
        // Then
        assertEquals(1, guessGameUseCase.getGameState().attemptCount)
    }

    @Test
    fun `should finish the game when correct guess is made`() {
        // When
        guessGameUseCase.processGuess(30)
        
        // Then
        assertTrue(guessGameUseCase.getGameState().isFinished)
    }

    @Test
    fun `should return TooLow result when guess is below preparation time`() {
        // When
        val result = guessGameUseCase.processGuess(20)
        
        // Then
        assertTrue(result is GuessAttemptResult.TooLow)
    }

    @Test
    fun `should increase attempt count when low guess is made`() {
        // When
        guessGameUseCase.processGuess(20)
        
        // Then
        assertEquals(1, guessGameUseCase.getGameState().attemptCount)
    }

    @Test
    fun `should not finish the game when low guess is made`() {
        // When
        guessGameUseCase.processGuess(20)
        
        // Then
        assertFalse(guessGameUseCase.getGameState().isFinished)
    }

    @Test
    fun `should return TooHigh result when guess is above preparation time`() {
        // When
        val result = guessGameUseCase.processGuess(40)
        
        // Then
        assertTrue(result is GuessAttemptResult.TooHigh)
    }

    @Test
    fun `should increase attempt count when high guess is made`() {
        // When
        guessGameUseCase.processGuess(40)
        
        // Then
        assertEquals(1, guessGameUseCase.getGameState().attemptCount)
    }

    @Test
    fun `should not finish the game when high guess is made`() {
        // When
        guessGameUseCase.processGuess(40)
        
        // Then
        assertFalse(guessGameUseCase.getGameState().isFinished)
    }

    @Test
    fun `should return GameOver result when maximum attempts are reached`() {
        // Given
        guessGameUseCase.processGuess(20) // First attempt
        guessGameUseCase.processGuess(40) // Second attempt
        
        // When
        val result = guessGameUseCase.processGuess(25) // Third attempt
        
        // Then
        assertTrue(result is GuessAttemptResult.GameOver)
    }

    @Test
    fun `should include correct answer when game is over`() {
        // Given
        guessGameUseCase.processGuess(20)
        guessGameUseCase.processGuess(40)
        
        // When
        val result = guessGameUseCase.processGuess(25) as GuessAttemptResult.GameOver
        
        // Then
        assertEquals(30, result.correctTime)
    }

    @Test
    fun `should set attempt count to maximum when game is over`() {
        // Given
        guessGameUseCase.processGuess(20)
        guessGameUseCase.processGuess(40)
        
        // When
        guessGameUseCase.processGuess(25)
        
        // Then
        assertEquals(3, guessGameUseCase.getGameState().attemptCount)
    }

    @Test
    fun `should finish the game when maximum attempts are reached`() {
        // Given
        guessGameUseCase.processGuess(20)
        guessGameUseCase.processGuess(40)
        
        // When
        guessGameUseCase.processGuess(25)
        
        // Then
        assertTrue(guessGameUseCase.getGameState().isFinished)
    }

    @Test
    fun `should return Correct result when correct guess is made on last attempt`() {
        // Given
        guessGameUseCase.processGuess(20)
        guessGameUseCase.processGuess(40)
        
        // When
        val result = guessGameUseCase.processGuess(30)
        
        // Then
        assertTrue(result is GuessAttemptResult.Correct)
    }

    @Test
    fun `should set attempt count to maximum when correct guess is made on last attempt`() {
        // Given
        guessGameUseCase.processGuess(20)
        guessGameUseCase.processGuess(40)
        
        // When
        guessGameUseCase.processGuess(30)
        
        // Then
        assertEquals(3, guessGameUseCase.getGameState().attemptCount)
    }

    @Test
    fun `should finish the game when correct guess is made on last attempt`() {
        // Given
        guessGameUseCase.processGuess(20)
        guessGameUseCase.processGuess(40)
        
        // When
        guessGameUseCase.processGuess(30)
        
        // Then
        assertTrue(guessGameUseCase.getGameState().isFinished)
    }

    @Test
    fun `should update attempt count to 1 when first guess is made`() {
        // When
        guessGameUseCase.processGuess(20)
        
        // Then
        assertEquals(1, guessGameUseCase.getGameState().attemptCount)
    }

    @Test
    fun `should update attempt count to 2 when second guess is made`() {
        // Given
        guessGameUseCase.processGuess(20)
        
        // When
        guessGameUseCase.processGuess(25)
        
        // Then
        assertEquals(2, guessGameUseCase.getGameState().attemptCount)
    }

    @Test
    fun `should update attempt count to 3 when third guess is made`() {
        // Given
        guessGameUseCase.processGuess(20)
        guessGameUseCase.processGuess(25)
        
        // When
        guessGameUseCase.processGuess(35)
        
        // Then
        assertEquals(3, guessGameUseCase.getGameState().attemptCount)
    }
}