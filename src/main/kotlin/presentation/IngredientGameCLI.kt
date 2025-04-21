package org.example.presentation

import org.example.logic.useCases.IngredientGameUseCase
import org.example.model.IngredientGameRound

class IngredientGameCLI(private val ingredientGameUseCase: IngredientGameUseCase) {

    fun start() {
        ingredientGameUseCase.startNewGame()
        printIntroMessage()

        while (!ingredientGameUseCase.isGameOver()) {
            playRound()
        }

        printSuccessMessage()
    }

    private fun playRound() {
        val round = ingredientGameUseCase.generateRound()
        displayRound(round)

        val userChoice = getUserChoice(round.questionChoices)

        handleAnswer(userChoice, round.correct)

        displayScore()
    }

    private fun printSuccessMessage() {
        if (ingredientGameUseCase.getCurrentRound() == ingredientGameUseCase.getTotalRounds()) {
            println("\n🎉 You completed all rounds !")
        }
    }

    private fun displayScore() = println("🎯 Final Score: ${ingredientGameUseCase.getScore()}")


    private fun handleAnswer(selected: String?, correct: String) {
        if (ingredientGameUseCase.checkAnswer(selected, correct)) {
            println("✅ Correct! Your score: ${ingredientGameUseCase.getScore()}")
        } else {
            println("❌ Wrong! The correct answer was: $correct")
        }
    }

    private fun getUserChoice(choices: List<String>): String? {
        print("\nYour choice (1–3): ")
        val userInputChoice = readln().trim().toIntOrNull()
        return choices.getOrNull((userInputChoice ?: 0) - 1)
    }

    private fun displayRound(round: IngredientGameRound) {
        println("\n🍽️ Meal: ${round.meal.name}")
        println("Which of the following is an ingredient?\n")

        round.questionChoices.forEachIndexed { index, choice ->
            println("${index + 1}. $choice")
        }
    }

    private fun printIntroMessage() {
        println("\n🎮 Welcome to the INGREDIENT GAME!")
        println("✅ One point per correct answer. ❌ Game ends on wrong answer.")
    }


}