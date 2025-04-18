package org.example

import org.example.data.CsvFileReader
import org.example.data.CsvRecipesRepository
import org.example.logic.useCases.UseCaseHolder
import org.example.presentation.HolderCLi

fun main() {
    val csvFileReader = CsvFileReader()
    val repository = CsvRecipesRepository(csvFileReader)
    val lovePotatoUseCase = LovePotatoUseCase(repository)
    val holderCli = HolderCLi(lovePotatoUseCase)
    holderCli.startCLI(useCases)
}
