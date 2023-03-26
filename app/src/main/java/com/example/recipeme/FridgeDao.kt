package com.example.recipeme

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface FridgeDao {
    @Query("SELECT * FROM fridge_table")
    fun getAll(): Flow<List<FridgeEntity>>

    @Insert
    fun insertAll(ingredients: List<FridgeEntity>)

    @Query("DELETE FROM fridge_table")
    fun deleteAll()
}