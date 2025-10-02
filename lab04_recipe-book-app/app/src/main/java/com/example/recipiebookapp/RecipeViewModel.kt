package com.example.recipiebookapp

import androidx.lifecycle.ViewModel


// ViewModel of Recipes
class RecipeViewModel : ViewModel() {

    fun sortByName() { MockRecipes.sortBy { it.name } }
    fun sortByRating() { MockRecipes.sortByDescending { it.rating } } // Descending to show best rating first.

    fun addRecipe(){

    }
}