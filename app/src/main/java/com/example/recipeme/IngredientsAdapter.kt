package com.example.recipeme

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView



class IngredientsAdapter (private val ings: List<Ingredient>): RecyclerView.Adapter<IngredientsAdapter.ViewHolder>(){
    class ViewHolder(iView: View): RecyclerView.ViewHolder(iView){
        val nameView: TextView;
        val quantityView: TextView;

        init {
            nameView = iView.findViewById(R.id.tvIngredientNameItem);
            quantityView = iView.findViewById(R.id.tvIngredientQuantityItem);

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
        val context = parent.context;
        val inflater = LayoutInflater.from(context);
        val wishItemView = inflater.inflate(R.layout.ingredient_item, parent, false)
        return ViewHolder(wishItemView);
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = ings.get(position)
        holder.nameView.text = item.name;
        holder.quantityView.text = item.quantity;
    }

    override fun getItemCount(): Int {
        return ings.size;
    }


}