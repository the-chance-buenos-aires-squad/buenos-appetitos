package org.example.logic.useCases

import org.example.logic.RecipesRepository

class GetHealthyFastFoodMealsUseCase(
    private val recipesRepository:RecipesRepository
) {
    fun getHealthyFastFood(count:Int):List<String>{
        val allRecipes=recipesRepository.getRecipes()
       val recipesPrepearedInFiftyMinutesOrLess= allRecipes
           .filter {
               it.minutes<=15&&
               it.nutrition[1]<=17&&
               it.nutrition[5]<=5&&
               it.nutrition[6]<=35&&
               it.nutrition[1] > 0&&
               it.nutrition[5] > 0&&
               it.nutrition[6] > 0
           }
           .sortedBy {
               (it.nutrition[1]*0.2+it.nutrition[5]*0.6+it.nutrition[6]*0.1)/3
           }
           .take(count)
           .map { "${it.name} total fat ${it.nutrition[1]} saturated fat ${it.nutrition[5]} Carbohydrates ${it.nutrition[6]}" }
        return recipesPrepearedInFiftyMinutesOrLess
    }
}