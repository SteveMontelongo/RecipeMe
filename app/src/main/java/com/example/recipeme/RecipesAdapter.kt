package com.example.recipeme

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class RecipesAdapter (val context: Context, private val recipes: List<Recipe>): RecyclerView.Adapter<RecipesAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context;
        val inflater = LayoutInflater.from(context);
        val itemView = inflater.inflate(R.layout.recipe_item, parent, false) //edit
        return ViewHolder(itemView);
    }

    inner class ViewHolder(iView: View) : RecyclerView.ViewHolder(iView) {
        var iItem: Recipe? = null
        val nameView: TextView;

        init {
            nameView = iView.findViewById(R.id.recipeName);
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = recipes[position]
        holder.iItem = item
        holder.nameView.text = item.name;
        holder.itemView.setOnClickListener {
            holder.iItem?.let { item ->
                val index = item.name as String
                val toast = Toast.makeText(context, index, Toast.LENGTH_SHORT);
                toast.show()
            }
        }
    }

    override fun getItemCount(): Int {
        return recipes.size;
    }
}
