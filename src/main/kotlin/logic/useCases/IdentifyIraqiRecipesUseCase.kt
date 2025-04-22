import org.example.logic.RecipesRepository
import org.example.model.Recipe

class IdentifyIraqiRecipesUseCase(private val repository: RecipesRepository) {

    fun getIraqiRecipes(): List<Recipe> {
        return repository.getRecipes().filterByCountry("iraq") ?: emptyList()
    }
}
