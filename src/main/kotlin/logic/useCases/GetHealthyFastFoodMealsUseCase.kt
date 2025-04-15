package org.example.logic.useCases

import org.example.logic.RecipesRepository

class GetHealthyFastFoodMealsUseCase(
    private val recipesRepository:RecipesRepository
) {
    fun getHealthyFastFood(count:Int):List<String>{
        val allRecipes=recipesRepository.getRecipes()
       val recipesPrepearedInFiftyMinutesOrLess= allRecipes
           .filter {
               it.minutes<=15
               (it.nutrition[1]+it.nutrition[5]+it.nutrition[6])/3!=0.0
           }
           .sortedBy {
               (it.nutrition[1]+it.nutrition[5]+it.nutrition[6])/3
           }
           .take(count)
           .map { it.name }
        return recipesPrepearedInFiftyMinutesOrLess
    }
}