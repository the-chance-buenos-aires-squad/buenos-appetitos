package org.example.presentation

import org.example.logic.useCases.GetSeaFoodRankingByProteinUseCase

class SeaFoodRankingCLI(private val getSeaFoodRankingByProteinUseCase: GetSeaFoodRankingByProteinUseCase) {

    fun start() {
        println("----------  SeaFood Ranking  ------------")
        var rank = 1
        getSeaFoodRankingByProteinUseCase.getSeaFoodRanking().forEach {
            println("Rank: $rank | Name: ${it.name} | Protein: ${it.nutrition.protein}")
            rank++
        }
    }
}