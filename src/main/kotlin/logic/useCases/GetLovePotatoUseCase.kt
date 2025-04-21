import org.example.logic.RecipesRepository
import org.example.model.Recipe

class GetLovePotatoUseCase(private val repository: RecipesRepository) {

    fun getRandomPotatoRecipes(): List<Recipe> {
        val allRecipes = repository.getRecipes()

        if (allRecipes.isEmpty()) return emptyList()

        val potatoRecipes = allRecipes.potatoLoverList()

        return if (potatoRecipes.size <= MAX_POTATO_MEALS) potatoRecipes
        else potatoRecipes.shuffled().take(MAX_POTATO_MEALS)
    }

    companion object {
        private const val MAX_POTATO_MEALS = 10
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
