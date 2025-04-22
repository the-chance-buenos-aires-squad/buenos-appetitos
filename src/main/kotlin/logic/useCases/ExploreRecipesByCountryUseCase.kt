import org.example.logic.RecipesRepository
import org.example.model.Recipe


class ExploreRecipesByCountryUseCase(private val repository: RecipesRepository) {

    fun searchCountryName(userInput: String): List<Recipe> {
        val country = userInput.trim().lowercase()
        if (country.isEmpty()) throw Exception("Country name cannot be empty")

        val allRecipes = repository.getRecipes()
        if (allRecipes.isEmpty()) return emptyList()

        val matchedRecipes = allRecipes.filterByCountry(country)
        return matchedRecipes?.shuffled()?.take(NUMBER_OF_SEARCH_RECIPES) ?: emptyList()
    }

    companion object {
        const val NUMBER_OF_SEARCH_RECIPES = 20

    }
}