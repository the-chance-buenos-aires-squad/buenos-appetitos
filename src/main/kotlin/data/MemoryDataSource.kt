package org.example.data

import org.example.model.Recipe

class MemoryDataSource {
    private var recipes:List<Recipe> = emptyList()

    fun getRecipes() = recipes

    fun setRecipesList(list: List<Recipe>){
        recipes = list
    }
}