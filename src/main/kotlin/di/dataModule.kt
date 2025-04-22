package di

import org.example.data.CsvFileReader
import org.example.data.CsvRecipesRepository
import org.example.data.MemoryDataSource
import org.example.data.RecipeParser
import org.example.logic.RecipesRepository
import org.koin.dsl.module
import java.io.File

val dataModule = module {
    single { File("src/main/kotlin/data/food.csv") }
    single { CsvFileReader(get()) }
    single { RecipeParser() }
    single { MemoryDataSource() }
    single<RecipesRepository> { CsvRecipesRepository(get(), get(), get()) }
}