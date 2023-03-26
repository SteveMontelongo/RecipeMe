package com.example.recipeme

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class RecipeAdd : AppCompatActivity() {
    var recipeIngredients: MutableList<RecipeIngredient> = ArrayList<RecipeIngredient>();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_add)

        val recipeAddBackBtn = findViewById<Button>(R.id.backBtnRecipeAdd)
        val recipeAddIngBtn = findViewById<Button>(R.id.addIngBtn)
        val recipeAddSubmitBtn = findViewById<Button>(R.id.addRecipeBtn)

        val tvRecipeNameView = findViewById<EditText>(R.id.rvRecipeName)
        val tvIngView = findViewById<EditText>(R.id.IngredientAdd)
        val tvQuantityView = findViewById<EditText>(R.id.IngredientQuantity)
        val tvUnitView = findViewById<EditText>(R.id.tvUnit)

        val rvRecipeIngredients = findViewById<RecyclerView>(R.id.rvRecipeIngredients)


        val adapter = RecipesAddAdapter(this, recipeIngredients, )
        rvRecipeIngredients.adapter = adapter
        rvRecipeIngredients.layoutManager = LinearLayoutManager(this);


        recipeAddIngBtn.setOnClickListener{
            val ing = RecipeIngredient(tvIngView.text.toString(), tvQuantityView.text.toString(), tvUnitView.text.toString())
            recipeIngredients.add(ing)
            adapter.notifyDataSetChanged()
        }

        recipeAddBackBtn.setOnClickListener{
            val intent = Intent(this, RecipeAdd::class.java)
            setResult(RESULT_OK, intent)
            finish()
        }

        recipeAddSubmitBtn.setOnClickListener{
            val recipeName = tvRecipeNameView.text.toString()
                //add recipeName to intent
            val intent = Intent(this, RecipesList::class.java)
            //send back recipe data
            //intent.addExtra ...
            val recipeModel = Recipe(recipeName, recipeIngredients as ArrayList<RecipeIngredient>)
            intent.putExtra("RECIPE_MODEL", Json.encodeToString(recipeModel))
            intent.putExtra("RECIPE_NAME", recipeName)
            Log.d("DebugLog", Json.encodeToString(recipeModel))
//            var toast = Toast.makeText(this, Json.encodeToString(recipeModel), Toast.LENGTH_SHORT)
//            toast.show()
            setResult(RESULT_OK, intent)
            finish()
//            startActivity(intent)
        }

    }
}