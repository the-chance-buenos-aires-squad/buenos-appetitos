package dummyData

import model.Nutrition
import org.example.model.Recipe
import java.time.LocalDate

object DummyRecipes {
    val healthyFastFoodRecipes = listOf(
        // Use Case 1: Healthy Fast Food (15 mins or less, low fat/carbs)
        Recipe(
            name = "Zucchini Noodles with Avocado Pesto",
            id = "HEALTHY_001",
            minutes = 15,
            contributorId = "user_health123",
            submitted = LocalDate.of(2023, 10, 5),
            tags = listOf("low-fat", "low-carb", "vegan", "quick"),
            nutrition = Nutrition(
                calories = 280.0,
                fat = 8.0,      // Low total fat
                sugar = 4.0,
                sodium = 200.0,
                protein = 6.0,
                saturatedFat = 1.2,  // Very low saturated fat
                carbohydrates = 18.0  // Low carbs
            ),
            numberOfSteps = 4,
            steps = listOf(
                "Spiralize zucchini into noodles.",
                "Blend avocado, basil, garlic, lemon juice, and olive oil for pesto.",
                "Toss zucchini noodles with pesto.",
                "Top with cherry tomatoes and pine nuts."
            ),
            description = "A light, refreshing meal packed with healthy fats and fiber.",
            ingredients = listOf("zucchini", "avocado", "basil", "lemon", "cherry tomatoes"),
            numberOfIngredients = 5
        ),
        Recipe(
            name = "Turmeric Chickpea Stir-Fry",
            id = "HEALTHY_002",
            minutes = 12,
            contributorId = "user_veganchef",
            submitted = LocalDate.of(2023, 9, 20),
            tags = listOf("high-protein", "gluten-free", "anti-inflammatory", "quick"),
            nutrition = Nutrition(
                calories = 220.0,
                fat = 4.5,
                sugar = 3.0,
                sodium = 300.0,
                protein = 12.0,
                saturatedFat = 0.6,
                carbohydrates = 22.0
            ),
            numberOfSteps = 3,
            steps = listOf(
                "Sauté chickpeas with turmeric, cumin, and garlic.",
                "Add spinach and cherry tomatoes.",
                "Serve with a squeeze of lemon."
            ),
            description = "A protein-rich, anti-inflammatory dish ready in minutes.",
            ingredients = listOf("chickpeas", "turmeric", "spinach", "lemon", "cherry tomatoes"),
            numberOfIngredients = 5
        ),
        Recipe(
            name = "Spicy Tuna Lettuce Wraps",
            id = "HEALTHY_003",
            minutes = 10,
            contributorId = "user_fishlover",
            submitted = LocalDate.of(2023, 11, 1),
            tags = listOf("low-carb", "high-protein", "omega-3", "no-cook"),
            nutrition = Nutrition(
                calories = 180.0,
                fat = 5.0,
                sugar = 2.0,
                sodium = 350.0,
                protein = 25.0,
                saturatedFat = 1.0,
                carbohydrates = 8.0
            ),
            numberOfSteps = 2,
            steps = listOf(
                "Mix canned tuna with Greek yogurt, sriracha, and lime juice.",
                "Spoon into lettuce cups and top with diced cucumber."
            ),
            description = "A no-cook, high-protein lunch with a kick!",
            ingredients = listOf("canned tuna", "Greek yogurt", "lettuce", "cucumber", "lime"),
            numberOfIngredients = 5
        ),
        Recipe(
            name = "Miso-Ginger Broccoli Bowl",
            id = "HEALTHY_004",
            minutes = 14,
            contributorId = "user_asiafoodie",
            submitted = LocalDate.of(2023, 8, 15),
            tags = listOf("vegan", "low-sodium", "high-fiber", "immunity-boosting"),
            nutrition = Nutrition(
                calories = 200.0,
                fat = 6.0,
                sugar = 5.0,
                sodium = 400.0,
                protein = 8.0,
                saturatedFat = 0.8,
                carbohydrates = 15.0
            ),
            numberOfSteps = 3,
            steps = listOf(
                "Steam broccoli and edamame for 5 mins.",
                "Whisk miso paste, ginger, rice vinegar, and sesame oil for dressing.",
                "Toss broccoli with dressing and serve over quinoa."
            ),
            description = "A fiber-rich bowl with immunity-boosting ingredients.",
            ingredients = listOf("broccoli", "edamame", "miso paste", "ginger", "quinoa"),
            numberOfIngredients = 5
        ),
        Recipe(
            name = "Chia Seed Protein Pudding",
            id = "HEALTHY_005",
            minutes = 5,
            contributorId = "user_breakfastking",
            submitted = LocalDate.of(2023, 12, 10),
            tags = listOf("no-cook", "high-protein", "low-sugar", "meal-prep"),
            nutrition = Nutrition(
                calories = 190.0,
                fat = 7.0,
                sugar = 3.0,
                sodium = 100.0,
                protein = 15.0,
                saturatedFat = 1.5,
                carbohydrates = 12.0
            ),
            numberOfSteps = 2,
            steps = listOf(
                "Mix chia seeds, almond milk, and protein powder in a jar.",
                "Refrigerate overnight and top with berries before serving."
            ),
            description = "A no-cook, protein-packed breakfast or snack.",
            ingredients = listOf("chia seeds", "almond milk", "protein powder", "mixed berries"),
            numberOfIngredients = 4
        )
    )

    // Use Case 2: Typo-Tolerant Search example
    private val searchTestRecipes = listOf(
        Recipe(
            name = "Cheezy Pasta Bake", // Intentional typo ("Cheezy" vs "Cheesy")
            id = "SEARCH_001",
            minutes = 25,
            contributorId = "user_pastaLover",
            submitted = LocalDate.of(2023, 10, 10),
            tags = listOf("comfort-food", "vegetarian"),
            nutrition = Nutrition(
                calories = 400.0, fat = 12.0, sugar = 8.0, sodium = 600.0,
                protein = 15.0, saturatedFat = 4.0, carbohydrates = 45.0
            ),
            numberOfSteps = 5,
            steps = listOf("Preheat oven...", "Mix ingredients..."),
            description = "A creamy, cheesy pasta bake with a golden crust.",
            ingredients = listOf("pasta", "cheddar", "milk", "flour"),
            numberOfIngredients = 4
        ),
        Recipe(
            name = "Chickn Stir-Fry", // Abbreviation ("Chickn" vs "Chicken")
            id = "SEARCH_002",
            minutes = 15,
            contributorId = "user_quickmeals",
            submitted = LocalDate.of(2023, 9, 15),
            tags = listOf("low-carb", "high-protein"),
            nutrition = Nutrition(
                calories = 280.0, fat = 8.0, sugar = 5.0, sodium = 450.0,
                protein = 25.0, saturatedFat = 1.5, carbohydrates = 12.0
            ),
            numberOfSteps = 4,
            steps = listOf("Slice chicken...", "Sauté vegetables..."),
            description = "A quick stir-fry with tender chicken and crisp veggies.",
            ingredients = listOf("chicken breast", "broccoli", "soy sauce"),
            numberOfIngredients = 3
        ),
        Recipe(
            name = "Tomatoe Basil Soup", // Common typo ("Tomatoe" vs "Tomato")
            id = "SEARCH_003",
            minutes = 20,
            contributorId = "user_souplover",
            submitted = LocalDate.of(2023, 11, 1),
            tags = listOf("vegan", "gluten-free"),
            nutrition = Nutrition(
                calories = 150.0, fat = 5.0, sugar = 10.0, sodium = 300.0,
                protein = 3.0, saturatedFat = 0.5, carbohydrates = 20.0
            ),
            numberOfSteps = 3,
            steps = listOf("Simmer tomatoes...", "Blend with basil..."),
            description = "A classic soup with fresh basil and ripe tomatoes.",
            ingredients = listOf("tomatoes", "basil", "onion", "garlic"),
            numberOfIngredients = 4
        ),
        Recipe(
            name = "Chocolat Cake", // Missing letter ("Chocolat" vs "Chocolate")
            id = "SEARCH_004",
            minutes = 40,
            contributorId = "user_baker2023",
            submitted = LocalDate.of(2023, 12, 5),
            tags = listOf("dessert", "vegetarian"),
            nutrition = Nutrition(
                calories = 350.0, fat = 18.0, sugar = 30.0, sodium = 200.0,
                protein = 5.0, saturatedFat = 8.0, carbohydrates = 45.0
            ),
            numberOfSteps = 6,
            steps = listOf("Preheat oven...", "Mix dry ingredients..."),
            description = "A rich, decadent chocolate cake.",
            ingredients = listOf("flour", "cocoa powder", "sugar", "eggs"),
            numberOfIngredients = 4
        ),
        Recipe(
            name = "Veggie Burgr", // Missing letter ("Burgr" vs "Burger")
            id = "SEARCH_005",
            minutes = 20,
            contributorId = "user_veganchef",
            submitted = LocalDate.of(2023, 8, 20),
            tags = listOf("vegan", "high-protein"),
            nutrition = Nutrition(
                calories = 320.0, fat = 10.0, sugar = 5.0, sodium = 400.0,
                protein = 18.0, saturatedFat = 1.5, carbohydrates = 35.0
            ),
            numberOfSteps = 4,
            steps = listOf("Mash beans...", "Form patties..."),
            description = "A hearty vegan burger with black beans and spices.",
            ingredients = listOf("black beans", "oats", "onion", "spices"),
            numberOfIngredients = 4
        )
    )

    val easyRecipes = listOf(
        Recipe(
            name = "Spinach & Feta Omelet",
            id = "EASY_001",
            minutes = 10,
            contributorId = "user_easychef",
            submitted = LocalDate.of(2023, 1, 5),
            tags = listOf("vegetarian", "high-protein", "quick"),
            nutrition = Nutrition(
                calories = 250.0,
                fat = 15.0,
                sugar = 2.0,
                sodium = 400.0,
                protein = 18.0,
                saturatedFat = 5.0,
                carbohydrates = 5.0
            ),
            numberOfSteps = 3,
            steps = listOf(
                "Whisk eggs with salt and pepper.",
                "Cook spinach in a pan, add eggs, and sprinkle feta.",
                "Fold and serve."
            ),
            description = "A protein-packed breakfast in 10 minutes.",
            ingredients = listOf("eggs", "spinach", "feta cheese", "salt", "pepper"),
            numberOfIngredients = 5
        ),
        Recipe(
            name = "Caprese Salad",
            id = "EASY_002",
            minutes = 5,
            contributorId = "user_italianfood",
            submitted = LocalDate.of(2023, 2, 10),
            tags = listOf("vegetarian", "no-cook", "gluten-free"),
            nutrition = Nutrition(
                calories = 200.0,
                fat = 12.0,
                sugar = 3.0,
                sodium = 150.0,
                protein = 8.0,
                saturatedFat = 4.0,
                carbohydrates = 10.0
            ),
            numberOfSteps = 2,
            steps = listOf(
                "Slice tomatoes and mozzarella.",
                "Layer with basil, drizzle olive oil, and season."
            ),
            description = "A fresh, no-cook Italian classic.",
            ingredients = listOf("tomatoes", "mozzarella", "basil", "olive oil", "salt"),
            numberOfIngredients = 5
        ),
        Recipe(
            name = "Peanut Butter Banana Toast",
            id = "EASY_003",
            minutes = 3,
            contributorId = "user_breakfastlover",
            submitted = LocalDate.of(2023, 3, 15),
            tags = listOf("vegan", "quick", "5-ingredients"),
            nutrition = Nutrition(
                calories = 220.0,
                fat = 10.0,
                sugar = 12.0,
                sodium = 100.0,
                protein = 6.0,
                saturatedFat = 2.0,
                carbohydrates = 28.0
            ),
            numberOfSteps = 1,
            steps = listOf("Spread peanut butter on toast and top with banana slices."),
            description = "A 3-minute energy-boosting breakfast.",
            ingredients = listOf("bread", "peanut butter", "banana"),
            numberOfIngredients = 3
        ),
        Recipe(
            name = "Veggie Stir-Fry",
            id = "EASY_004",
            minutes = 20,
            contributorId = "user_veggiequeen",
            submitted = LocalDate.of(2023, 4, 20),
            tags = listOf("vegan", "low-carb", "quick"),
            nutrition = Nutrition(
                calories = 180.0,
                fat = 8.0,
                sugar = 5.0,
                sodium = 300.0,
                protein = 4.0,
                saturatedFat = 1.0,
                carbohydrates = 15.0
            ),
            numberOfSteps = 4,
            steps = listOf(
                "Chop bell peppers, broccoli, and carrots.",
                "Sauté garlic in oil.",
                "Add veggies and stir-fry for 5 mins.",
                "Season with soy sauce."
            ),
            description = "A colorful, crunchy stir-fry in 20 minutes.",
            ingredients = listOf("bell peppers", "broccoli", "carrots", "soy sauce", "garlic"),
            numberOfIngredients = 5
        ),
        Recipe(
            name = "Avocado Tuna Wrap",
            id = "EASY_005",
            minutes = 15,
            contributorId = "user_fishfan",
            submitted = LocalDate.of(2023, 5, 5),
            tags = listOf("high-protein", "low-carb", "quick"),
            nutrition = Nutrition(
                calories = 300.0,
                fat = 15.0,
                sugar = 2.0,
                sodium = 400.0,
                protein = 20.0,
                saturatedFat = 2.5,
                carbohydrates = 18.0
            ),
            numberOfSteps = 3,
            steps = listOf(
                "Mash avocado and mix with canned tuna.",
                "Spread on a tortilla.",
                "Add lettuce and roll."
            ),
            description = "A no-cook, protein-rich lunch.",
            ingredients = listOf("tortilla", "avocado", "canned tuna", "lettuce"),
            numberOfIngredients = 4
        ),
        Recipe(
            name = "Tomato Basil Pasta",
            id = "EASY_006",
            minutes = 25,
            contributorId = "user_pastalover",
            submitted = LocalDate.of(2023, 6, 10),
            tags = listOf("vegetarian", "comfort-food", "quick"),
            nutrition = Nutrition(
                calories = 350.0,
                fat = 10.0,
                sugar = 6.0,
                sodium = 200.0,
                protein = 12.0,
                saturatedFat = 3.0,
                carbohydrates = 45.0
            ),
            numberOfSteps = 4,
            steps = listOf(
                "Boil pasta.",
                "Sauté garlic in olive oil.",
                "Add chopped tomatoes and basil.",
                "Toss with pasta."
            ),
            description = "A simple, fresh pasta dish.",
            ingredients = listOf("pasta", "tomatoes", "basil", "garlic", "olive oil"),
            numberOfIngredients = 5
        ),
        Recipe(
            name = "Greek Yogurt Parfait",
            id = "EASY_007",
            minutes = 5,
            contributorId = "user_healthysnacks",
            submitted = LocalDate.of(2023, 7, 15),
            tags = listOf("vegetarian", "no-cook", "high-protein"),
            nutrition = Nutrition(
                calories = 200.0,
                fat = 2.0,
                sugar = 10.0,
                sodium = 50.0,
                protein = 15.0,
                saturatedFat = 1.0,
                carbohydrates = 25.0
            ),
            numberOfSteps = 2,
            steps = listOf(
                "Layer yogurt, granola, and berries in a bowl.",
                "Drizzle with honey."
            ),
            description = "A 5-minute breakfast or snack.",
            ingredients = listOf("Greek yogurt", "granola", "berries", "honey"),
            numberOfIngredients = 4
        ),
        Recipe(
            name = "Egg Fried Rice",
            id = "EASY_008",
            minutes = 20,
            contributorId = "user_asianfood",
            submitted = LocalDate.of(2023, 8, 20),
            tags = listOf("high-protein", "quick", "leftover-friendly"),
            nutrition = Nutrition(
                calories = 280.0,
                fat = 8.0,
                sugar = 3.0,
                sodium = 500.0,
                protein = 12.0,
                saturatedFat = 2.0,
                carbohydrates = 35.0
            ),
            numberOfSteps = 5,
            steps = listOf(
                "Scramble eggs in a pan.",
                "Add cooked rice and frozen peas.",
                "Stir in soy sauce.",
                "Cook for 5 mins.",
                "Garnish with green onions."
            ),
            description = "A quick way to use leftover rice.",
            ingredients = listOf("rice", "eggs", "soy sauce", "peas", "green onions"),
            numberOfIngredients = 5
        ),
        Recipe(
            name = "Black Bean Tacos",
            id = "EASY_009",
            minutes = 15,
            contributorId = "user_mexicanfood",
            submitted = LocalDate.of(2023, 9, 25),
            tags = listOf("vegan", "high-fiber", "quick"),
            nutrition = Nutrition(
                calories = 220.0,
                fat = 5.0,
                sugar = 2.0,
                sodium = 300.0,
                protein = 8.0,
                saturatedFat = 1.0,
                carbohydrates = 35.0
            ),
            numberOfSteps = 3,
            steps = listOf(
                "Warm tortillas.",
                "Mash black beans with cumin.",
                "Assemble with lettuce and salsa."
            ),
            description = "A vegan taco ready in 15 minutes.",
            ingredients = listOf("tortillas", "black beans", "lettuce", "salsa", "cumin"),
            numberOfIngredients = 5
        ),
        Recipe(
            name = "Hummus Veggie Sandwich",
            id = "EASY_010",
            minutes = 10,
            contributorId = "user_plantbased",
            submitted = LocalDate.of(2023, 10, 30),
            tags = listOf("vegan", "no-cook", "high-fiber"),
            nutrition = Nutrition(
                calories = 300.0,
                fat = 10.0,
                sugar = 5.0,
                sodium = 400.0,
                protein = 10.0,
                saturatedFat = 1.5,
                carbohydrates = 40.0
            ),
            numberOfSteps = 2,
            steps = listOf(
                "Spread hummus on bread.",
                "Layer cucumber, spinach, and shredded carrots."
            ),
            description = "A crunchy, no-cook vegan sandwich.",
            ingredients = listOf("bread", "hummus", "cucumber", "spinach", "carrots"),
            numberOfIngredients = 5
        )
    )

    val eggFreeSweets = listOf(
        Recipe(
            name = "Vegan Chocolate Avocado Mousse",
            id = "EGGFREE_001",
            minutes = 10,
            contributorId = "user_vegandesserts",
            submitted = LocalDate.of(2023, 5, 12),
            tags = listOf("vegan", "egg-free", "gluten-free"),
            nutrition = Nutrition(
                calories = 200.0,
                fat = 14.0,
                sugar = 12.0,
                sodium = 10.0,
                protein = 4.0,
                saturatedFat = 3.0,
                carbohydrates = 18.0
            ),
            numberOfSteps = 3,
            steps = listOf(
                "Blend ripe avocado, cocoa powder, and maple syrup.",
                "Add almond milk for consistency.",
                "Chill for 1 hour before serving."
            ),
            description = "A creamy, egg-free chocolate mousse made with avocado.",
            ingredients = listOf("avocado", "cocoa powder", "maple syrup", "almond milk"),
            numberOfIngredients = 4
        ),
        Recipe(
            name = "No-Bake Oatmeal Cookie Bites",
            id = "EGGFREE_002",
            minutes = 15,
            contributorId = "user_healthybaker",
            submitted = LocalDate.of(2023, 6, 8),
            tags = listOf("egg-free", "no-bake", "vegan"),
            nutrition = Nutrition(
                calories = 150.0,
                fat = 6.0,
                sugar = 8.0,
                sodium = 50.0,
                protein = 3.0,
                saturatedFat = 1.0,
                carbohydrates = 22.0
            ),
            numberOfSteps = 2,
            steps = listOf(
                "Mix oats, peanut butter, honey, and dark chocolate chips.",
                "Roll into balls and refrigerate."
            ),
            description = "Egg-free energy bites with oats and peanut butter.",
            ingredients = listOf("oats", "peanut butter", "honey", "dark chocolate chips"),
            numberOfIngredients = 4
        ),
        Recipe(
            name = "Coconut Chia Pudding",
            id = "EGGFREE_003",
            minutes = 5,
            contributorId = "user_cleaneats",
            submitted = LocalDate.of(2023, 7, 20),
            tags = listOf("egg-free", "vegan", "high-fiber"),
            nutrition = Nutrition(
                calories = 180.0,
                fat = 10.0,
                sugar = 6.0,
                sodium = 20.0,
                protein = 5.0,
                saturatedFat = 7.0,
                carbohydrates = 15.0
            ),
            numberOfSteps = 2,
            steps = listOf(
                "Whisk chia seeds with coconut milk and vanilla extract.",
                "Refrigerate overnight and top with mango slices."
            ),
            description = "A tropical, egg-free pudding with chia and coconut.",
            ingredients = listOf("chia seeds", "coconut milk", "vanilla extract", "mango"),
            numberOfIngredients = 4
        ),
        Recipe(
            name = "Almond Date Energy Balls",
            id = "EGGFREE_004",
            minutes = 15,
            contributorId = "user_snackmaster",
            submitted = LocalDate.of(2023, 8, 3),
            tags = listOf("egg-free", "no-bake", "paleo"),
            nutrition = Nutrition(
                calories = 120.0,
                fat = 5.0,
                sugar = 10.0,
                sodium = 5.0,
                protein = 3.0,
                saturatedFat = 0.5,
                carbohydrates = 18.0
            ),
            numberOfSteps = 2,
            steps = listOf(
                "Blend dates, almonds, and cocoa powder in a food processor.",
                "Roll into balls and coat with shredded coconut."
            ),
            description = "Naturally sweetened, egg-free energy snacks.",
            ingredients = listOf("dates", "almonds", "cocoa powder", "shredded coconut"),
            numberOfIngredients = 4
        ),
        Recipe(
            name = "Raspberry Sorbet",
            id = "EGGFREE_005",
            minutes = 240, // Includes freezing time
            contributorId = "user_frozenTreats",
            submitted = LocalDate.of(2023, 9, 14),
            tags = listOf("egg-free", "dairy-free", "fruit-based"),
            nutrition = Nutrition(
                calories = 90.0,
                fat = 0.2,
                sugar = 20.0,
                sodium = 2.0,
                protein = 1.0,
                saturatedFat = 0.0,
                carbohydrates = 23.0
            ),
            numberOfSteps = 3,
            steps = listOf(
                "Blend frozen raspberries, lime juice, and agave syrup.",
                "Freeze mixture for 4 hours.",
                "Scrape with a fork to create sorbet texture."
            ),
            description = "A refreshing, egg-free sorbet with fresh raspberries.",
            ingredients = listOf("raspberries", "lime juice", "agave syrup"),
            numberOfIngredients = 3
        )
    )

    //Keto-Friendly Recipes (Pass Filter)
    val passingKetoRecipes = listOf(
        Recipe(
            name = "Garlic Butter Salmon with Avocado Salad",
            id = "KETO_001",
            minutes = 25,
            contributorId = "user_ketochef1",
            submitted = LocalDate.of(2023, 5, 10),
            tags = listOf("keto", "high-fat", "low-carb"),
            nutrition = Nutrition(
                calories = 550.0,
                fat = 45.0,       // ≥40g ✅
                sugar = 3.0,
                sodium = 600.0,
                protein = 35.0,   // ≥30g ✅
                saturatedFat = 8.0, // ≤15g ✅
                carbohydrates = 8.0 // ≤30g ✅
            ),
            numberOfSteps = 4,
            steps = listOf(
                "Season salmon with salt and pepper.",
                "Pan-sear salmon in garlic butter.",
                "Toss avocado, spinach, and olive oil for salad.",
                "Serve salmon over salad."
            ),
            description = "A high-fat, low-carb keto staple.",
            ingredients = listOf("salmon", "avocado", "spinach", "garlic", "butter"),
            numberOfIngredients = 5
        ),
        Recipe(
            name = "Cheesy Bacon Egg Muffins",
            id = "KETO_002",
            minutes = 20,
            contributorId = "user_ketobreakfast",
            submitted = LocalDate.of(2023, 6, 15),
            tags = listOf("keto", "high-protein", "egg-free"),
            nutrition = Nutrition(
                calories = 480.0,
                fat = 42.0,       // ≥40g ✅
                sugar = 2.0,
                sodium = 800.0,
                protein = 32.0,   // ≥30g ✅
                saturatedFat = 12.0, // ≤15g ✅
                carbohydrates = 4.0 // ≤30g ✅
            ),
            numberOfSteps = 3,
            steps = listOf(
                "Preheat oven to 375°F.",
                "Mix bacon, cheese, and cream in a muffin tin.",
                "Bake for 15 minutes."
            ),
            description = "Easy keto breakfast muffins.",
            ingredients = listOf("bacon", "cheddar", "heavy cream", "salt"),
            numberOfIngredients = 4
        ),
        Recipe(
            name = "Keto Beef & Broccoli Stir-Fry",
            id = "KETO_003",
            minutes = 30,
            contributorId = "user_ketoasian",
            submitted = LocalDate.of(2023, 7, 20),
            tags = listOf("keto", "high-protein", "low-carb"),
            nutrition = Nutrition(
                calories = 620.0,
                fat = 50.0,       // ≥40g ✅
                sugar = 5.0,
                sodium = 900.0,
                protein = 38.0,   // ≥30g ✅
                saturatedFat = 14.0, // ≤15g ✅
                carbohydrates = 12.0 // ≤30g ✅
            ),
            numberOfSteps = 5,
            steps = listOf(
                "Slice beef into strips.",
                "Sauté broccoli in sesame oil.",
                "Cook beef with soy sauce.",
                "Combine and simmer.",
                "Garnish with sesame seeds."
            ),
            description = "A keto-friendly twist on takeout.",
            ingredients = listOf("beef", "broccoli", "soy sauce", "sesame oil"),
            numberOfIngredients = 4
        )
    )

    //Non-Keto Recipes (Fail Filter)
    val failingKetoRecipes = listOf(
        Recipe(
            name = "Sweet Potato & Chicken Bowl",
            id = "NONKETO_001",
            minutes = 35,
            contributorId = "user_mealprep",
            submitted = LocalDate.of(2023, 8, 5),
            tags = listOf("high-carb", "balanced"),
            nutrition = Nutrition(
                calories = 400.0,
                fat = 25.0,       // <40g ❌
                sugar = 10.0,
                sodium = 500.0,
                protein = 30.0,  // ✅
                saturatedFat = 5.0, // ✅
                carbohydrates = 45.0 // >30g ❌
            ),
            numberOfSteps = 4,
            steps = listOf(
                "Roast sweet potatoes.",
                "Grill chicken breast.",
                "Assemble bowl with quinoa.",
                "Top with avocado."
            ),
            description = "A carb-heavy post-workout meal.",
            ingredients = listOf("sweet potato", "chicken", "quinoa", "avocado"),
            numberOfIngredients = 4
        ),
        Recipe(
            name = "Grilled Chicken Caesar Salad",
            id = "NONKETO_002",
            minutes = 15,
            contributorId = "user_saladlover",
            submitted = LocalDate.of(2023, 9, 10),
            tags = listOf("low-fat", "high-protein"),
            nutrition = Nutrition(
                calories = 300.0,
                fat = 25.0,       // <40g ❌
                sugar = 4.0,
                sodium = 700.0,
                protein = 35.0,    // ✅
                saturatedFat = 6.0,  // ✅
                carbohydrates = 10.0 // ✅
            ),
            numberOfSteps = 3,
            steps = listOf(
                "Grill chicken.",
                "Toss lettuce with Caesar dressing.",
                "Top with croutons."
            ),
            description = "Classic salad with croutons.",
            ingredients = listOf("chicken", "romaine", "croutons", "Parmesan"),
            numberOfIngredients = 4
        ),
        Recipe(
            name = "Coconut Curry Shrimp",
            id = "NONKETO_003",
            minutes = 25,
            contributorId = "user_thaicuisine",
            submitted = LocalDate.of(2023, 10, 15),
            tags = listOf("high-saturated-fat", "seafood"),
            nutrition = Nutrition(
                calories = 450.0,
                fat = 45.0,       // ✅
                sugar = 8.0,
                sodium = 1000.0,
                protein = 28.0,    // <30g ❌
                saturatedFat = 18.0, // >15g ❌
                carbohydrates = 15.0 // ✅
            ),
            numberOfSteps = 4,
            steps = listOf(
                "Sauté shrimp in coconut oil.",
                "Add curry paste and coconut milk.",
                "Simmer for 10 mins.",
                "Serve over rice."
            ),
            description = "Creamy coconut curry with shrimp.",
            ingredients = listOf("shrimp", "coconut milk", "curry paste", "rice"),
            numberOfIngredients = 4
        )
    )


    val countryRecipes = listOf(
        // Italian Recipes
        Recipe(
            name = "Spaghetti Carbonara",
            id = "ITALY_001",
            minutes = 30,
            contributorId = "user_italianfood",
            submitted = LocalDate.of(2023, 5, 10),
            tags = listOf("Italian", "pasta", "cheesy"),
            nutrition = Nutrition(
                calories = 650.0,
                fat = 35.0,
                sugar = 2.0,
                sodium = 800.0,
                protein = 25.0,
                saturatedFat = 12.0,
                carbohydrates = 50.0
            ),
            numberOfSteps = 5,
            steps = listOf(
                "Boil spaghetti until al dente.",
                "Fry pancetta until crispy.",
                "Whisk eggs, pecorino cheese, and black pepper.",
                "Toss pasta with pancetta and egg mixture.",
                "Serve immediately."
            ),
            description = "A classic Roman pasta dish with eggs and pancetta.",
            ingredients = listOf("spaghetti", "pecorino romano", "eggs", "pancetta", "black pepper"),
            numberOfIngredients = 5
        ),
        Recipe(
            name = "Margherita Pizza",
            id = "ITALY_002",
            minutes = 25,
            contributorId = "user_italianfood",
            submitted = LocalDate.of(2023, 6, 15),
            tags = listOf("Italian", "pizza", "vegetarian"),
            nutrition = Nutrition(
                calories = 800.0,
                fat = 30.0,
                sugar = 5.0,
                sodium = 900.0,
                protein = 20.0,
                saturatedFat = 10.0,
                carbohydrates = 90.0
            ),
            numberOfSteps = 4,
            steps = listOf(
                "Roll out pizza dough.",
                "Spread tomato sauce and fresh mozzarella.",
                "Bake at 475°F for 10 minutes.",
                "Top with fresh basil."
            ),
            description = "Traditional Neapolitan pizza with fresh basil and mozzarella.",
            ingredients = listOf("pizza dough", "tomatoes", "mozzarella", "basil", "olive oil"),
            numberOfIngredients = 5
        ),

        // Japanese Recipes
        Recipe(
            name = "Teriyaki Chicken Bowl",
            id = "JAPAN_001",
            minutes = 35,
            contributorId = "user_japanesefood",
            submitted = LocalDate.of(2023, 7, 20),
            tags = listOf("Japanese", "rice", "teriyaki"),
            nutrition = Nutrition(
                calories = 720.0,
                fat = 15.0,
                sugar = 20.0,
                sodium = 1200.0,
                protein = 45.0,
                saturatedFat = 3.5,
                carbohydrates = 80.0
            ),
            numberOfSteps = 6,
            steps = listOf(
                "Marinate chicken in teriyaki sauce.",
                "Grill chicken until cooked through.",
                "Cook sushi rice in rice cooker.",
                "Slice chicken into strips.",
                "Assemble bowl with rice, chicken, and steamed broccoli.",
                "Drizzle with extra sauce."
            ),
            description = "Grilled chicken glazed with teriyaki sauce, served over sushi rice.",
            ingredients = listOf("chicken breast", "soy sauce", "mirin", "sushi rice", "broccoli"),
            numberOfIngredients = 5
        ),
        Recipe(
            name = "Miso Soup",
            id = "JAPAN_002",
            minutes = 15,
            contributorId = "user_japanesefood",
            submitted = LocalDate.of(2023, 8, 5),
            tags = listOf("Japanese", "soup", "tofu"),
            nutrition = Nutrition(
                calories = 80.0,
                fat = 3.0,
                sugar = 2.0,
                sodium = 900.0,
                protein = 6.0,
                saturatedFat = 0.5,
                carbohydrates = 8.0
            ),
            numberOfSteps = 3,
            steps = listOf(
                "Bring dashi broth to a simmer.",
                "Dissolve miso paste in broth.",
                "Add tofu cubes and seaweed. Serve immediately."
            ),
            description = "A staple soup with miso paste, tofu, and seaweed.",
            ingredients = listOf("miso paste", "tofu", "seaweed", "dashi broth", "green onions"),
            numberOfIngredients = 5
        ),

        // Mexican Recipes
        Recipe(
            name = "Chicken Tacos",
            id = "MEXICO_001",
            minutes = 20,
            contributorId = "user_mexicanfood",
            submitted = LocalDate.of(2023, 9, 10),
            tags = listOf("Mexican", "tacos", "spicy"),
            nutrition = Nutrition(
                calories = 350.0,
                fat = 12.0,
                sugar = 3.0,
                sodium = 600.0,
                protein = 25.0,
                saturatedFat = 3.0,
                carbohydrates = 30.0
            ),
            numberOfSteps = 4,
            steps = listOf(
                "Season chicken with taco spices.",
                "Grill chicken until charred.",
                "Warm corn tortillas.",
                "Assemble with salsa verde and cilantro."
            ),
            description = "Soft corn tortillas with grilled chicken and salsa verde.",
            ingredients = listOf("chicken thighs", "corn tortillas", "salsa verde", "cilantro", "lime"),
            numberOfIngredients = 5
        ),
        Recipe(
            name = "Guacamole",
            id = "MEXICO_002",
            minutes = 10,
            contributorId = "user_mexicanfood",
            submitted = LocalDate.of(2023, 10, 15),
            tags = listOf("Mexican", "dip", "avocado"),
            nutrition = Nutrition(
                calories = 150.0,
                fat = 12.0,
                sugar = 1.0,
                sodium = 200.0,
                protein = 2.0,
                saturatedFat = 2.0,
                carbohydrates = 8.0
            ),
            numberOfSteps = 3,
            steps = listOf(
                "Mash ripe avocados in a bowl.",
                "Mix in diced onion, cilantro, and lime juice.",
                "Season with salt and serve with chips."
            ),
            description = "A creamy dip made with ripe avocados and lime.",
            ingredients = listOf("avocado", "lime", "onion", "cilantro", "salt"),
            numberOfIngredients = 5
        ),

        // Edge Cases
        Recipe(
            name = "French Onion Soup",
            id = "EDGE_001",
            minutes = 60,
            contributorId = "user_frenchfood",
            submitted = LocalDate.of(2023, 11, 1),
            tags = listOf("soup", "onion"),
            nutrition = Nutrition(
                calories = 250.0,
                fat = 10.0,
                sugar = 8.0,
                sodium = 1000.0,
                protein = 12.0,
                saturatedFat = 5.0,
                carbohydrates = 30.0
            ),
            numberOfSteps = 6,
            steps = listOf(
                "Caramelize onions in butter.",
                "Deglaze pan with red wine.",
                "Add beef broth and simmer.",
                "Toast baguette slices.",
                "Ladle soup into bowls, top with bread and Gruyère.",
                "Broil until cheese melts."
            ),
            description = "Caramelized onion soup with toasted bread and melted cheese.",
            ingredients = listOf("onions", "beef broth", "baguette", "Gruyère cheese", "butter"),
            numberOfIngredients = 5
        )
    )


    val potatoRecipes = listOf(
        Recipe(
            name = "Classic Mashed Potatoes",
            id = "POTATO_001",
            minutes = 30,
            contributorId = "user_comfortfood",
            submitted = LocalDate.of(2023, 1, 5),
            tags = listOf("potato", "comfort food", "side dish"),
            nutrition = Nutrition(
                calories = 220.0,
                fat = 8.0,
                sugar = 2.0,
                sodium = 400.0,
                protein = 4.0,
                saturatedFat = 5.0,
                carbohydrates = 35.0
            ),
            numberOfSteps = 4,
            steps = listOf(
                "Peel and boil potatoes until tender.",
                "Drain and mash with butter and milk.",
                "Season with salt and pepper.",
                "Garnish with chives."
            ),
            description = "Creamy mashed potatoes with butter and milk.",
            ingredients = listOf("russet potatoes", "butter", "milk", "salt", "pepper"),
            numberOfIngredients = 5
        ),
        Recipe(
            name = "Crispy Garlic Potato Wedges",
            id = "POTATO_002",
            minutes = 40,
            contributorId = "user_snackmaster",
            submitted = LocalDate.of(2023, 2, 12),
            tags = listOf("potato", "appetizer", "vegan"),
            nutrition = Nutrition(
                calories = 180.0,
                fat = 7.0,
                sugar = 1.0,
                sodium = 300.0,
                protein = 3.0,
                saturatedFat = 1.0,
                carbohydrates = 28.0
            ),
            numberOfSteps = 3,
            steps = listOf(
                "Cut potatoes into wedges.",
                "Toss with olive oil, garlic powder, and paprika.",
                "Bake at 425°F for 35 minutes."
            ),
            description = "Oven-baked potato wedges with garlic seasoning.",
            ingredients = listOf("potatoes", "olive oil", "garlic powder", "paprika", "salt"),
            numberOfIngredients = 5
        ),
        Recipe(
            name = "Indian Aloo Gobi",
            id = "POTATO_003",
            minutes = 45,
            contributorId = "user_indianfood",
            submitted = LocalDate.of(2023, 3, 18),
            tags = listOf("potato", "Indian", "vegetarian"),
            nutrition = Nutrition(
                calories = 250.0,
                fat = 10.0,
                sugar = 5.0,
                sodium = 600.0,
                protein = 6.0,
                saturatedFat = 2.0,
                carbohydrates = 35.0
            ),
            numberOfSteps = 6,
            steps = listOf(
                "Sauté cumin seeds in oil.",
                "Add chopped potatoes and cauliflower.",
                "Mix in turmeric, coriander, and garam masala.",
                "Cook until vegetables are tender.",
                "Garnish with cilantro.",
                "Serve with roti."
            ),
            description = "Spiced potato and cauliflower curry.",
            ingredients = listOf("potatoes", "cauliflower", "cumin", "turmeric", "ginger"),
            numberOfIngredients = 5
        ),
        Recipe(
            name = "Loaded Potato Soup",
            id = "POTATO_004",
            minutes = 50,
            contributorId = "user_souplover",
            submitted = LocalDate.of(2023, 4, 22),
            tags = listOf("potato", "soup", "comfort food"),
            nutrition = Nutrition(
                calories = 350.0,
                fat = 15.0,
                sugar = 8.0,
                sodium = 800.0,
                protein = 12.0,
                saturatedFat = 8.0,
                carbohydrates = 45.0
            ),
            numberOfSteps = 5,
            steps = listOf(
                "Sauté onions and garlic in butter.",
                "Add diced potatoes and chicken broth.",
                "Simmer until potatoes are soft.",
                "Blend half the soup for creaminess.",
                "Top with bacon bits and cheddar."
            ),
            description = "Creamy potato soup with bacon and cheese toppings.",
            ingredients = listOf("potatoes", "onion", "garlic", "chicken broth", "bacon"),
            numberOfIngredients = 5
        ),
        Recipe(
            name = "Spanish Potato Tortilla",
            id = "POTATO_005",
            minutes = 40,
            contributorId = "user_spanishfood",
            submitted = LocalDate.of(2023, 5, 30),
            tags = listOf("potato", "Spanish", "omelette"),
            nutrition = Nutrition(
                calories = 280.0,
                fat = 12.0,
                sugar = 3.0,
                sodium = 400.0,
                protein = 15.0,
                saturatedFat = 3.0,
                carbohydrates = 30.0
            ),
            numberOfSteps = 4,
            steps = listOf(
                "Slice potatoes thinly and fry in olive oil.",
                "Whisk eggs with salt.",
                "Layer potatoes in pan and pour eggs over.",
                "Cook until set and flip to brown both sides."
            ),
            description = "Traditional Spanish potato omelette.",
            ingredients = listOf("potatoes", "eggs", "onion", "olive oil", "salt"),
            numberOfIngredients = 5
        ),
        Recipe(
            name = "Sweet Potato Fries",
            id = "POTATO_006",
            minutes = 35,
            contributorId = "user_healthysnacks",
            submitted = LocalDate.of(2023, 6, 7),
            tags = listOf("sweet potato", "baked", "appetizer"),
            nutrition = Nutrition(
                calories = 200.0,
                fat = 6.0,
                sugar = 8.0,
                sodium = 250.0,
                protein = 2.0,
                saturatedFat = 1.0,
                carbohydrates = 35.0
            ),
            numberOfSteps = 3,
            steps = listOf(
                "Cut sweet potatoes into fries.",
                "Toss with olive oil and smoked paprika.",
                "Bake at 400°F for 30 minutes."
            ),
            description = "Oven-baked sweet potato fries with smoky flavor.",
            ingredients = listOf("sweet potatoes", "olive oil", "smoked paprika", "salt"),
            numberOfIngredients = 4
        ),
        Recipe(
            name = "Potato Gnocchi",
            id = "POTATO_007",
            minutes = 60,
            contributorId = "user_italianfood",
            submitted = LocalDate.of(2023, 7, 14),
            tags = listOf("potato", "Italian", "pasta"),
            nutrition = Nutrition(
                calories = 300.0,
                fat = 2.0,
                sugar = 1.0,
                sodium = 300.0,
                protein = 8.0,
                saturatedFat = 0.5,
                carbohydrates = 60.0
            ),
            numberOfSteps = 6,
            steps = listOf(
                "Boil and mash potatoes.",
                "Mix with flour and egg to form dough.",
                "Roll into ropes and cut into pieces.",
                "Press with fork to create ridges.",
                "Boil until they float to the surface.",
                "Serve with marinara sauce."
            ),
            description = "Homemade potato dumplings with marinara.",
            ingredients = listOf("potatoes", "flour", "egg", "salt", "marinara sauce"),
            numberOfIngredients = 5
        ),
        Recipe(
            name = "German Potato Salad",
            id = "POTATO_008",
            minutes = 25,
            contributorId = "user_germanfood",
            submitted = LocalDate.of(2023, 8, 19),
            tags = listOf("potato", "German", "salad"),
            nutrition = Nutrition(
                calories = 180.0,
                fat = 10.0,
                sugar = 5.0,
                sodium = 500.0,
                protein = 3.0,
                saturatedFat = 2.0,
                carbohydrates = 20.0
            ),
            numberOfSteps = 4,
            steps = listOf(
                "Boil baby potatoes until tender.",
                "Sauté bacon and onions.",
                "Mix potatoes with bacon, onions, and vinegar dressing.",
                "Garnish with parsley."
            ),
            description = "Warm potato salad with bacon and vinegar.",
            ingredients = listOf("baby potatoes", "bacon", "onion", "apple cider vinegar", "parsley"),
            numberOfIngredients = 5
        ),
        Recipe(
            name = "Cheesy Potato Casserole",
            id = "POTATO_009",
            minutes = 55,
            contributorId = "user_comfortfood",
            submitted = LocalDate.of(2023, 9, 25),
            tags = listOf("potato", "casserole", "cheesy"),
            nutrition = Nutrition(
                calories = 400.0,
                fat = 20.0,
                sugar = 3.0,
                sodium = 700.0,
                protein = 15.0,
                saturatedFat = 10.0,
                carbohydrates = 45.0
            ),
            numberOfSteps = 5,
            steps = listOf(
                "Slice potatoes thinly.",
                "Layer with cheddar cheese and cream.",
                "Repeat layers in baking dish.",
                "Bake at 375°F for 45 minutes.",
                "Top with chives before serving."
            ),
            description = "Decadent potato casserole with melted cheese.",
            ingredients = listOf("potatoes", "cheddar", "heavy cream", "garlic", "salt"),
            numberOfIngredients = 5
        ),
        Recipe(
            name = "Potato and Chorizo Tacos",
            id = "POTATO_010",
            minutes = 30,
            contributorId = "user_mexicanfood",
            submitted = LocalDate.of(2023, 10, 31),
            tags = listOf("potato", "Mexican", "tacos"),
            nutrition = Nutrition(
                calories = 320.0,
                fat = 15.0,
                sugar = 2.0,
                sodium = 600.0,
                protein = 12.0,
                saturatedFat = 5.0,
                carbohydrates = 35.0
            ),
            numberOfSteps = 4,
            steps = listOf(
                "Dice and pan-fry potatoes until crispy.",
                "Cook chorizo in separate pan.",
                "Combine potatoes and chorizo.",
                "Serve in warm tortillas with salsa."
            ),
            description = "Spicy potato and chorizo street tacos.",
            ingredients = listOf("potatoes", "chorizo", "corn tortillas", "salsa", "cilantro"),
            numberOfIngredients = 5
        )
    )

    //High-Calorie Recipes (Pass Filter)
    val highCalorieRecipes = listOf(
        Recipe(
            name = "Double Bacon Cheeseburger with Fries",
            id = "HIGH_001",
            minutes = 25,
            contributorId = "user_comfortfood",
            submitted = LocalDate.of(2023, 5, 15),
            tags = listOf("high-calorie", "comfort-food", "fast-food"),
            nutrition = Nutrition(
                calories = 950.0,  // >700 ✅
                fat = 55.0,
                sugar = 10.0,
                sodium = 1200.0,
                protein = 45.0,
                saturatedFat = 20.0,
                carbohydrates = 65.0
            ),
            numberOfSteps = 5,
            steps = listOf(
                "Grill beef patties with salt and pepper.",
                "Layer bacon strips and cheddar cheese on patties.",
                "Toast burger buns.",
                "Assemble with lettuce, tomato, and mayo.",
                "Serve with crispy french fries."
            ),
            description = "A decadent burger with crispy bacon and melted cheese.",
            ingredients = listOf("beef patty", "bacon", "cheddar", "burger bun", "fries"),
            numberOfIngredients = 5
        ),
        Recipe(
            name = "Creamy Lobster Mac & Cheese",
            id = "HIGH_002",
            minutes = 40,
            contributorId = "user_gourmet",
            submitted = LocalDate.of(2023, 6, 20),
            tags = listOf("high-calorie", "seafood", "pasta"),
            nutrition = Nutrition(
                calories = 850.0,  // >700 ✅
                fat = 50.0,
                sugar = 5.0,
                sodium = 1100.0,
                protein = 35.0,
                saturatedFat = 25.0,
                carbohydrates = 60.0
            ),
            numberOfSteps = 6,
            steps = listOf(
                "Cook pasta until al dente.",
                "Sauté lobster meat in butter.",
                "Make cheese sauce with heavy cream and Gruyère.",
                "Combine pasta, lobster, and sauce.",
                "Top with breadcrumbs and bake.",
                "Broil until golden."
            ),
            description = "Luxurious macaroni with lobster and three cheeses.",
            ingredients = listOf("lobster", "pasta", "Gruyère", "heavy cream", "breadcrumbs"),
            numberOfIngredients = 5
        ),
        Recipe(
            name = "Deep-Fried Chicken Basket",
            id = "HIGH_003",
            minutes = 30,
            contributorId = "user_friedfood",
            submitted = LocalDate.of(2023, 7, 25),
            tags = listOf("high-calorie", "fried", "comfort-food"),
            nutrition = Nutrition(
                calories = 800.0,  // >700 ✅
                fat = 45.0,
                sugar = 2.0,
                sodium = 900.0,
                protein = 30.0,
                saturatedFat = 15.0,
                carbohydrates = 55.0
            ),
            numberOfSteps = 4,
            steps = listOf(
                "Marinate chicken in buttermilk.",
                "Coat with seasoned flour.",
                "Deep-fry until golden brown.",
                "Serve with fries and coleslaw."
            ),
            description = "Crispy fried chicken with a side of fries.",
            ingredients = listOf("chicken thighs", "buttermilk", "flour", "potatoes", "oil"),
            numberOfIngredients = 5
        )
    )

    //Non-High-Calorie Recipes (Fail Filter)
    val lowCalorieRecipes = listOf(
        Recipe(
            name = "Grilled Chicken Salad",
            id = "LOW_001",
            minutes = 20,
            contributorId = "user_healthyeats",
            submitted = LocalDate.of(2023, 8, 5),
            tags = listOf("low-calorie", "salad", "high-protein"),
            nutrition = Nutrition(
                calories = 350.0,  // <700 ❌
                fat = 15.0,
                sugar = 5.0,
                sodium = 400.0,
                protein = 30.0,
                saturatedFat = 3.0,
                carbohydrates = 20.0
            ),
            numberOfSteps = 3,
            steps = listOf(
                "Grill chicken breast.",
                "Toss mixed greens with vinaigrette.",
                "Top with chicken and cherry tomatoes."
            ),
            description = "A light, protein-packed salad.",
            ingredients = listOf("chicken breast", "lettuce", "tomatoes", "vinaigrette"),
            numberOfIngredients = 4
        ),
        Recipe(
            name = "Vegetable Stir-Fry",
            id = "LOW_002",
            minutes = 25,
            contributorId = "user_vegan",
            submitted = LocalDate.of(2023, 9, 10),
            tags = listOf("low-calorie", "vegan", "quick"),
            nutrition = Nutrition(
                calories = 280.0,  // <700 ❌
                fat = 10.0,
                sugar = 8.0,
                sodium = 600.0,
                protein = 8.0,
                saturatedFat = 1.5,
                carbohydrates = 35.0
            ),
            numberOfSteps = 4,
            steps = listOf(
                "Slice bell peppers, carrots, and broccoli.",
                "Stir-fry in sesame oil.",
                "Add soy sauce and garlic.",
                "Serve over rice."
            ),
            description = "A colorful mix of sautéed veggies.",
            ingredients = listOf("bell peppers", "carrots", "broccoli", "soy sauce"),
            numberOfIngredients = 4
        )
    )


    /**
     * // Output:
     * // 1. Grilled Salmon Steak - 38g protein
     * // 2. Shrimp Scampi Pasta - 35g
     * // 3. Tuna Poke Bowl - 30g
     * // 4. Lobster Bisque - 28g
     * // 5. Crab Cakes - 25g
     */
    val seafoodRecipes = listOf(
        Recipe(
            name = "Grilled Salmon Steak",
            id = "SEAFOOD_001",
            minutes = 25,
            contributorId = "user_seafoodlover",
            submitted = LocalDate.of(2023, 9, 1),
            tags = listOf("seafood", "high-protein", "keto"),
            nutrition = Nutrition(
                calories = 400.0,
                fat = 22.0,
                sugar = 2.0,
                sodium = 500.0,
                protein = 38.0,  // Highest protein
                saturatedFat = 5.0,
                carbohydrates = 3.0
            ),
            numberOfSteps = 3,
            steps = listOf(
                "Season salmon with lemon, salt, and pepper.",
                "Grill for 6 minutes per side.",
                "Serve with asparagus."
            ),
            description = "Juicy salmon steak with a charred finish.",
            ingredients = listOf("salmon", "lemon", "olive oil", "asparagus"),
            numberOfIngredients = 4
        ),
        Recipe(
            name = "Shrimp Scampi Pasta",
            id = "SEAFOOD_002",
            minutes = 20,
            contributorId = "user_italianseafood",
            submitted = LocalDate.of(2023, 9, 5),
            tags = listOf("seafood", "pasta", "garlic"),
            nutrition = Nutrition(
                calories = 650.0,
                fat = 30.0,
                sugar = 4.0,
                sodium = 800.0,
                protein = 35.0,
                saturatedFat = 10.0,
                carbohydrates = 55.0
            ),
            numberOfSteps = 4,
            steps = listOf(
                "Sauté shrimp in garlic butter.",
                "Deglaze pan with white wine.",
                "Toss with cooked linguine.",
                "Garnish with parsley."
            ),
            description = "Garlicky shrimp with al dente pasta.",
            ingredients = listOf("shrimp", "linguine", "garlic", "white wine"),
            numberOfIngredients = 4
        ),
        Recipe(
            name = "Tuna Poke Bowl",
            id = "SEAFOOD_003",
            minutes = 15,
            contributorId = "user_hawaiianfood",
            submitted = LocalDate.of(2023, 9, 10),
            tags = listOf("seafood", "raw", "bowl"),
            nutrition = Nutrition(
                calories = 450.0,
                fat = 15.0,
                sugar = 8.0,
                sodium = 600.0,
                protein = 30.0,
                saturatedFat = 3.0,
                carbohydrates = 40.0
            ),
            numberOfSteps = 3,
            steps = listOf(
                "Cube fresh tuna and marinate in soy sauce.",
                "Layer sushi rice, tuna, avocado, and edamame.",
                "Top with sesame seeds."
            ),
            description = "Hawaiian-style raw tuna bowl.",
            ingredients = listOf("tuna", "sushi rice", "avocado", "edamame"),
            numberOfIngredients = 4
        ),
        Recipe(
            name = "Lobster Bisque",
            id = "SEAFOOD_004",
            minutes = 45,
            contributorId = "user_frenchchef",
            submitted = LocalDate.of(2023, 9, 15),
            tags = listOf("seafood", "soup", "luxury"),
            nutrition = Nutrition(
                calories = 320.0,
                fat = 18.0,
                sugar = 5.0,
                sodium = 900.0,
                protein = 28.0,
                saturatedFat = 8.0,
                carbohydrates = 12.0
            ),
            numberOfSteps = 6,
            steps = listOf(
                "Sauté lobster shells in butter.",
                "Add tomato paste and brandy.",
                "Simmer with fish stock and cream.",
                "Blend and strain the soup.",
                "Garnish with lobster meat.",
                "Serve with crusty bread."
            ),
            description = "Creamy French lobster soup.",
            ingredients = listOf("lobster", "fish stock", "heavy cream", "brandy"),
            numberOfIngredients = 4
        ),
        Recipe(
            name = "Crab Cakes",
            id = "SEAFOOD_005",
            minutes = 30,
            contributorId = "user_maryland",
            submitted = LocalDate.of(2023, 9, 20),
            tags = listOf("seafood", "appetizer", "pan-fried"),
            nutrition = Nutrition(
                calories = 280.0,
                fat = 12.0,
                sugar = 2.0,
                sodium = 700.0,
                protein = 25.0,
                saturatedFat = 2.5,
                carbohydrates = 15.0
            ),
            numberOfSteps = 4,
            steps = listOf(
                "Mix crab meat with breadcrumbs and egg.",
                "Form into patties.",
                "Pan-fry until golden brown.",
                "Serve with remoulade sauce."
            ),
            description = "Crispy Maryland-style crab cakes.",
            ingredients = listOf("crab meat", "breadcrumbs", "egg", "mayonnaise"),
            numberOfIngredients = 4
        )
    )


    //Dummy Italian Group Recipes
    val italianGroupRecipes = listOf(
        Recipe(
            name = "Classic Lasagna",
            id = "ITALY_GROUP_001",
            minutes = 90,
            contributorId = "user_italiangrandma",
            submitted = LocalDate.of(2023, 5, 1),
            tags = listOf("Italian", "for-large-groups", "oven-baked"),
            nutrition = Nutrition(
                calories = 350.0,
                fat = 15.0,
                sugar = 8.0,
                sodium = 800.0,
                protein = 20.0,
                saturatedFat = 6.0,
                carbohydrates = 35.0
            ),
            numberOfSteps = 6,
            steps = listOf(
                "Layer lasagna sheets with béchamel and meat sauce.",
                "Add mozzarella and Parmesan cheese.",
                "Repeat layers.",
                "Bake at 375°F for 45 mins.",
                "Let rest for 15 mins.",
                "Slice into 12 portions."
            ),
            description = "A crowd-pleasing lasagna that serves 12 people.",
            ingredients = listOf("lasagna sheets", "ground beef", "tomato sauce", "mozzarella"),
            numberOfIngredients = 4
        ),
        Recipe(
            name = "Baked Ziti al Forno",
            id = "ITALY_GROUP_002",
            minutes = 60,
            contributorId = "user_italianchef",
            submitted = LocalDate.of(2023, 6, 10),
            tags = listOf("Italian", "for-large-groups", "pasta"),
            nutrition = Nutrition(
                calories = 320.0,
                fat = 12.0,
                sugar = 6.0,
                sodium = 700.0,
                protein = 18.0,
                saturatedFat = 5.0,
                carbohydrates = 40.0
            ),
            numberOfSteps = 4,
            steps = listOf(
                "Boil ziti pasta until al dente.",
                "Mix with marinara sauce and ricotta.",
                "Top with mozzarella and bake.",
                "Serves 10-12 people."
            ),
            description = "Baked pasta dish perfect for gatherings.",
            ingredients = listOf("ziti", "ricotta", "marinara", "mozzarella"),
            numberOfIngredients = 4
        ),
        Recipe(
            name = "Risotto alla Milanese (Family Style)",
            id = "ITALY_GROUP_003",
            minutes = 50,
            contributorId = "user_milanfood",
            submitted = LocalDate.of(2023, 7, 15),
            tags = listOf("Italian", "for-large-groups", "rice"),
            nutrition = Nutrition(
                calories = 280.0,
                fat = 10.0,
                sugar = 3.0,
                sodium = 600.0,
                protein = 8.0,
                saturatedFat = 4.0,
                carbohydrates = 38.0
            ),
            numberOfSteps = 5,
            steps = listOf(
                "Sauté onions and Arborio rice in butter.",
                "Deglaze with white wine.",
                "Slowly add saffron-infused broth.",
                "Stir in Parmesan cheese.",
                "Serves 8-10 people."
            ),
            description = "Creamy saffron risotto scaled for groups.",
            ingredients = listOf("Arborio rice", "saffron", "Parmesan", "white wine"),
            numberOfIngredients = 4
        ),
        Recipe(
            name = "Focaccia Bread Platter",
            id = "ITALY_GROUP_004",
            minutes = 40,
            contributorId = "user_baker2023",
            submitted = LocalDate.of(2023, 8, 20),
            tags = listOf("Italian", "for-large-groups", "bread"),
            nutrition = Nutrition(
                calories = 200.0,
                fat = 8.0,
                sugar = 2.0,
                sodium = 400.0,
                protein = 5.0,
                saturatedFat = 1.0,
                carbohydrates = 28.0
            ),
            numberOfSteps = 3,
            steps = listOf(
                "Proof yeast and mix dough.",
                "Top with rosemary and sea salt.",
                "Bake in large sheet pan (serves 15+)."
            ),
            description = "Giant focaccia for sharing, serves 15+ people.",
            ingredients = listOf("flour", "yeast", "olive oil", "rosemary"),
            numberOfIngredients = 4
        ),
        Recipe(
            name = "Tiramisu Tray",
            id = "ITALY_GROUP_005",
            minutes = 120,
            contributorId = "user_dessertlover",
            submitted = LocalDate.of(2023, 9, 5),
            tags = listOf("Italian", "for-large-groups", "dessert"),
            nutrition = Nutrition(
                calories = 300.0,
                fat = 15.0,
                sugar = 25.0,
                sodium = 150.0,
                protein = 6.0,
                saturatedFat = 8.0,
                carbohydrates = 35.0
            ),
            numberOfSteps = 4,
            steps = listOf(
                "Layer ladyfingers dipped in coffee.",
                "Spread mascarpone mixture.",
                "Repeat layers in a large tray.",
                "Chill overnight (serves 20)."
            ),
            description = "Classic tiramisu in a shareable tray.",
            ingredients = listOf("ladyfingers", "mascarpone", "coffee", "cocoa"),
            numberOfIngredients = 4
        )
    )


    val allRecipes = healthyFastFoodRecipes + easyRecipes +
                eggFreeSweets + passingKetoRecipes +
                failingKetoRecipes + countryRecipes +
                potatoRecipes + highCalorieRecipes +
                lowCalorieRecipes + seafoodRecipes + italianGroupRecipes


}