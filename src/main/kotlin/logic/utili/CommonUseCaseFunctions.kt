import org.example.model.Recipe

fun List<Recipe>.filterByCountry(country: String): List<Recipe>? {
    return this.filterNotNull().filter{ recipe ->
        val tagsMatch = recipe.tags.any { it.contains(country, ignoreCase = true) }
        val descriptionMatch = recipe.description?.contains(country, ignoreCase = true) ?: false
        tagsMatch || descriptionMatch
    }
}