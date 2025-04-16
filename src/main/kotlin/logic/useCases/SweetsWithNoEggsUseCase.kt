package org.example.logic.useCases

import org.example.logic.RecipesRepository
import org.example.model.Recipe

class SweetsWithNoEggsUseCase(private val repository: RecipesRepository) {

    private val suggestedSweetsId = mutableSetOf<String>()

    fun getRandomSweetsNoEggs(): Recipe {

        val allMeals = repository.getRecipes()

        if (allMeals.isEmpty()){
            throw Exception("No meals found")
        }

        val sweetNoEggs = allMeals
            .filter { it.tags.any{tag -> tag.contains("dessert",ignoreCase = true) } }
            .filter { !it.ingredients.any {ingredient -> ingredient.contains("egg",ignoreCase = true) } }
            .filter { it.id !in suggestedSweetsId}

        println(sweetNoEggs.size)


        if (sweetNoEggs.isEmpty()){
            throw Exception("No suitable dessert without eggs found.")
        }

        val randomSweets = sweetNoEggs.random()
        suggestedSweetsId.add(randomSweets.id)

        return randomSweets
    }
}