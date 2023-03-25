package com.example.recipeme

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [IngredientEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase(){

}