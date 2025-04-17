package org.example.data

import java.nio.file.Files
import java.nio.file.Paths
import java.util.stream.Collectors

class CsvFileReader {
    
      fun readCsvFile(): List<List<String>> {
        val filePath = "src/main/kotlin/data/food.csv"
        
        return Files.lines(Paths.get(filePath))
            .skip(1) 
            .collect(Collectors.joining("\n"))
            .let { parseCSVWithMultilineFields(it) }
    }
    private fun parseCSVWithMultilineFields(content: String): List<List<String>> {
        val records = mutableListOf<List<String>>()
        val fields = mutableListOf<String>()
        val currentField = StringBuilder()

        var inQuotes = false
        var i = 0

        while (i < content.length) {
            val char = content[i]

            when {
                char == '"' -> {
                    if (i + 1 < content.length && content[i + 1] == '"') {
                        currentField.append('"')
                        i++
                    } else {
                        inQuotes = !inQuotes
                    }
                }
                char == ',' && !inQuotes -> {
                    fields.add(currentField.toString().trim())
                    currentField.clear()
                }
                char == '\n' && !inQuotes -> {
                    fields.add(currentField.toString().trim())
                    if (fields.isNotEmpty()) {
                        records.add(fields.toList())
                        fields.clear()
                    }
                    currentField.clear()
                }
                else -> {
                    currentField.append(char)
                }
            }

            i++
        }

        if (currentField.isNotEmpty() || fields.isNotEmpty()) {
            fields.add(currentField.toString().trim())
            records.add(fields.toList())
        }

        return records
    }
}