package org.example.logic.useCases

class FuzzySearchUseCase(private val kmpSearchUseCase: KmpSearchUseCase) {

     fun isFuzzyMatch(text: String, query: String, maxDistance: Int = 2): Boolean {
        if (query.isEmpty()) return false

        // Check for direct substring match first (optimization)
        if (query.length <= text.length && kmpSearchUseCase.kmp(text, query) != -1) {
            return true
        }

        // Calculate Levenshtein distance
        val dp = Array(query.length + 1) { IntArray(text.length + 1) }

        for (i in 0..query.length) dp[i][0] = i
        for (j in 0..text.length) dp[0][j] = j

        for (i in 1..query.length) {
            for (j in 1..text.length) {
                dp[i][j] = minOf(
                    dp[i-1][j-1] + if (query[i-1] == text[j-1]) 0 else 1,
                    dp[i-1][j] + 1,
                    dp[i][j-1] + 1
                )
            }
        }

        return dp[query.length][text.length] <= maxDistance
    }

}