package com.example.recipeme


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class RecipesAddAdapter (val context: Context, private val ingredients: List<RecipeIngredient>): RecyclerView.Adapter<RecipesAddAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context;
        val inflater = LayoutInflater.from(context);
        val itemView = inflater.inflate(R.layout.recipe_item_ingredient, parent, false) //edit
        return ViewHolder(itemView);
    }

    inner class ViewHolder(iView: View) : RecyclerView.ViewHolder(iView) {
        var iItem: RecipeIngredient? = null
        val nameView: TextView;
        val unitView: TextView
        val quantityView: TextView

        init {
            nameView = iView.findViewById(R.id.tvRecipeIngredientItemName);
            unitView = iView.findViewById(R.id.tvRecipeIngredientItemUnit)
            quantityView = iView.findViewById(R.id.tvRecipeIngredientItemQuantity)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = ingredients[position]
        holder.iItem = item
        holder.nameView.text = item.name;
        holder.quantityView.text = item.quantity
        holder.unitView.text = item.unit
        holder.itemView.setOnClickListener {
            holder.iItem?.let { item ->
                val index = item.name as String
                val toast = Toast.makeText(context, index, Toast.LENGTH_SHORT);
                toast.show()
            }
        }
    }

    override fun getItemCount(): Int {
        return ingredients.size;
    }
}
