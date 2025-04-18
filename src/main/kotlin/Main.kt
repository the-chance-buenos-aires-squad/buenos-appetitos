package org.example

import org.example.data.CsvFileReader
import org.example.data.CsvRecipesRepository
import org.example.logic.useCases.UseCaseHolder
import org.example.presentation.HolderCLi

fun main() {
    val csvFileReader = CsvFileReader()
    val repository = CsvRecipesRepository(csvFileReader)
    val highCalorieUseCase = HighCalorieUseCase(repository)
    val holderCli = HolderCLi(highCalorieUseCase)
    holderCli.startCLI(useCases)
}
