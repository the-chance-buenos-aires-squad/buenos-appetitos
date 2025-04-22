package org.example.data

import org.example.model.Recipe

class MemoryDataSource {
    private var recipes:List<Recipe> = emptyList()

    fun recipeList() = recipes

    fun setRecipesList(parsedRecipes: List<Recipe>){
        recipes = parsedRecipes
    }
}