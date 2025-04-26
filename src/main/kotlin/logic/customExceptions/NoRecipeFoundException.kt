package logic.customExceptions

class NoRecipeFoundException(
    message: String = "No recipe found"
) : RuntimeException(message)