package logic.customExceptions

class EmptyInputException(
    message: String = "No input given from the user"
) : RuntimeException(message)