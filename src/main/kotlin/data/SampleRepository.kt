package org.example.data

import org.example.logic.MealsRepository
import org.example.model.MainMeal
import java.text.SimpleDateFormat

class SampleRepository:MealsRepository {


    val sampleMainMeals = listOf(
        MainMeal(
            name = "arriba   baked winter squash mexican style",
            id = 137739,
            minutes = 55,
            contributorId = 47892,
            submitted = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2005-09-16 00:00:00"),
            tags = listOf("60-minutes-or-less", "time-to-make", "course", "main-ingredient", "cuisine", "preparation", "occasion", "north-american", "side-dishes", "vegetables", "mexican", "easy", "fall", "holiday-event", "vegetarian", "winter", "dietary", "christmas", "seasonal", "squash"),
            nutrition = listOf(51.5, 0.0, 13.0, 0.0, 2.0, 0.0, 4.0),
            numberOfSteps = 11,
            steps = listOf(
                "make a choice and proceed with recipe",
                "depending on size of squash , cut into half or fourths",
                "remove seeds",
                "for spicy squash , drizzle olive oil or melted butter over each cut squash piece",
                "season with mexican seasoning mix ii",
                "for sweet squash , drizzle melted honey , butter , grated piloncillo over each cut squash piece",
                "season with sweet mexican spice mix",
                "bake at 350 degrees , again depending on size , for 40 minutes up to an hour , until a fork can easily pierce the skin",
                "be careful not to burn the squash especially if you opt to use sugar or butter",
                "if you feel more comfortable , cover the squash with aluminum foil the first half hour , give or take , of baking",
                "if desired , season with salt"
            ),
            description = "autumn is my favorite time of year to cook! this recipe can be prepared either spicy or sweet, your choice! two of my posted mexican-inspired seasoning mix recipes are offered as suggestions.",
            ingredients = listOf("winter squash", "mexican seasoning", "mixed spice", "honey", "butter", "olive oil", "salt"),
            numberOfIngredients = 7
        ),
        MainMeal(
            name = "a bit different  breakfast pizza",
            id = 31490,
            minutes = 30,
            contributorId = 26278,
            submitted = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2002-06-17 00:00:00"),
            tags = listOf("30-minutes-or-less", "time-to-make", "course", "main-ingredient", "cuisine", "preparation", "occasion", "north-american", "breakfast", "main-dish", "pork", "american", "oven", "easy", "kid-friendly", "pizza", "dietary", "northeastern-united-states", "meat", "equipment"),
            nutrition = listOf(173.4, 18.0, 0.0, 17.0, 22.0, 35.0, 1.0),
            numberOfSteps = 9,
            steps = listOf(
                "preheat oven to 425 degrees f",
                "press dough into the bottom and sides of a 12 inch pizza pan",
                "bake for 5 minutes until set but not browned",
                "cut sausage into small pieces",
                "whisk eggs and milk in a bowl until frothy",
                "spoon sausage over baked crust and sprinkle with cheese",
                "pour egg mixture slowly over sausage and cheese",
                "s& p to taste",
                "bake 15-20 minutes or until eggs are set and crust is brown"
            ),
            description = "this recipe calls for the crust to be prebaked a bit before adding ingredients. feel free to change sausage to ham or bacon. this warms well in the microwave for those late risers.",
            ingredients = listOf("prepared pizza crust", "sausage patty", "eggs", "milk", "salt and pepper", "cheese"),
            numberOfIngredients = 6
        ),
        // Continued for all 10 items...
        MainMeal(
            name = "bananas 4 ice cream  pie",
            id = 70971,
            minutes = 180,
            contributorId = 102353,
            submitted = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2003-09-10 00:00:00"),
            tags = listOf("weeknight", "time-to-make", "course", "main-ingredient", "preparation", "pies-and-tarts", "desserts", "lunch", "snacks", "no-cook", "refrigerator", "kid-friendly", "frozen-desserts", "pies", "chocolate", "dietary", "inexpensive", "equipment", "number-of-servings", "technique", "4-hours-or-less"),
            nutrition = listOf(4270.8, 254.0, 1306.0, 111.0, 127.0, 431.0, 220.0),
            numberOfSteps = 8,
            steps = listOf(
                "crumble cookies into a 9-inch pie plate , or cake pan",
                "pat down to form an even layer",
                "drizzle 1 cup of chocolate topping evenly over the cookies with a small spoon",
                "scoop the vanilla ice cream on top of the chocolate and smooth down",
                "cover with half of the sliced bananas",
                "top with strawberry ice cream",
                "cover and freeze until firm",
                "before serving , top with 1 / 4 cup chocolate topping , whipped cream , and sliced bananas"
            ),
            description = "",
            ingredients = listOf("chocolate sandwich style cookies", "chocolate syrup", "vanilla ice cream", "bananas", "strawberry ice cream", "whipped cream"),
            numberOfIngredients = 6
        ),
        MainMeal(
            name = "greens to go  smoothie",
            id = 353044,
            minutes = 6,
            contributorId = 336058,
            submitted = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2009-01-30 00:00:00"),
            tags = listOf("15-minutes-or-less", "time-to-make", "course", "main-ingredient", "preparation", "low-protein", "beverages", "breakfast", "fruit", "vegetables", "easy", "beginner-cook", "vegetarian", "smoothies", "food-processor-blender", "dietary", "low-sodium", "low-calorie", "low-carb", "low-in-something", "apples", "tropical-fruit", "bananas", "greens", "equipment", "small-appliance", "3-steps-or-less"),
            nutrition = listOf(209.5, 20.0, 64.0, 1.0, 5.0, 58.0, 7.0),
            numberOfSteps = 4,
            steps = listOf(
                "add all ingredients , except greens to a blender",
                "stuff the rest of the blender with greens - about three to five good handfuls - they shrink when blended ! lettuce , romaine , spinach , chard , kale , collards , parsley , or whatever you have in the fridge or garden",
                "blend until smooth",
                "you could use more fruit than greens and slowly add more greens as you get used to them"
            ),
            description = "this is from nancy campbell from above rubies magazine.  \"the chlorophyll in greens combats tumor growth; it works on the adrenal glands, purifies the lymph nodes, unclogs the arteries and enriches the blood. it protects against radiation. chlorophyll is only one of the many benefits of greens. it also provides a large gamut of vitamins and minerals, phyto-nutrients and colon cleansing cellulose. it is blended so smoothly that even babies starting solids and toddlers who can't chew up greens can swallow these power foods and not gag. the cellulose is broken down and the greens are so easily digested that they begin to assimilate even while still in their mouth.\"  you might need to play with the greens amount to get the right amount of sweetness.  i added more fruit to cut the greens taste and would use only milk or kefir, instead of water next time.  try strawberry, raspberry, peach etc.  i adjusted it just a bit.",
            ingredients = listOf("banana", "water", "honey", "apple", "coconut milk", "flax seed oil", "oat bran", "greens"),
            numberOfIngredients = 8
        ),
        MainMeal(
            name = "hawaiian  chicken salad appetizer",
            id = 263781,
            minutes = 15,
            contributorId = 603152,
            submitted = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2007-11-07 00:00:00"),
            tags = listOf("15-minutes-or-less", "time-to-make", "course", "preparation", "appetizers", "easy"),
            nutrition = listOf(205.7, 24.0, 12.0, 19.0, 4.0, 26.0, 4.0),
            numberOfSteps = 5,
            steps = listOf(
                "combine ingredients except for the bread",
                "chill in refrigerator",
                "when ready to serve , cut the top part of the hawaiian king bread off leaving a hallow space to put in the chicken salad",
                "cut up the bread into chunks",
                "use the bread to dip into the chicken salad"
            ),
            description = "this is a wonderful chicken salad. my family calls it \"hawaiian chicken\" only because it is served in the \"hawaiian king\" bread bowl.",
            ingredients = listOf("sour cream", "knorr vegetable soup mix", "green onion", "water chestnuts", "hellmann's mayonnaise", "hawaiian bread"),
            numberOfIngredients = 6
        ),
        MainMeal(
            name = "healthy  fried rice",
            id = 66932,
            minutes = 25,
            contributorId = 93406,
            submitted = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2003-07-16 00:00:00"),
            tags = listOf("30-minutes-or-less", "time-to-make", "course", "main-ingredient", "cuisine", "preparation", "side-dishes", "rice", "asian", "microwave", "stove-top", "dietary", "low-sodium", "low-in-something", "pasta-rice-and-grains", "brown-rice", "equipment"),
            nutrition = listOf(372.6, 19.0, 13.0, 2.0, 19.0, 10.0, 18.0),
            numberOfSteps = 7,
            steps = listOf(
                "heat 1 tbsp oil in pan and saut mushrooms until browned",
                "remove from heat",
                "heat remaining oil in pan and add rice",
                "add egg and mix until rice is thoroughly coated with egg",
                "cook for 5-10 minutes on high , or until egg is lightly browned",
                "combine rice mixture and vegetables in large bowl and stir until vegetables are well mixed",
                "add soy sauce to taste and serve"
            ),
            description = "i made this once, hoping to have something light for lunch. not only was it easy, but it tasted great, and the bulk of the rice kept me full for hours!",
            ingredients = listOf("brown rice", "olive oil", "peas", "carrot", "broccoli floret", "sliced mushrooms", "egg", "soy sauce"),
            numberOfIngredients = 8
        ),
        MainMeal(
            name = "healthy  pumpkin pie",
            id = 48131,
            minutes = 75,
            contributorId = 41810,
            submitted = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2002-12-09 00:00:00"),
            tags = listOf("weeknight", "time-to-make", "course", "main-ingredient", "cuisine", "preparation", "occasion", "north-american", "pies-and-tarts", "desserts", "vegetables", "oven", "easy", "diabetic", "fall", "heirloom-historical", "holiday-event", "kid-friendly", "vegetarian", "pies", "dietary", "gifts", "christmas", "thanksgiving", "seasonal", "comfort-food", "novelty", "taste-mood", "equipment", "4-hours-or-less"),
            nutrition = listOf(36.3, 0.0, 3.0, 2.0, 7.0, 0.0, 1.0),
            numberOfSteps = 9,
            steps = listOf(
                "preheat oven to 425f",
                "mix pumpkin , milk , and egg whites until smooth",
                "gradually stir in splenda",
                "add the pumpkin pie spice",
                "taste and add more if need be",
                "pour into crust and spread evenly",
                "bake in the oven for 15 minutes then reduce the temperature to 350f and bake for another 45 minutes",
                "let cool and serve your favorite way",
                "to make to crust less likely to turn soggy , beat some egg whites and brush over the crust and bake at 350f for about 5 minutes"
            ),
            description = "i made up this recipe and whipped it up the night before thanksgiving, it was great! it's about as healthy as a pie can possibly be! only problem was that my crust was soggy, but the packaging gave a suggestion to that, which i have included to keep yours from going soggy. plus it's delicious, low-fat and sugar-free.  what more could you ask for in a pie?!",
            ingredients = listOf("pumpkin", "skim milk", "egg whites", "reduced fat graham cracker crust", "splenda sugar substitute", "pumpkin pie spice"),
            numberOfIngredients = 6
        ),
        MainMeal(
            name = "homebakes   knock off beef stew and biscuits",
            id = 32363,
            minutes = 35,
            contributorId = 29110,
            submitted = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2002-06-26 00:00:00"),
            tags = listOf("60-minutes-or-less", "time-to-make", "course", "main-ingredient", "preparation", "occasion", "5-ingredients-or-less", "casseroles", "main-dish", "beef", "oven", "easy", "stews", "copycat", "one-dish-meal", "comfort-food", "meat", "novelty", "taste-mood", "equipment"),
            nutrition = listOf(191.9, 14.0, 6.0, 17.0, 9.0, 13.0, 7.0),
            numberOfSteps = 7,
            steps = listOf(
                "preheat oven to 350f",
                "pour stew into an 8 x 8-inch baking dish , i recommend glass",
                "prepare biscuits according to package",
                "spoon them onto the top of the stew",
                "bake for 30 minutes or until biscuits are browned",
                "let stand for 3-5 minutes",
                "serve and enjoy"
            ),
            description = "i love figuring out what's in the packaged foods that i like. this should be easy enough for even the most novice cook.",
            ingredients = listOf("beef stew", "biscuits"),
            numberOfIngredients = 2
        ),
        MainMeal(
            name = "honey  i m peanuts about you   cake",
            id = 293225,
            minutes = 70,
            contributorId = 240552,
            submitted = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2008-03-21 00:00:00"),
            tags = listOf("time-to-make", "course", "main-ingredient", "preparation", "occasion", "for-large-groups", "desserts", "oven", "easy", "dinner-party", "holiday-event", "cakes", "chocolate", "dietary", "taste-mood", "sweet", "equipment", "number-of-servings", "presentation", "served-cold", "4-hours-or-less"),
            nutrition = listOf(1187.4, 137.0, 205.0, 19.0, 55.0, 206.0, 31.0),
            numberOfSteps = 39,
            steps = listOf(
                "cook's",
                "preheat the oven to 350 degrees f",
                "grease 2 round cake pans",
                "as the oven preheats , begin to make the cake batter",
                "dissolve the cocoa into the espresso",
                "stir the buttermilk into the cocoa mixture and set aside to cool",
                "whisk together the flour , baking soda and salt",
                "in another bowl , beat the butter with an electric mixer at medium speed for about 6 minutes , while gradually adding the sugar",
                "the butter mixture should be light and fluffy",
                "slowly add the eggs and vanilla to the butter , while continuing to beat",
                "be sure to scrape down the sides of the bowl as you go , so all the ingredients get incorporated",
                "fold the flour and cocoa mixtures into the butter mixture with a rubber spatula , alternating from flour to cocoa in the following order: 1 / 3 flour mixture",
                "1 / 2 cocoa mixture",
                "1 / 3 flour mixture",
                "1 / 2 cocoa mixture",
                "1 / 3 flour mixture",
                "do not overmix !",
                "pour half of the finished batter into each of the cake pans and level the tops with the spatula",
                "bake for 30-40 minutes in the center of the oven , rotating halfway through",
                "check with a wooden toothpick for doneness",
                "cool for at least 15 minutes before inverting onto a baking rack to cool completely",
                "make the buttercream: with an electric mixer beat the peanut butter and butter in a large bowl until creamy",
                "add the powdered sugar and beat until spreadable",
                "with a serrated knife halve the two layers making 4",
                "place 1 cake layer top down on the rack and spread 1 / 3 of the buttercream to the edges of the layer",
                "top the first cake layer with another and repeat with the buttercream",
                "continue layering with the remaining layers and buttercream , ending with cake",
                "refrigerate covered in plastic wrap for at least 3 hours and up to overnight",
                "remove the cake from the refrigerator and carefully transfer to a wire rack over a sheet pan",
                "to make the ganache , chop the butter and scatter it into the chocolate pieces in a large bowl",
                "heat the cream and corn syrup in a saucepan over moderate heat",
                "as soon as the cream mixture begins to boil , pour it over the chocolate mixture and begin stirring vigorously",
                "when the chocolate has melted and is combined thoroughly with the cream , stir in the vanilla",
                "quickly begin pouring the ganache over the top of the cake , allowing it to flow down and cover the sides",
                "pack the chopped peanuts onto the ganache to cover the sides of the cake , leaving the chocolate on top exposed",
                "store in the refrigerator",
                "serving suggestions: i like to reserve a little of the buttercream and a handful of whole honey roasted peanuts to decorate the top of the cake",
                "try using a star",
                "then , set peanuts into the buttercream border to form an interesting design"
            ),
            description = "i saw this recipe showdown cake recipe and had to try it.  dh was like \"wow! you have got to try it!\"  i told him that i had already had enough of it.  i had to give this away so i wouldn't eat more. lol!  this recipe makes a good size cake so you don't need big pieces. this is a chocolate peanut butter lovers dream! :d",
            ingredients = listOf("unsweetened cocoa", "espresso", "buttermilk", "unbleached all-purpose flour", "baking soda", "salt", "unsalted butter", "sugar", "extra-large eggs", "pure vanilla extract", "creamy peanut butter", "powdered sugar", "semisweet chocolate", "heavy cream", "light corn syrup", "honey roasted peanuts"),
            numberOfIngredients = 16
        ),
        MainMeal(
            name = "hot tamale  burgers",
            id = 235173,
            minutes = 60,
            contributorId = 240500,
            submitted = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2007-06-16 00:00:00"),
            tags = listOf("60-minutes-or-less", "time-to-make", "course", "main-ingredient", "preparation", "main-dish", "rice", "vegan", "vegetarian", "dietary", "pasta-rice-and-grains", "brown-rice"),
            nutrition = listOf(165.3, 5.0, 4.0, 0.0, 7.0, 2.0, 10.0),
            numberOfSteps = 15,
            steps = listOf(
                "bring rice and 3 cups water to a boil in medium sauce pan",
                "reduce heat to low , cover and simmer 40 minutes or until water is absorbed by rice",
                "meanwhile , heat oil in large skillet over medium heat",
                "add onion , bell pepper , corn , chipotle , adobo sauce , garlic , cumin and salt",
                "saut 2 minutes , then reduce heat to low",
                "cover and cook 15 minutes or until vegetables are tender , stirring once or twice",
                "whisk 1 / 2 cup cornmeal in bowl add to skillet mixture and cover and cook on low for 10 minutes more",
                "stirring once or twice",
                "remove from heat , and stir in hot rice , cilantro , lime juice and zest",
                "cool 20 minutes",
                "wet hands and shape mixture into 8 burgers",
                "refrigerate 30 minutes",
                "prepare grill to medium heat",
                "brush burgers and grill rack with olive oil",
                "grill 7 minutes per side or until crusty on the outside"
            ),
            description = "spicy burgers are vegetarian.",
            ingredients = listOf("short-grain brown rice", "olive oil", "onion", "red bell pepper", "corn kernel", "chipotle chile in adobo", "garlic cloves", "ground cumin", "yellow cornmeal", "cilantro", "fresh lime juice", "lime zest"),
            numberOfIngredients = 12
        ),
        MainMeal(
            name = "i can t believe i made cream puffs",
            id = 226001,
            minutes = 55,
            contributorId = 237783,
            submitted = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2007-05-02 00:00:00"),
            tags = listOf("60-minutes-or-less", "time-to-make", "course", "preparation", "desserts", "inexpensive"),
            nutrition = listOf(198.6, 17.0, 57.0, 8.0, 6.0, 40.0, 6.0),
            numberOfSteps = 12,
            steps = listOf(
                "heat water and butter to rolling boil",
                "stir in flour , stirring vigorously over low heat about 1 minute , or until mixture forms a ball",
                "remove from heat",
                "beat in eggs all at one time and continue beating until smooth",
                "drop dough by scant 1 / 4 cupfuls about 3\" apart on ungreased baking sheet",
                "bake at 400 for 35 to 40 minutes , or until puffed and golden",
                "cool out of draft",
                "cut in half horizontally , open , and remove any uncooked filaments of dough",
                "make dream whip according to package directions",
                "make pudding with only half the milk it calls for",
                "quickly add the pudding to the dream whip and mix well",
                "fill cream puffs , sprinkle with a little powdered sugar and refrigerate until serving -- if you don't eat them first !"
            ),
            description = "my old friend pat gave me this recipe 20 years ago and i really love these easy puffs....a little too much, as my waistline attests!",
            ingredients = listOf("water", "butter", "all-purpose flour", "eggs", "instant vanilla pudding", "whipped dessert topping mix"),
            numberOfIngredients = 6
        ),
        MainMeal(
            name = "i hate miracle whip  but i love this potato salad that s",
            id = 220186,
            minutes = 40,
            contributorId = 450571,
            submitted = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2007-04-02 00:00:00"),
            tags = listOf("60-minutes-or-less", "time-to-make", "course", "main-ingredient", "cuisine", "preparation", "occasion", "north-american", "side-dishes", "potatoes", "vegetables", "american", "picnic", "dietary", "midwestern", "to-go"),
            nutrition = listOf(333.4, 20.0, 39.0, 22.0, 17.0, 13.0, 15.0),
            numberOfSteps = 5,
            steps = listOf(
                "boil potatoes until tender but not mushy , set aside to cool",
                "for dressing: mix all ingredients in a bowl",
                "any and all of the ingredients can be adjusted to your taste as my dressing never turns out the same way twice depending on whether i'm in the mood for sweet , peppery , salty , etc",
                "mix potatoes , celery , onion , egg , and dressing in a bowl and chill",
                "enjoy !"
            ),
            description = "i have had people who swear they hate miracle whip eat an entire plate of this potato salad.  it's my mom's recipe, and you can try it with regular mayo, but i won't stand by the results because it's so good with the whip that i've never tried it any other way!",
            ingredients = listOf("potatoes", "celery", "white onions", "hard-boiled eggs", "miracle whip", "milk", "sugar", "mustard", "dijon mustard", "white pepper", "fresh ground pepper", "salt"),
            numberOfIngredients = 12
        ),
        MainMeal(
            name = "i thought i had nothing to eat  rice",
            id = 217201,
            minutes = 35,
            contributorId = 333475,
            submitted = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2007-03-16 00:00:00"),
            tags = listOf("60-minutes-or-less", "time-to-make", "course", "main-ingredient", "preparation", "occasion", "for-1-or-2", "main-dish", "side-dishes", "rice", "easy", "vegetarian", "dietary", "spicy", "one-dish-meal", "inexpensive", "egg-free", "free-of-something", "pasta-rice-and-grains", "white-rice", "taste-mood", "savory", "number-of-servings"),
            nutrition = listOf(792.5, 19.0, 1.0, 11.0, 26.0, 37.0, 50.0),
            numberOfSteps = 5,
            steps = listOf(
                "boil water , take off heat and add tea bag",
                "let steep",
                "add rice and all other ingredients except butter , bring back to a boil",
                "reduce heat , cover and simmer for 20 minutes",
                "remove from heat , stir in butter until completely melted"
            ),
            description = "i have no money, so i had to make something to eat out of what i had hanging around (not much). this is what i ended up with!! i had to eat it by itself, but i think it would be good with a juicy steak or some grilled marinated portabello mushrooms. the flavor of the rice depends on the tea; i'm thinking about making some rice with lemon tea and black pepper, basil, dill and red pepper to have with some fish. i used 1/4 teaspoon as a standard measurement for all the spices, they are meant to be \"to taste\".",
            ingredients = listOf("water", "white rice", "tea bag", "seasoning salt", "black pepper", "dried basil", "chili powder", "garlic powder", "cayenne pepper", "soy sauce", "lemon juice", "butter"),
            numberOfIngredients = 12
        )
    )



    override fun getMeals(): List<MainMeal> {
        return sampleMainMeals
    }

}
