package logic.utili

import com.google.common.truth.Truth.assertThat
import dummyData.DummyTags
import dummyData.createDummyRecipe
import org.junit.jupiter.api.Test

class CommonUseCaseFunctionsKtTest {

    @Test
    fun `should return list of size 1 when filtering list with one iraqi recipe mention in tags and description`() {
        //given
        val countryList = listOf(
            createDummyRecipe(tags = listOf("iraq"), description = "this is iraqi recipe"),
            createDummyRecipe(tags = DummyTags.countriesTags, description = "this is italian recipe"),
        )

        //when
        val iraqiRecipes = countryList.filterByCountry("iraq")

        //then
        assertThat(iraqiRecipes.size).isEqualTo(1)
    }


    @Test
    fun `should return list of size 2 when filtering list with two iraqi recipes mention in mentioned once in tags and once in description`() {
        //given
        val countryList = listOf(
            createDummyRecipe(tags = listOf("iraq"), description = " default description"),
            createDummyRecipe(tags = DummyTags.countriesTags, description = "this is italian recipe"),
            createDummyRecipe(tags = DummyTags.countriesTags, description = " this is iraqi recipe"),
        )

        //when
        val iraqiRecipes = countryList.filterByCountry("iraq")

        //then
        assertThat(iraqiRecipes.size).isEqualTo(2)
    }


    @Test
    fun `should ignore null descriptions when filtering`() {
        // Given
        val recipes = listOf(
            createDummyRecipe(tags = listOf("iraq"), description = null),  // Tags match
            createDummyRecipe(tags = listOf("italy"), description = null)  // No match
        )

        // When
        val result = recipes.filterByCountry("iraq")

        // Then
        assertThat(result).hasSize(1)
    }


}