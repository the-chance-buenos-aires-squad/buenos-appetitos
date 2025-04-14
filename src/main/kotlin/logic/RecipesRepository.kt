package org.example.logic

import org.example.model.Recipe

interface RecipesRepository {
    fun getRecipes():List<Recipe>
}