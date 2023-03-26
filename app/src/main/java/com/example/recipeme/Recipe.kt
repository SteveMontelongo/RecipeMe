package com.example.recipeme

import kotlinx.serialization.Serializable
@Serializable
class Recipe(val name: String, val ingredients : ArrayList<RecipeIngredient>)