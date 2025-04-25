package org.example.logic.customExceptions

class NoSweetFreeEggRecipeFoundException (
    private val date: String = "",
    message: String = "No sweet free egg recipe found"
) : RuntimeException(message)