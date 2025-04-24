package org.example.logic.useCases

import org.example.logic.RecipesRepository
import org.example.logic.customExceptions.NoRecipesWithIngredientsException
import org.example.model.IngredientGameRound
import kotlin.random.Random

class IngredientGameUseCase(private val repository: RecipesRepository) {

    private var score = 0
    private var currentRound = 0
    private var hasGameEnded = false
    private var correctAnswer: String = ""

    fun startNewGame() {
        score = 0
        currentRound = 0
        hasGameEnded = false
    }

    fun createNewRound(): IngredientGameRound {

        val availableRecipesWithIngredients = repository.getRecipes().filter { it.ingredients.isNotEmpty() }
        if (availableRecipesWithIngredients.isEmpty()) throw NoRecipesWithIngredientsException()
        val allIngredients = availableRecipesWithIngredients.flatMap { it.ingredients }.distinct()
        val randomRecipe = availableRecipesWithIngredients.random()
        correctAnswer = randomRecipe.ingredients.random()

        val wrongOptions = allIngredients.getRandomWrongOptions(correctAnswer)

        val allOptions = (wrongOptions + correctAnswer).shuffled()

        currentRound++

        return IngredientGameRound(
            meal = randomRecipe,
            questionChoices = allOptions,
            correct = correctAnswer
        )
    }

    fun checkAnswer(selected: String): Boolean {
        val isCorrect = selected == correctAnswer

        if (isCorrect) {
            score += 1000
        } else {
            hasGameEnded = true
        }
        return isCorrect
    }

    fun isGameEnd(): Boolean = hasGameEnded || currentRound >= totalIngredientGameRound

    fun getScore(): Int = score

    fun getCurrentRound(): Int = currentRound

    private fun List<String>.getRandomWrongOptions(
        correctAnswer: String,
        numberOfOptions: Int = numberOfWrongAnswer
    ): List<String> {
        val filteredIngredients = this.filterNot { it.equals(correctAnswer, ignoreCase = true) }.toMutableList()
        val wrongOptions = mutableListOf<String>()

        while (wrongOptions.size < numberOfOptions) {
            val randomIndex = Random.nextInt(filteredIngredients.size)
            val option = filteredIngredients.removeAt(randomIndex)
            wrongOptions.add(option)
        }

        return wrongOptions
    }

    companion object {
        const val totalIngredientGameRound = 15
        private const val numberOfWrongAnswer = 2
    }

}