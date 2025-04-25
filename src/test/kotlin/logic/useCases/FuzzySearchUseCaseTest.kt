package logic.useCases

import com.google.common.truth.Truth.assertThat
import org.example.logic.useCases.FuzzySearchUseCase
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class FuzzySearchUseCaseTest {
    private val fuzzySearchUseCase = FuzzySearchUseCase()

    // Core operations
    @ParameterizedTest
    @CsvSource(
        "kitten, sitting, 3, true",
        "flaw, lawn, 2, true",
        "apple, aple, 1, true",
        "star, srat, 2, true",
        "star, srat, 1, false"
    )
    fun `verify various edit operations`(text: String, query: String, max: Int, expected: Boolean) {
        //when
        val result = fuzzySearchUseCase.isFuzzyMatch(text, query, max)

        //then
        assertThat(result).isEqualTo(expected)
    }

    // Boundary conditions
    @Test
    fun `empty query returns false`() {
        //give
        val text = "any"
        val emptyQuery = ""

        //when
        val result = fuzzySearchUseCase.isFuzzyMatch("any", "")

        //then
        assertThat(result).isFalse()
    }


    @ParameterizedTest
    @CsvSource(
        // query, maxDistance, expected
        "'abc', 3, true",
        "'abcd', 3, false",
        "'a', 1, true",
        "'ab', 2, true",
        "'abcdef', 5, false"
    )
    fun `empty text matches queries within max distance`(query: String, maxDistance: Int, expected: Boolean) {
        //when
        val result = fuzzySearchUseCase.isFuzzyMatch("", query, maxDistance)

        //then
        assertThat(result).isEqualTo(expected)
    }


    // Single character scenarios
    @ParameterizedTest
    @CsvSource(
        "a, a, 0, true",
        "a, b, 1, true",
        "a, b, 0, false"
    )
    fun `single char matches`(text: String, query: String, maxDistance: Int, expected: Boolean) {
        //when
        val result = fuzzySearchUseCase.isFuzzyMatch(text, query, maxDistance)

        //then
        assertThat(result).isEqualTo(expected)
    }


    // Matrix edge cases
    @Test
    fun `minimum length comparisons`() {
        //given
        val onChar = "a"
        val twoChars = "ab"

        //when
        val result = fuzzySearchUseCase.isFuzzyMatch("ab", "a", 1)

        //then
        assertThat(result).isTrue()
    }


    // Long string stress tests
    @Test
    fun `handle long strings with edge edits`() {
        //given
        val longStr = "pneumonoultramicroscopicsilicovolcanoconiosis"

        //when
        val dropLastChar = fuzzySearchUseCase.isFuzzyMatch(longStr, longStr.dropLast(1), 1)
        val add_x = fuzzySearchUseCase.isFuzzyMatch(longStr, longStr + "x", 1)
        val takeLast_10 = fuzzySearchUseCase.isFuzzyMatch(longStr, longStr.take(10), 35)


        //then
        assertThat(dropLastChar && add_x && takeLast_10).isTrue()

    }

    @ParameterizedTest
    @CsvSource(
        "cafe, café, 1, true",
        "file_v2, ile_, 3,true"
    )
    fun `handle diacritics and special characters`(text: String, query: String, maxDistance: Int, expected: Boolean) {

        //when
        val result = fuzzySearchUseCase.isFuzzyMatch(text, query, maxDistance)

        //then
        assertThat(result).isEqualTo(true)
    }

    // Algorithm edge cases
    @ParameterizedTest
    @CsvSource(
        "abcd, abce",
        "abcd, abcf",
        "abcd, abcde",
        "abcd, abc"
    )
    fun `exact threshold boundaries`(text: String, query: String) {
        //when
        val result = fuzzySearchUseCase.isFuzzyMatch(text, query, 1)

        //then
        assertThat(result).isTrue()
    }


}