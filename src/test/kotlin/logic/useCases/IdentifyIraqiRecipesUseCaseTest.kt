package logic.useCases

import dummyData.DummyRecipes
import dummyData.DummyTags
import io.mockk.every
import io.mockk.mockk
import logic.utili.filterByCountry
import org.example.logic.RecipesRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class IdentifyIraqiRecipesUseCaseTest{
 private lateinit var iraqiRecipesUseCase: IdentifyIraqiRecipesUseCase
  private val repository: RecipesRepository = mockk(relaxed = true)

  @BeforeEach
  fun stetup(){
   iraqiRecipesUseCase = IdentifyIraqiRecipesUseCase(repository)
  }
 @Test
 fun `should return iraqi recipe when pass all recipes `(){
  //given
  every{ repository.getRecipes()} returns DummyRecipes.iraqiRecipeOptions
  //when
   val result = iraqiRecipesUseCase.getIraqiRecipes()
  //then
  assertEquals(2,result.size)
 }
 @Test
 fun `should return empty list of iraqi recipe  when no recipes found `() {
  // Given
  every {  repository.getRecipes()} returns emptyList()

  // When
  val result = iraqiRecipesUseCase.getIraqiRecipes()

  // Then
  assertEquals(0, result.size)
 }
 @Test
 fun `should return list of iraqi recipes when description and tag for recipe contain iraqi word `(){
  // Given
  DummyRecipes.iraqiRecipeOptions
  //when
  val result = DummyRecipes.iraqiRecipeOptions.filterByCountry("iraqi")
  //then
  assertEquals(2,result.size)
 }

 }