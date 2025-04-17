import org.example.logic.RecipesRepository
import org.example.model.Recipe

class LovePotatoUseCase(private val repository: RecipesRepository) {

    fun getRandomPotatoRecipes(): List<Recipe> {
        val allRecipes = repository.getRecipes()
        val potatoRecipes = allRecipes.filter { recipe ->
            recipe.ingredients.any { ingredient ->
                ingredient.contains("potato", ignoreCase = true) ||
                        ingredient.contains("potatoes", ignoreCase = true)
            }
        }
        return if (potatoRecipes.size <= 10) potatoRecipes
        else potatoRecipes.shuffled().take(10)

    }
}