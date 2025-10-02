package com.example.recipiebookapp

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf

// Class to display values
data class Recipes(
    val name: String,
    var description: String?,
    val rating: Int,
    val tag: List<Tags>
)

enum class Tags {
    VEGAN, MEAT, SWEET, FISH,
    ADVANCED,HARD, MEDIUM, EASY,
}



// Mock data of recipes
var MockRecipes = mutableStateListOf(
    Recipes(
        name = "Pizza",
        description = "A flavour of italy! Meat and salad combined with flower can be an enticing combintaion",
        rating = 5,
        tag = listOf(Tags.EASY, Tags.MEAT)
    ),
    Recipes(
        name = "Sushi",
        description = "Fresh taste of the sea. Rice, fish, and vegetables wrapped in perfection.",
        rating = 4,
        tag = listOf(Tags.FISH, Tags.ADVANCED)
    ),
    Recipes(
        name = "Pancakes",
        description = null, // Should show "No description yet"
        rating = 2,
        tag = listOf(Tags.EASY, Tags.SWEET)
    )
)