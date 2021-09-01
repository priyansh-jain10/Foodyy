package com.example.foody.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.example.foody.R
import com.example.foody.adapters.IngredientsAdapter.*
import com.example.foody.models.ExtendedIngredient
import com.example.foody.util.Constants.Companion.BASE_IMAGE_URL
import com.example.foody.util.RecipesDiffUtil
import kotlinx.android.synthetic.main.ingredients_row_layout.view.*

class IngredientsAdapter: RecyclerView.Adapter<MyViewHolder>() {
    private var ingredientsList=emptyList<ExtendedIngredient>()
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.ingredients_row_layout,parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.ingredient_imageView.load(BASE_IMAGE_URL+ingredientsList[position].image)
        holder.itemView.ingredient_name.text=ingredientsList[position].name
        holder.itemView.ingredient_consistency.text=ingredientsList[position].consistency
        holder.itemView.ingredient_unit.text=ingredientsList[position].unit
        holder.itemView.ingredient_amount.text=ingredientsList[position].amount.toString()
        holder.itemView.ingredient_original.text=ingredientsList[position].original
    }

    override fun getItemCount(): Int {
        return ingredientsList.size
    }

    fun setData(newIngredients:List<ExtendedIngredient>): List<ExtendedIngredient>{
        val ingredientsDiffUtil=RecipesDiffUtil(ingredientsList,newIngredients)
        val diffUtilResult= DiffUtil.calculateDiff(ingredientsDiffUtil)
        ingredientsList=newIngredients
        diffUtilResult.dispatchUpdatesTo(this)
        return ingredientsList
    }
}