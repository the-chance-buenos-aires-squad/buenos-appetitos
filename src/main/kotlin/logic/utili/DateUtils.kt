package org.example.logic.utili

import org.example.model.Recipe
import java.time.DateTimeException
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

object DateUtils {


    fun parseToLocalDate(dateStr: String, pattern: String = "yyyy-MM-dd"): LocalDate {
        val formatter = DateTimeFormatter.ofPattern(pattern)
        return LocalDate.parse(dateStr, formatter)
    }
}
