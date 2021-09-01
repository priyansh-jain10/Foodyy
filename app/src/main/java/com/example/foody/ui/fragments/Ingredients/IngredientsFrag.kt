package com.example.foody.ui.fragments.Ingredients

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foody.R
import com.example.foody.adapters.IngredientsAdapter
import com.example.foody.models.Result
import kotlinx.android.synthetic.main.fragment_ingredients.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [IngredientsFrag.newInstance] factory method to
 * create an instance of this fragment.
 */
class IngredientsFrag : Fragment() {
    private val mAdapter:IngredientsAdapter by lazy { IngredientsAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_ingredients, container, false)

        val args=arguments
        val myBundle:Result?=args?.getParcelable("recipeBundle")

        setupRecyclerView(view)
        myBundle?.extendedIngredients?.let { mAdapter.setData(it) }
        return view
    }

    private fun setupRecyclerView(view:View) {
        view.ingredientsRecyclerView.adapter=mAdapter
        view.ingredientsRecyclerView.layoutManager=LinearLayoutManager(requireContext())
    }

}