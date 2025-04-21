package org.example.presentation

import org.example.logic.useCases.IngredientGameUseCase
import java.util.Scanner

class IngredientGameCLI(private val ingredientGameUseCase: IngredientGameUseCase) {


     fun start() {
        val scanner = Scanner(System.`in`)
        var score = 0

        println("🎮 Welcome to the INGREDIENT GAME!")
        println("✅ One point per correct answer. ❌ Game ends on wrong answer.")

        repeat(15) {
            val ingredientGameRound = ingredientGameUseCase.generateRound()

            println("\n🍽️ Meal: ${ingredientGameRound.meal.name}")
            println("Which of the following is an ingredient?")
            ingredientGameRound.options.forEachIndexed { index, option ->
                println("${index + 1}. $option")
            }

            print("Your choice (1–3): ")
            val choice = scanner.nextLine().toIntOrNull()
            val selected = ingredientGameRound.options.getOrNull((choice ?: 0) - 1)

            if (ingredientGameUseCase.checkAnswer(selected, ingredientGameRound.correct)) {
                score += 1000
                println("✅ Correct! Your score: $score")
            } else {
                println("❌ Wrong! The correct answer was: ${ingredientGameRound.correct}")
                println("🎯 Final Score: $score")
                return
            }
        }

        println("\n🎉 You completed all rounds! Final Score: $score")
    }

}