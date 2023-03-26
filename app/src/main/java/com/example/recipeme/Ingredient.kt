package com.example.recipeme

import kotlinx.serialization.Serializable
@Serializable
class Ingredient(val name: String, var quantity: String) {
}