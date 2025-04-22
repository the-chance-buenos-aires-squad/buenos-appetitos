import logic.useCases.IdentifyIraqiRecipesUseCase
import org.example.presentation.displyUtils.displayDetails

class IraqiRecipesCli(
    private val identifyIraqiRecipesUseCase: IdentifyIraqiRecipesUseCase
) {
    fun startCli() {
        println("Listing Iraqi Recipes")

        val results = identifyIraqiRecipesUseCase.getIraqiRecipes()

        if (results.isEmpty()) {
            println("No recipes found for Iraq")
        }
        println("Found ${results.size} recipes for Iraq\n")
        results.forEachIndexed { index, recipe ->
            println("(${index + 1})")
            recipe.displayDetails()
            println()
        }
    }

}
