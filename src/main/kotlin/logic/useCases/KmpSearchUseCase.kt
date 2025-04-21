package org.example.logic.useCases

class KmpSearchUseCase {

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
                when (j == 0) {
                    true -> i++
                    false -> j = lps[j - 1]
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
                when (len == 0) {
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