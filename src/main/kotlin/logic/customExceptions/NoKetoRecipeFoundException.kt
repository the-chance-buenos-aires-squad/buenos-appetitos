package org.example.logic.customExceptions

class NoKetoRecipeFoundException (
    message: String = "No Keto Recipes found"
) : RuntimeException(message)