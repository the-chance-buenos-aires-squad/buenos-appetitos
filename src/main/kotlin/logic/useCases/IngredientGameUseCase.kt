package org.example.logic.useCases

import org.example.logic.RecipesRepository
import org.example.model.IngredientGameRound

class IngredientGameUseCase(repository: RecipesRepository) {

    private val allMeals = repository.getRecipes().filter { it.ingredients.isNotEmpty() }
    private val allIngredients = allMeals.flatMap { it.ingredients }.distinct()
    private var score = 0
    private var currentRound = 0
    private var isGameOver = false

    fun generateRound(): IngredientGameRound {

        if (isGameOver()) {
            throw IllegalStateException("Game is over")
        }

        val meal = allMeals.random()
        val correct = meal.ingredients.random()

        val wrongOptions = allIngredients
            .filterNot { it.equals(correct, ignoreCase = true) }.shuffled().take(2)

        val allOptions = (wrongOptions + correct).shuffled()

        currentRound++

        return IngredientGameRound(
            meal = meal,
            questionChoices = allOptions,
            correct = correct
        )
    }

    fun startNewGame() {
        score = 0
        currentRound = 0
        isGameOver = false
    }

    fun checkAnswer(selected: String?, correct: String): Boolean {
        val isCorrect = selected?.equals(correct, ignoreCase = true) == true

        if (isCorrect) {
            score += 1000
        } else {
            isGameOver = true
        }
        return isCorrect
    }

    fun isGameOver(): Boolean = isGameOver || currentRound >= totalRounds

    fun getScore(): Int = score

    fun getCurrentRound(): Int = currentRound

    fun getTotalRounds(): Int = totalRounds

    companion object {
        private const val totalRounds = 15
    }

}