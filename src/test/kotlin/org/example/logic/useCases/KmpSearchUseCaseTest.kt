package org.example.logic.useCases

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import kotlin.test.Test

class KmpSearchUseCaseTest{
    private val kmpUS = KmpSearchUseCase()


    @Test
    fun `should find exact match in text`() {
        val text = "ABABDABACDABABCABAB"
        val pattern = "ABABCABAB"
        assertThat(kmpUS.kmp(text, pattern)).isEqualTo(10) // Pattern starts at index 10
    }

    @Test
    fun `should return -1 when no match exists`() {
        val text = "ABCDEFGH"
        val pattern = "XYZ"
        assertThat(kmpUS.kmp(text, pattern)).isEqualTo(-1)
    }

    @Test
    fun `should match at start of text`() {
        val text = "ABCABCDAB"
        val pattern = "ABC"
        assertThat(kmpUS.kmp(text, pattern)).isEqualTo(0)
    }

    @Test
    fun `should match at end of text`() {
        val text = "ABCDEFABC"
        val pattern = "ABC"
        assertThat(kmpUS.kmp(text, pattern)).isEqualTo(0)
    }

    @Test
    fun `empty pattern should return -1`() {
        val text = "ANY_TEXT"
        assertThat(kmpUS.kmp(text, " ")).isEqualTo(-1) // By definition
    }

    @Test
    fun `should return -1 when end of text reached during partial match`() {
        val text = "ABAB"
        val pattern = "ABABC" // Longer than text
        assertThat(kmpUS.kmp(text, pattern)).isEqualTo(-1)
    }
}