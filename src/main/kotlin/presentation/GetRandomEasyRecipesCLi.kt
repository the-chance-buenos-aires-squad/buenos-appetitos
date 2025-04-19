package org.example.presentation

import org.example.logic.useCases.GetRandomEasyRecipesUseCase
import org.example.model.Recipe

class GetRandomEasyRecipesCLi(val getRandomEasyRecipesUseCase: GetRandomEasyRecipesUseCase) {

    fun suggestRandomRecipes(){
        displayEasyRecipesHeadMessage()
        val easyRecipesList: List<Recipe> = getRandomEasyRecipesUseCase.suggestEasyRecipes()
        println(easyRecipesList)
        TODO("use extension display function from Amr4X4")
    }


    private fun displayEasyRecipesHeadMessage(){
        println("Here Are Some easy recipes" +
                " \ncan be prepared with less then 30 minutes" +
                " \nand got only 5 ingredient " +
                "\nrequired just 5 steps or less to finish")
        println("----------------------------------------------")
    }
}

