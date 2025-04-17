import org.example.logic.RecipesRepository
import org.example.model.Recipe

class HighCalorieUseCase (private val repository: RecipesRepository){
    fun getRandomHighCalorieRecipe(minCalories: Int = 700): List<Recipe> {
        val allRecipes = repository.getRecipes()

        val highCalorieRecipes = allRecipes.filter { recipe ->
            recipe.nutrition.calories > minCalories
        }

        return if (highCalorieRecipes.isNotEmpty()) {
            highCalorieRecipes.shuffled().take(1)
        } else {
            emptyList()
        }
    }
}