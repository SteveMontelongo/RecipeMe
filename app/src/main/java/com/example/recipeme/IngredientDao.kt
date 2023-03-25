package com.example.recipeme

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface IngredientDao {
    @Query("SELECT * FROM ingredients_table")
    fun getAll(): Flow<List<IngredientEntity>>

    @Insert
    fun insertAll(ingredients: List<Ingredient>)

    @Query("DELETE FROM ingredients_table")
    fun deleteAll()
}