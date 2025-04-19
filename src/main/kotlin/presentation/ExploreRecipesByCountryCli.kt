class ExploreRecipesByCountryCli(
    private val exploreRecipesByCountryUseCase: ExploreRecipesByCountryUseCase
) {

    fun startCli() {
        println("Please enter the country name to search for its recipes:")
        while (true) {
            print("Enter a country (or 'exit'): ")
            val userInput = readlnOrNull()?.trim() ?: continue

            if (userInput.equals("exit", ignoreCase = true)) {
                println("Goodbye!")
                break
            }
            val results = exploreRecipesByCountryUseCase.searchCountryName(userInput)
            if (results.isEmpty()) {
                println("No recipes found for \"$userInput\".")
            }
            val recipeForm = RecipesForm()
            println("Found ${results.size} recipes for \"$userInput\"\n")
            results.forEachIndexed  { index, recipe ->
                println("(${index + 1})")
                recipeForm.printingRecipes(recipe)
                println()
            }
        }
    }
}
