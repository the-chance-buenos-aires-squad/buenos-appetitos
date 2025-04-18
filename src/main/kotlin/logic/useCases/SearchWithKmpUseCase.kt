package org.example.logic.useCases

import org.example.logic.RecipesRepository
import org.example.model.Recipe

class SearchWithKmpUseCase(val repository: RecipesRepository){
    private val allRecipes = repository.getRecipes()

    fun searchRecipes(
        recipes: List<Recipe> = allRecipes,
        query: String,
        useFuzzy: Boolean = false,
        maxTypos: Int = 3
    ): List<Recipe> {
        return recipes.filter { recipe ->
            // Exact match first
            kmp(recipe.name.lowercase(), query.lowercase()) != -1 ||
                    (useFuzzy && isFuzzyMatch(recipe.name.lowercase(), query.lowercase(), maxTypos))
        }
    }


    private fun isFuzzyMatch(text: String, query: String, maxDistance: Int = 2): Boolean {
        if (query.isEmpty()) return false

        // Check for direct substring match first (optimization)
        if (query.length <= text.length && kmp(text, query) != -1) {
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

    fun kmp(text: String, pattern: String): Int {
        val lps = computeLPSArray(pattern)
        var i = 0
        var j = 0
        while (i < text.length) {
            if (pattern[j] == text[i]) {
                j++
                i++
            }
            if (j == pattern.length) {
                return i - j
            } else if (i < text.length && pattern[j] != text[i]) {
                when( j == 0){
                    true -> i++
                    false ->  j = lps[j - 1]
                }
            }
        }
        return -1
    }

    private fun computeLPSArray(pattern: String): IntArray {
        val lps = IntArray(pattern.length)
        var len = 0
        var i = 1
        lps[0] = 0
        while (i < pattern.length) {
            if (pattern[i] == pattern[len]) {
                len++
                lps[i] = len
                i++
            } else {
                when(len == 0){
                    true -> {
                        lps[i] = len
                        i++
                    }
                    false -> len = lps[len - 1]
                }
            }
        }
        return lps
    }



}


