package org.example.logic.useCases

import com.google.common.truth.Truth.assertThat
import kotlin.test.Test

class KmpSearchUseCaseTest {
    private val kmpSearchUseCase = KmpSearchUseCase()


    @Test
    fun `should find exact match in text`() {
        //given
        val text = "ABABDABACDABABCABAB"
        val pattern = "ABABCABAB"

        //when
        val result = kmpSearchUseCase.kmp(text, pattern)

        //then
        assertThat(result).isEqualTo(10) // Pattern starts at index 10
    }

    @Test
    fun `should return -1 when no match exists`() {
        //given
        val text = "ABCDEFGH"
        val pattern = "XYZ"

        //when
        val result = kmpSearchUseCase.kmp(text, pattern)

        //then
        assertThat(result).isEqualTo(-1)
    }

    @Test
    fun `should match at start of text`() {
        //given
        val text = "ABCABCDAB"
        val pattern = "ABC"

        //when
        val result = kmpSearchUseCase.kmp(text, pattern)
        //then
        assertThat(result).isEqualTo(0)
    }

    @Test
    fun `should match at end of text`() {
        //given
        val text = "ABCDEFABC"
        val pattern = "ABC"

        //when
        val result = kmpSearchUseCase.kmp(text, pattern)

        //then
        assertThat(result).isEqualTo(0)
    }

    @Test
    fun `empty pattern should return -1`() {
        //given
        val text = "ANY_TEXT"

        //when
        val result = kmpSearchUseCase.kmp(text, " ")

        //then
        assertThat(result).isEqualTo(-1) // By definition
    }

    @Test
    fun `should return -1 when end of text reached during partial match`() {
        //given
        val text = "ABAB"
        val pattern = "ABABC" // Longer than text

        //when
        val result = kmpSearchUseCase.kmp(text, pattern)

        //then
        assertThat(result).isEqualTo(-1)
    }
}