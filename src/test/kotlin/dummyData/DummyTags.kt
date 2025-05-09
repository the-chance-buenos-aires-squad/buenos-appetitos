package dummyData
object DummyTags {
    // Dietary Preferences
    val dietary = listOf(
        "vegan",
        "vegetarian",
        "gluten-free",
        "low-carb",
        "keto",
        "high-protein",
        "dairy-free",
        "egg-free",
        "nut-free",
        "paleo"
    )

    // Meal Types
    val mealTypesWithDessert = listOf(
        "breakfast",
        "lunch",
        "dinner",
        "snack",
        "dessert",
        "appetizer"
    )

    val mealTypesWithOutDessert = listOf(
        "breakfast",
        "lunch",
        "dinner",
        "snack",
        "appetizer"
    )

    // Cuisines
    val countriesTags = listOf(
        "Italian",
        "Mexican",
        "Japanese",
        "Indian",
        "Mediterranean",
        "Chinese",
        "American",
        "French",
        "Thai",
        "Greek",
    )

    // Preparation Styles
    val preparation = listOf(
        "quick",
        "meal-prep",
        "no-cook",
        "oven-baked",
        "slow-cooker",
        "one-pan",
        "grilled",
        "fried"
    )

    // Special Use Cases
    val special = listOf(
        "for-large-groups",
        "comfort-food",
        "kid-friendly",
        "immunity-boosting",
        "low-sodium",
        "high-fiber"
    )

    // Combined list of all tags
    val allTags = dietary + mealTypesWithDessert + countriesTags + preparation + special
}