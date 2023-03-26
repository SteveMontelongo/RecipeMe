package com.example.recipeme

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class RecipesList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipes_list)

        val recipeListBtn = findViewById<Button>(R.id.addCstmRecipeBtn)
        val backBtn = findViewById<Button>(R.id.backBtn)
        backBtn.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            setResult(RESULT_OK, intent)
            finish()
        }
        recipeListBtn.setOnClickListener{
                val intent = Intent(this, RecipeAdd::class.java)
                this.startActivityForResult(intent, 5)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK){
            Log.d("DebugLog", "Result OK")
        }
        if(resultCode == Activity.RESULT_CANCELED){
            Log.d("DebugLog", "I have canceled")
        }
    }
}