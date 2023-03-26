package com.example.recipeme

import android.app.Application

class IngredientApplication : Application() {
    val db by lazy { AppDatabase.getInstance(this)}
}