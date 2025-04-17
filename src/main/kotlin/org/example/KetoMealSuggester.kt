package org.example

import org.example.model.Recipe

class KetoMealSuggester(private val meals: List<Recipe>) {
    private var currentIndex = 0

    fun suggestNext(): Recipe? {
        return if (currentIndex < meals.size) meals[currentIndex++] else null
    }
}

