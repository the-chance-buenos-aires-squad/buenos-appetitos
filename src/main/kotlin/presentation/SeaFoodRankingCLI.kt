package org.example.presentation

import org.example.logic.useCases.GetSeaFoodRankingByProteinUseCase

class SeaFoodRankingCLI(private val getSeaFoodRankingByProteinUseCase: GetSeaFoodRankingByProteinUseCase) {

    fun start() {
        println("----------  SeaFood Ranking  ------------")
        getSeaFoodRankingByProteinUseCase.getSeaFoodRanking().forEach {
            println("Rank: ${it.rank} | Name: ${it.name} | Protein: ${it.amountOfProtein}")
        }
    }
}