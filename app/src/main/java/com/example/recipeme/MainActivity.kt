package com.example.recipeme

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    var ingredients: MutableList<Ingredient> = ArrayList<Ingredient>();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Adding views to connect xml and class variables
        val ingRv = findViewById<RecyclerView>(R.id.recyclerView);
        val addButton = findViewById<Button>(R.id.btnAddItem);
        val ingNameView = findViewById<EditText>(R.id.tvIngredientsName);
        val ingQuantityView = findViewById<EditText>(R.id.tvIngredientsQuantity);

        //adapter to connect views with the recycler view
        val adapter = IngredientsAdapter(ingredients)
        ingRv.adapter = adapter;
        ingRv.layoutManager = LinearLayoutManager(this);

        addButton.setOnClickListener {
            var item = Ingredient(ingNameView.text.toString(), ingQuantityView.text.toString());
            ingredients.add(item);
            adapter.notifyDataSetChanged()
            ingNameView.getText().clear();
            ingQuantityView.getText().clear();
            hideKeyboard(this);
        }
    }
    fun hideKeyboard(activity: Activity) {
        val imm: InputMethodManager =
            activity.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view = activity.currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}