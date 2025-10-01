package com.example.recipiebookapp


// Class to display values
data class Recipes(
    val name: String,
    val description: String?,
    val rating: Int,
    val tag: List<Tags>
)

enum class Tags {
    VEGAN, MEAT, SWEET, FISH,
    ADVANCED,HARD, MEDIUM, EASY,
}


// Mock data to test with
val MockRecipes = mutableListOf(
    Recipes(
        name = "Pizza",
        description = "A flavour of italy! Meat and salad combined with flower can be an enticing combintaion",
        rating = 5,
        tag = listOf<Tags>(Tags.EASY, Tags.MEAT)
    ),
    Recipes(
        name = "Sushi",
        description = "Fresh taste of the sea. Rice, fish, and vegetables wrapped in perfection.",
        rating = 4,
        tag = listOf<Tags>(Tags.FISH, Tags.ADVANCED)
    ),

            Recipes(
            name = "Pancakes",
    description = "Soft and fluffy – perfect for breakfast or dessert, with syrup or berries.",
    rating = 3,
    tag = listOf(Tags.EASY, Tags.SWEET)
)
)