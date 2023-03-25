package com.example.recipeme

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class IngredientsAdapter (val context: Context, private val ings: List<Ingredient>, private val iListener: IngredientInterface?): RecyclerView.Adapter<IngredientsAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
        val context = parent.context;
        val inflater = LayoutInflater.from(context);
        val itemView = inflater.inflate(R.layout.ingredient_item, parent, false)
        return ViewHolder(itemView);
    }

    inner class ViewHolder(iView: View): RecyclerView.ViewHolder(iView), View.OnClickListener{
        var iItem: Ingredient? = null
        val nameView: TextView;
        val quantityView: TextView;

        init {
            nameView = iView.findViewById(R.id.tvIngredientNameItem);
            quantityView = iView.findViewById(R.id.tvIngredientQuantityItem);
            iView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val index = "hi"
            val toast = Toast.makeText(context, index, Toast.LENGTH_SHORT);
        }
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = ings[position]
        holder.iItem = item
        holder.nameView.text = item.name;
        holder.quantityView.text = item.quantity;
        holder.itemView.setOnClickListener{
            holder.iItem?.let{
                item->
                iListener?.onClick(item)
            }
        }
    }

    override fun getItemCount(): Int {
        return ings.size;
    }

}