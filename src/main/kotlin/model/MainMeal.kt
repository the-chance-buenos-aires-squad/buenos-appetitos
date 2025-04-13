package org.example.model

import java.util.*

data class MainMeal (
    val name:String,
    val id:Int,
    val minutes:Int,
    val contributorId:Int,
    val submitted: Date,
    val tags:List<String>,
    val nutrition:List<Double>,
    val numberOfSteps:Int,
    val steps:List<String>,
    val description:String?,
    val ingredients:List<String>,
    val numberOfIngredients:Int
    )