package dummyData

import model.Nutrition

object DummyNutrition {
    // High-protein, low-carb (suitable for keto)
    val ketoFriendly = Nutrition(
        calories = 600.0,
        fat = 45.0,
        sugar = 5.0,
        sodium = 800.0,
        protein = 35.0,
        saturatedFat = 12.0,
        carbohydrates = 8.0
    )

    // High-calorie (for "So Thin Problem" use case)
    val highCalorie = Nutrition(
        calories = 850.0,
        fat = 55.0,
        sugar = 20.0,
        sodium = 1200.0,
        protein = 30.0,
        saturatedFat = 18.0,
        carbohydrates = 60.0
    )

    // Balanced nutrition (generic healthy meal)
    val balanced = Nutrition(
        calories = 400.0,
        fat = 15.0,
        sugar = 10.0,
        sodium = 500.0,
        protein = 25.0,
        saturatedFat = 5.0,
        carbohydrates = 45.0
    )

    // Low-sodium, low-fat (for heart-healthy diets)
    val heartHealthy = Nutrition(
        calories = 300.0,
        fat = 8.0,
        sugar = 8.0,
        sodium = 200.0,
        protein = 20.0,
        saturatedFat = 2.0,
        carbohydrates = 35.0
    )

    // High-protein seafood (for seafood sorting use case)
    val highProteinSeafood = Nutrition(
        calories = 450.0,
        fat = 20.0,
        sugar = 2.0,
        sodium = 600.0,
        protein = 40.0, // High protein
        saturatedFat = 4.0,
        carbohydrates = 10.0
    )

    // High-carb Italian (for group meals)
    val italianGroupMeal = Nutrition(
        calories = 350.0,
        fat = 15.0,
        sugar = 8.0,
        sodium = 800.0,
        protein = 20.0,
        saturatedFat = 6.0,
        carbohydrates = 35.0
    )

    // Non-keto example (fails keto filter)
    val nonKeto = Nutrition(
        calories = 500.0,
        fat = 30.0,//❌
        sugar = 10.0,
        sodium = 700.0,
        protein = 25.0,//❌
        saturatedFat = 20.0,//❌
        carbohydrates = 45.0//❌
    )

    // List of all predefined nutrition profiles
    val list = listOf(
        ketoFriendly,
        highCalorie,
        balanced,
        heartHealthy,
        highProteinSeafood,
        italianGroupMeal,
        nonKeto
    )
}