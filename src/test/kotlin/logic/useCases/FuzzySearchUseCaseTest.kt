package org.example.logic.useCases

import com.google.common.truth.Truth.assertThat
import org.example.logic.useCases.FuzzySearchUseCase
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import kotlin.test.Test

class FuzzySearchUseCaseTest{
    private val fuzzySearchUseCase:FuzzySearchUseCase = FuzzySearchUseCase()



    @ParameterizedTest
    @CsvSource(
        "kitten, sitting, 3, true",  // Substitution heavy
        "flaw, lawn, 2, true",       // Deletion heavy
        "apple, aple, 1, true"       // Insertion heavy
    )
    fun `parameterized edit operations`(text: String, query: String, max: Int, expected: Boolean) {
        assertThat(fuzzySearchUseCase.isFuzzyMatch(text, query, max)).isEqualTo(expected)
    }


    @Test
    fun `should match single character substitution`() {
        assertThat(fuzzySearchUseCase.isFuzzyMatch("a", "b", 1)).isTrue()
    }

    @Test
    fun `should reject single character over threshold`() {
        assertThat(fuzzySearchUseCase.isFuzzyMatch("a", "b", 0)).isFalse()
    }

    @Test
    fun `should reject when both text and query are empty`() {
        assertThat(fuzzySearchUseCase.isFuzzyMatch("", "")).isFalse()
    }


    @Test
    fun `should count transpositions as two operations`() {
        // "star" -> "srat" (distance = 2)
        assertThat(fuzzySearchUseCase.isFuzzyMatch("star", "srat", 2)).isTrue()
        assertThat(fuzzySearchUseCase.isFuzzyMatch("star", "srat", 1)).isFalse()
    }

    @Test
    fun `should handle query length 1 vs text length 0`() {
        // Insertion cost = 1 (text is empty)
        assertThat(fuzzySearchUseCase.isFuzzyMatch("", "a", 1)).isTrue()
    }

    @Test
    fun `should handle text length 1 vs query length 0`() {
        // Query is empty → returns false
        assertThat(fuzzySearchUseCase.isFuzzyMatch("a", "")).isFalse()
    }


    @Test
    fun `should prefer substitution over insertion`() {
        // "cat" -> "car" (substitution cost = 1)
        assertThat(fuzzySearchUseCase.isFuzzyMatch("cat", "car", 1)).isTrue()
    }

    @Test
    fun `should prefer deletion over substitution`() {
        // "flight" -> "fligh" (delete 't', cost = 1)
        assertThat(fuzzySearchUseCase.isFuzzyMatch("flight", "fligh", 1)).isTrue()
    }

    @Test
    fun `should prefer insertion over substitution`() {
        // "file" -> "files" (insert 's', cost = 1)
        assertThat(fuzzySearchUseCase.isFuzzyMatch("file", "files", 1)).isTrue()
    }

    @Test
    fun `should handle matching first characters`() {
        // Distance = 0 (exact match)
        assertThat(fuzzySearchUseCase.isFuzzyMatch("apple", "apple")).isTrue()
    }

    @Test
    fun `should handle mismatched first characters`() {
        // a -> b (substitution, distance = 1)
        assertThat(fuzzySearchUseCase.isFuzzyMatch("apple", "bpple", 1)).isTrue()
    }

    @Test
    fun `should match long words with multiple substitutions and insertions`() {
        // Given
        val longText = "pneumonoultramicroscopicsilicovolcanoconiosis"
        val typoQuery = "pneumonoultramicroscopicsilicovolcanoconiosix" // Extra 'x' at end (distance = 1)

        // When
        val result = fuzzySearchUseCase.isFuzzyMatch(
            text = longText,
            query = typoQuery,
            maxDistance = 1
        )

        // Then
        assertThat(result).isTrue()
    }

    @Test
    fun `should reject long words exceeding max distance`() {
        // Given
        val longText = "supercalifragilisticexpialidocious"
        val badTypo =  "supercalifragilisticexpialidociouunmyz" // 2 changes: 's'->'z'+ extra unmy

        // When
        val result = fuzzySearchUseCase.isFuzzyMatch(
            text = longText,
            query = badTypo,
            maxDistance = 4
        )

        // Then
        assertThat(result).isFalse()
    }

    @Test
    fun `should accept long words matched max distance of 5`() {
        // Given
        val longText = "supercalifragilisticexpialidocious"
        val badTypo =  "supercalifragilisticexpialidociouunmyz" // 2 changes: 's'->'z'+ extra unmy

        // When
        val result = fuzzySearchUseCase.isFuzzyMatch(
            text = longText,
            query = badTypo,
            maxDistance = 5
        )

        // Then
        assertThat(result).isTrue()
    }


}