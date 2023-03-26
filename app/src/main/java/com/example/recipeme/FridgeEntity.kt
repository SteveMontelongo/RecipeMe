package com.example.recipeme


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fridge_table")
data class FridgeEntity(
    @PrimaryKey(autoGenerate = true) val id: Long =0,
    @ColumnInfo(name = "fridgeIngredientName") val ingredientName: String?,
    @ColumnInfo(name = "fridgeIngredientQuantity") val ingredientQuantity: String?
)