package com.example.recipeme

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class RecipeAdd : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_add)

        val recipeAddBackBtn = findViewById<Button>(R.id.backBtnRecipeAdd)
        val recipeAddIngBtn = findViewById<Button>(R.id.addIngBtn)
        val recipeAddSubmitBtn = findViewById<Button>(R.id.addRecipeBtn)

        val tvIngView = findViewById<EditText>(R.id.IngredientAdd)
        val tvQuantityView = findViewById<EditText>(R.id.IngredientQuantity)
        val tvUnitView = findViewById<EditText>(R.id.tvUnit)

        recipeAddBackBtn.setOnClickListener{
            val intent = Intent(this, RecipeAdd::class.java)
            setResult(RESULT_OK, intent)
            finish()
        }

        recipeAddSubmitBtn.setOnClickListener{
            val intent = Intent(this, RecipeAdd::class.java)
            //send back recipe data
            //intent.addExtra ....

            setResult(RESULT_OK, intent)
            finish()
        }

    }
}