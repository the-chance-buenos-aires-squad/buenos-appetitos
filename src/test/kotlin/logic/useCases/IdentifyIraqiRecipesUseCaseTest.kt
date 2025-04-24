package logic.useCases

import com.google.common.truth.Truth.assertThat
import dummyData.DummyRecipes
import dummyData.DummyTags
import io.mockk.every
import io.mockk.mockk
import logic.utili.filterByCountry
import org.example.logic.RecipesRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class IdentifyIraqiRecipesUseCaseTest {
 private lateinit var iraqiRecipesUseCase: IdentifyIraqiRecipesUseCase
 val repository: RecipesRepository = mockk()

 @BeforeEach
 fun stetUp() {
  iraqiRecipesUseCase = IdentifyIraqiRecipesUseCase(repository)
 }

 @Test
 fun `should return iraqi recipe when pass all recipes `() {
  //given
  every { repository.getRecipes() } returns DummyRecipes.iraqiRecipeOptions
  //when
  val result = iraqiRecipesUseCase.getIraqiRecipes()
  //then
  assertThat(result).isEqualTo(2)
 }

 @Test
 fun `should return empty list of iraqi recipe  when no recipes found `() {
  // Given
  every { repository.getRecipes() } returns emptyList()

  // When
  val result = iraqiRecipesUseCase.getIraqiRecipes()

  // Then
  assertThat(result).isEqualTo(0)
 }

 @Test
 fun `should return list of iraqi recipes when description and tag for recipe contain iraqi word `() {
  // Given
  DummyRecipes.iraqiRecipeOptions
  //when
  val result = DummyRecipes.iraqiRecipeOptions.filterByCountry("iraqi")
  //then
  assertThat(result).isEqualTo(2)
 }

}