package org.example.logic

import org.example.model.MainMeal

interface MealsRepository {
    fun getMeals():List<MainMeal>
}