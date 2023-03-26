package com.example.recipeme

import kotlinx.serialization.Serializable
@Serializable
class RecipeIngredient(val name: String, val unit: String, val quantity: String)