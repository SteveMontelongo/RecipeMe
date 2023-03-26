package com.example.recipeme

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class RecipesList : AppCompatActivity() {
    var recipes: MutableList<Recipe> = ArrayList<Recipe>();
    var test: ArrayList<RecipeIngredient> = ArrayList<RecipeIngredient>();
    val tester = RecipeIngredient("name", "1", "1")

    lateinit var recipeRv: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        test.add(tester)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipes_list)

        val recipeListBtn = findViewById<Button>(R.id.addCstmRecipeBtn)
        val backBtn = findViewById<Button>(R.id.backBtn)

        recipeRv = findViewById(R.id.rvRecipes)
        val adapter = RecipesAdapter(this, recipes)

        recipeRv.adapter = adapter
        recipeRv.layoutManager = LinearLayoutManager(this);

//        val stringResult = intent.getStringExtra("RECIPE_MODEL")

//        if(stringResult !=null) {
//            Log.d("DebugLog", "Result OK")
//            val gson = Gson()
//            val itemType = object : TypeToken<Recipe>() {}.type
//            val itemModel: Recipe = gson.fromJson(stringResult, itemType)
//
//            recipes.add(Recipe(itemModel.name, itemModel.ingredients))
//            adapter.notifyDataSetChanged()
//        }

        backBtn.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            setResult(RESULT_OK, intent)
            finish()
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