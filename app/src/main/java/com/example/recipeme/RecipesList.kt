package com.example.recipeme

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.launch

class RecipesList : AppCompatActivity() {
    var recipes: MutableList<Recipe> = ArrayList<Recipe>();
    var fridgeIngredients: MutableList<DisplayFridge> = ArrayList<DisplayFridge>()

    lateinit var recipeRv: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipes_list)

        val recipeListBtn = findViewById<Button>(R.id.addCstmRecipeBtn)
        val backBtn = findViewById<Button>(R.id.backBtn)

        recipeRv = findViewById(R.id.rvRecipes)
        val adapter = RecipesAdapter(this, recipes)

        recipeRv.adapter = adapter
        recipeRv.layoutManager = LinearLayoutManager(this);

        backBtn.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            setResult(RESULT_OK, intent)
            finish()
        }

        lifecycleScope.launch {
            (application as IngredientApplication).db.ingredientDao().getAll().collect { databaseList ->
                databaseList.map { entity ->
                    DisplayFridge(
                        entity.ingName,
                        entity.ingQuantity
                    )
                }.also { mappedList ->
                    fridgeIngredients.clear()
                    fridgeIngredients.addAll(mappedList)
                }
            }
        }.also{
            var i =0
            for(recipe in recipes){
                var j =0
                for(ingredient in recipe.ingredients)
                    if(fridgeIngredients.contains(ingredient as DisplayFridge)){

                        j++
                    }
                i++
            }
        }

        recipeListBtn.setOnClickListener{
            val intent = Intent(this, RecipeAdd::class.java)
            this.startActivityForResult(intent, 5)
        }
        adapter.notifyDataSetChanged()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK){
            Log.d("DebugLog", "Result OK")
            val recipeNameString = data?.getStringExtra("RECIPE_MODEL")
            if(recipeNameString !=null){
                Log.d("DebugLog", "RecipeNameString not null")
                val gson = Gson()
                val itemType = object: TypeToken<Recipe>(){}.type
                val itemModel : Recipe = gson.fromJson(recipeNameString, itemType)
                Log.d("DebugLog", "Model: " + itemModel.name)
                Log.d("DebugLog", "RecipeSize1: " + recipes.size)

                recipes.add(Recipe(itemModel.name, itemModel.ingredients))
                recipeRv.adapter?.notifyDataSetChanged()

                Log.d("DebugLog", "RecipeSize2: " + recipes.size)



            }
        }
        if(resultCode == Activity.RESULT_CANCELED){
            Log.d("DebugLog", "I have canceled")
        }
    }
}