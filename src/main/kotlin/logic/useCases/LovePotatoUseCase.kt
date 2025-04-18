import org.example.logic.RecipesRepository
import org.example.model.Recipe

class LovePotatoUseCase(private val repository: RecipesRepository) {

    fun getRandomPotatoRecipes(): List<Recipe> {
        val allRecipes = repository.getRecipes()

        if (allRecipes.isEmpty()) throw Exception("No recipes found")

        val potatoRecipes = allRecipes.potatoLoverList()

        return if (potatoRecipes.size <= 10) potatoRecipes
        else potatoRecipes.shuffled().take(10)
    }
}

private fun List<Recipe>.potatoLoverList(): List<Recipe> {
    return this.filter { recipe ->
        recipe.ingredients.any { ingredient ->
            ingredient.contains("potato", ignoreCase = true) ||
                    ingredient.contains("potatoes", ignoreCase = true)
        }
    }
}