package com.example.foody.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.navArgs
import com.example.foody.R
import com.example.foody.adapters.PagerAdapter
import com.example.foody.ui.fragments.Ingredients.IngredientsFrag
import com.example.foody.ui.fragments.Instructions.InstructionsFrag
import com.example.foody.ui.fragments.Overview.OverView
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {
    private val args by navArgs<DetailsActivityArgs>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        setSupportActionBar(toolbar)
        val fragment=ArrayList<Fragment>()
        fragment.add(OverView())
        fragment.add(IngredientsFrag())
        fragment.add(InstructionsFrag())

        val titles=ArrayList<String>()
        titles.add("Overview")
        titles.add("Ingredient")
        titles.add("Instructions")

        val resultBundle=Bundle()
        resultBundle.putParcelable("recipeBundle",args.result)
        val adapter=PagerAdapter(resultBundle,fragment,titles,supportFragmentManager)
         Viewpager.adapter=adapter
        tablayout.setupWithViewPager(Viewpager)


        toolbar.setTitleTextColor(ContextCompat.getColor(this,R.color.white))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==android.R.id.home)
            finish()
        return super.onOptionsItemSelected(item)
    }
}