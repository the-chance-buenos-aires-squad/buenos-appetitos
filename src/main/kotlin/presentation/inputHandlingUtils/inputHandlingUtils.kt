package org.example.presentation.inputHandlingUtils

import org.example.presentation.customExceptions.EmptyInputException


fun readUserInput(): String {
    val userInput = readln()
    if (userInput.isBlank()) throw EmptyInputException()
    return userInput
}


fun handleUserInput() = try {
    readUserInput()
} catch (e: EmptyInputException) {
    println("you didn't enter anything redirecting back to main menu")
    ""
} catch (e: NullPointerException) {
    println("incorrect Input .... redirecting to main menu")
    ""
}