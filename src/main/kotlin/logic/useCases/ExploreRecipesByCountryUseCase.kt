import org.example.logic.RecipesRepository
import org.example.model.Recipe

const val NUMBER_OF_SEARCH_RECIPES = 20

class ExploreRecipesByCountryUseCase(private val repository: RecipesRepository) {

    fun searchCountryName(userInput: String): List<Recipe> {
        val country = userInput.trim().lowercase()
        val allRecipes = repository.getRecipes()
        if (country.isEmpty() || allRecipes.isEmpty()) return emptyList()

        val matchedRecipes = allRecipes.filterByCountry(country)
        return matchedRecipes?.shuffled()?.take(NUMBER_OF_SEARCH_RECIPES) ?: emptyList()
    }
}