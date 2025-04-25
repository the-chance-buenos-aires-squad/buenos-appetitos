package logic.useCases

class FuzzySearchUseCase {

    fun isFuzzyMatch(text: String, query: String, maxDistance: Int = 2): Boolean {
        // Early exit conditions
        if (query.isEmpty()) return false
        if (text.isEmpty()) return query.length <= maxDistance

        // Initialize DP matrix with explicit type declaration
        val dp = Array(query.length + 1) { i ->
            IntArray(text.length + 1).apply {
                this[0] = i  // Initialize first column
            }
        }

        // Initialize first row
        for (j in text.indices) {
            dp[0][j] = j
        }

        // Matrix population with explicit bounds checking
        for (i in 1 until dp.size) {
            for (j in 1 until dp[0].size) {
                val matchCost = if (query[i-1] == text[j-1]) 0 else 1
                dp[i][j] = minOf(
                    dp[i-1][j-1] + matchCost,
                    dp[i-1][j] + 1,
                    dp[i][j-1] + 1
                )
            }
        }

        return dp.last().last() <= maxDistance
    }
}