import org.example.data.CsvFileReader
import org.example.data.CsvRecipesRepository

fun main() {
    val csvFileReader = CsvFileReader()
    val repository = CsvRecipesRepository(csvFileReader)
    val identifyIraqiRecipesUseCase = IdentifyIraqiRecipesUseCase(repository)

    println("Listing Iraqi Recipes")

    val results = identifyIraqiRecipesUseCase.getIraqiRecipes()

    if (results.isEmpty()) {
        println("No recipes found for Iraq")
    }
    val recipeForm = RecipesForm()
    println("Found ${results.size} recipes for Iraq\n")
    results.forEachIndexed  { index, recipe ->
        println("(${index + 1})")
        recipeForm.printingRecipes(recipe)
        println()
    }
}
