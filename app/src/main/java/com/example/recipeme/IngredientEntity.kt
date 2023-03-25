package com.example.recipeme

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ingredients_table")
data class IngredientEntity(
    @PrimaryKey(autoGenerate = true) val id: Long =0,
    @ColumnInfo(name = "ingName") val ingName: String?,
    @ColumnInfo(name = "ingQuantity") val ingQuantity: String?
)
