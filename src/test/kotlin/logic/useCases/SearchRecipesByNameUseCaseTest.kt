package logic.useCases

import com.google.common.truth.Truth.assertThat
import dummyData.DummyRecipes
import io.mockk.every
import io.mockk.mockk
import org.example.logic.RecipesRepository
import org.example.logic.useCases.FuzzySearchUseCase
import org.example.logic.useCases.KmpSearchUseCase
import org.example.logic.useCases.SearchRecipesByNameUseCase
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class SearchRecipesByNameUseCaseTest {

    private lateinit var repository: RecipesRepository
    private lateinit var kmpSearch: KmpSearchUseCase
    private lateinit var fuzzySearch: FuzzySearchUseCase
    private lateinit var searchRecipesByNameUseCase: SearchRecipesByNameUseCase

    @BeforeEach
    fun setup() {
        repository = mockk()
        kmpSearch = mockk()
        fuzzySearch = mockk()
        every { repository.getRecipes() } returns DummyRecipes.searchTestRecipes
        searchRecipesByNameUseCase = SearchRecipesByNameUseCase(repository, fuzzySearch, kmpSearch)
    }

    @Test
    fun `should return recipe when exact match exists using KMP`() {
       //given
        every { kmpSearch.kmp(match { it.contains("chickn", ignoreCase = true) }, "chickn") } returns 0
        every { kmpSearch.kmp(not(match { it.contains("chickn", ignoreCase = true) }), "chickn") } returns -1
        every { fuzzySearch.isFuzzyMatch(any(), any(), any()) } returns false
        //when
        val result = searchRecipesByNameUseCase.searchRecipeName(query = "chickn", useFuzzy = false)
        //then
        assertThat(result).hasSize(1)
        assertThat(result[0].name.lowercase()).contains("chickn")
    }

    @Test
    fun `should return matching recipe using fuzzy match`() {
        //given
        every { kmpSearch.kmp(any(), "cheezy") } returns -1
        every { fuzzySearch.isFuzzyMatch("cheezy pasta bake", "cheezy", 2) } returns true
        every { fuzzySearch.isFuzzyMatch(not("cheezy pasta bake"), "cheezy", 2) } returns false
        //when
        val result = searchRecipesByNameUseCase.searchRecipeName(query = "cheezy", useFuzzy = true, maxTypos = 2)
        //then
        assertThat(result).hasSize(1)
        assertThat(result[0].name.lowercase()).contains("cheezy")
    }

    @Test
    fun `should return empty list when no match is found`() {
        //when
        every { kmpSearch.kmp(any(), "xyzabc") } returns -1
        every { fuzzySearch.isFuzzyMatch(any(), "xyzabc", any()) } returns false
        //when
        val result = searchRecipesByNameUseCase.searchRecipeName(query = "xyzabc")
        //then
        assertThat(result).isEmpty()
    }

    @Test
    fun `should return at most maxRecipesToShare results`() {
        //given
        val repeated = DummyRecipes.searchTestRecipes.first().copy(name = "Cake")
        val largeList = List(100) { repeated }

        every { repository.getRecipes() } returns largeList
        every { kmpSearch.kmp(any(), "cake") } returns 0
        every { fuzzySearch.isFuzzyMatch(any(), any(), any()) } returns false
        //when
        val useCaseWithLimit = SearchRecipesByNameUseCase(
            repository, fuzzySearch, kmpSearch, maxRecipesToShare = 10
        )
        val result = useCaseWithLimit.searchRecipeName(query = "cake")
        //then
        assertThat(result).hasSize(10)
    }
}
