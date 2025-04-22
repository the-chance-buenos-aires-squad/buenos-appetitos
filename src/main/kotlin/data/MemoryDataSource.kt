package org.example.data

import org.example.model.Recipe

class MemoryDataSource {
    private var recipes:List<Recipe> = emptyList()

    fun getCachedRecipes() = recipes

    fun cacheRecipes(parsedRecipes: List<Recipe>){
        recipes = parsedRecipes
    }
}