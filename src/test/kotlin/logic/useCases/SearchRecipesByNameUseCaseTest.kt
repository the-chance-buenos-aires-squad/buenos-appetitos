package logic.useCases

import com.google.common.truth.Truth.assertThat
import dummyData.DummyRecipes
import io.mockk.every
import io.mockk.mockk
import org.example.logic.RecipesRepository
import org.example.logic.useCases.FuzzySearchUseCase
import org.example.logic.useCases.KmpSearchUseCase
import org.example.logic.useCases.SearchRecipesByNameUseCase
import org.example.logic.utili.CommonUtilizes.DEFAULT_NUM_OF_RECIPES
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class SearchRecipesByNameUseCaseTest {

    private lateinit var repository: RecipesRepository
    private lateinit var kmpSearch: KmpSearchUseCase
    private lateinit var fuzzySearch: FuzzySearchUseCase
    private lateinit var searchUseCase: SearchRecipesByNameUseCase

    @BeforeEach
    fun setup() {
        repository = mockk()
        kmpSearch = mockk()
        fuzzySearch = mockk()
        every { repository.getRecipes() } returns DummyRecipes.searchTestRecipes
        searchUseCase = SearchRecipesByNameUseCase(repository, fuzzySearch, kmpSearch)
    }

    @Test
    fun `should return recipe when exact match exists using KMP`() {
        // given
        stubKmpForExactMatch("chickn")
        stubFuzzyAlwaysFalse()
        // when
        val result = searchUseCase.searchRecipeName(query = "chickn", useFuzzy = false)
        // then
        assertThat(result).hasSize(1)
    }

    @Test
    fun `should return matching recipe using fuzzy match`() {
        // given
        stubKmpAlwaysNegative("cheezy")
        stubFuzzyForExactNameMatch("cheezy pasta bake", "cheezy", 2)
        // when
        val result = searchUseCase.searchRecipeName(query = "cheezy", useFuzzy = true, maxTypos = 2)
        // then
        assertThat(result[0].name.lowercase()).contains("cheezy")
    }

    @Test
    fun `should return empty list when no match is found`() {
        // given
        stubKmpAlwaysNegative("xyzabc")
        stubFuzzyAlwaysFalse()
        // when
        val result = searchUseCase.searchRecipeName(query = "xyzabc")
        // then
        assertThat(result).isEmpty()
    }

    @Test
    fun `should return at most maxRecipesToShare results`() {
        // given
        val repeated = DummyRecipes.searchTestRecipes.first().copy(name = "Cake")
        val largeList = List(100) { repeated }

        every { repository.getRecipes() } returns largeList
        stubKmpAlwaysPositive("cake")
        stubFuzzyAlwaysFalse()

        val useCaseWithLimit = SearchRecipesByNameUseCase(
            repository,
            fuzzySearch,
            kmpSearch,
            maxRecipesToShare = DEFAULT_NUM_OF_RECIPES
        )

        // when
        val result = useCaseWithLimit.searchRecipeName(query = "cake")

        // then
        assertThat(result).hasSize(DEFAULT_NUM_OF_RECIPES)
    }


    private fun stubKmpForExactMatch(query: String) {
        every { kmpSearch.kmp(match { it.contains(query, ignoreCase = true) }, query) } returns 0
        every { kmpSearch.kmp(not(match { it.contains(query, ignoreCase = true) }), query) } returns -1
    }

    private fun stubKmpAlwaysNegative(query: String) {
        every { kmpSearch.kmp(any(), query) } returns -1
    }

    private fun stubKmpAlwaysPositive(query: String) {
        every { kmpSearch.kmp(any(), query) } returns 0
    }

    private fun stubFuzzyAlwaysFalse() {
        every { fuzzySearch.isFuzzyMatch(any(), any(), any()) } returns false
    }

    private fun stubFuzzyForExactNameMatch(name: String, query: String, maxTypos: Int) {
        every { fuzzySearch.isFuzzyMatch(name, query, maxTypos) } returns true
        every { fuzzySearch.isFuzzyMatch(not(name), query, maxTypos) } returns false
    }
}
