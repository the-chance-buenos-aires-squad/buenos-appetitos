package logic.customExceptions

class NoRecipeFoundException(
    message: String = "No recipe found for date"
) : RuntimeException(message)