package org.example

import org.example.data.CsvFileReader
import org.example.data.CsvRecipesRepository
import org.example.logic.useCases.UseCaseHolder
import org.example.presentation.HolderCLi

fun main() {
    val csvFileReader = CsvFileReader()
    val repository = CsvRecipesRepository(csvFileReader)
    val useCases = UseCaseHolder(repository)
    val holderCli = HolderCLi()
    holderCli.startCLI(useCases)
}
