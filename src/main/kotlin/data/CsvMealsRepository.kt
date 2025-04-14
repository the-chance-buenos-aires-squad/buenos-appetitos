package org.example.data

import org.example.logic.MealsRepository
import org.example.model.MainMeal

class CsvMealsRepository(val csvFileReader: CsvFileReader):MealsRepository {
    override fun getMeals(): List<MainMeal> {
        TODO("Not yet implemented")
    }

}