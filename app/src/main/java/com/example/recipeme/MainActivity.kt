package com.example.recipeme

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    var ingredients: MutableList<DisplayIngredient> = ArrayList<DisplayIngredient>();
    lateinit var ingRv: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Adding views to connect xml and class variables
        ingRv = findViewById<RecyclerView>(R.id.recyclerView);
        val addButton = findViewById<Button>(R.id.btnAddItem);
        val ingNameView = findViewById<EditText>(R.id.tvIngredientsName);
        val ingQuantityView = findViewById<EditText>(R.id.tvIngredientsQuantity);

        //adapter to connect views with the recycler view
        val adapter = IngredientsAdapter(this, ingredients, )
        ingRv.adapter = adapter

        lifecycleScope.launch {
            (application as IngredientApplication).db.ingredientDao().getAll().collect { databaseList ->
                databaseList.map { entity ->
                    DisplayIngredient(
                        entity.ingName,
                        entity.ingQuantity
                    )
                }.also { mappedList ->
                    ingredients.clear()
                    ingredients.addAll(mappedList)
                    adapter.notifyDataSetChanged()
                }
            }
        }
        ingRv.layoutManager = LinearLayoutManager(this);

        addButton.setOnClickListener {
            var item = DisplayIngredient(ingNameView.text.toString(), ingQuantityView.text.toString());
            ingredients.add(item);
            adapter.notifyDataSetChanged()
            ingNameView.getText().clear();
            ingQuantityView.getText().clear();
            hideKeyboard(this);
            ingredients.let { list ->
                lifecycleScope.launch(IO) {
                    (application as IngredientApplication).db.ingredientDao().deleteAll()
                    (application as IngredientApplication).db.ingredientDao().insertAll(list.map {
                        IngredientEntity(
                            ingName = it.name,
                            ingQuantity = it.quantity
                        )
                    })
                }
            }


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

    fun dummyfunction(name: String){
        val toast = Toast.makeText(this, name, Toast.LENGTH_SHORT);
        toast.show()
        for(item in ingredients){
            var toast2 = Toast.makeText(this, item.name, Toast.LENGTH_SHORT);
            toast2.show()
        }
    }

}