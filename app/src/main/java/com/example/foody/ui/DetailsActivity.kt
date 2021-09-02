package com.example.foody.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.navArgs
import com.example.foody.R
import com.example.foody.adapters.PagerAdapter
import com.example.foody.data.database.FavouritesEntity
import com.example.foody.ui.fragments.Ingredients.IngredientsFrag
import com.example.foody.ui.fragments.Instructions.InstructionsFrag
import com.example.foody.ui.fragments.Overview.OverView
import com.example.foody.viewmodels.MainViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_details.*
@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {

    private var savedRecipe=false
    private var savedRecipeId=0
    private val args by navArgs<DetailsActivityArgs>()
    private val mainViewModel:MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        setSupportActionBar(toolbar)
        val fragment=ArrayList<Fragment>()
        fragment.add(OverView())
        fragment.add(IngredientsFrag())


        val titles=ArrayList<String>()
        titles.add("Overview")
        titles.add("Ingredient")
        //titles.add("Instructions")

        val resultBundle=Bundle()
        resultBundle.putParcelable("recipeBundle",args.result)
        val adapter=PagerAdapter(resultBundle,fragment,titles,supportFragmentManager)
         Viewpager.adapter=adapter
        tablayout.setupWithViewPager(Viewpager)


        toolbar.setTitleTextColor(ContextCompat.getColor(this,R.color.white))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.details_menu,menu)
        val menuItem=menu?.findItem(R.id.favouriteIcon)
        checkSavedRecipe(menuItem!!)
        return true
    }

    private fun checkSavedRecipe(menuItem: MenuItem) {
        mainViewModel.readFavouriteRecepies.observe(this,{favouritesEntity->
            try{
                for(savedReicpe in favouritesEntity)
                    if(savedReicpe.result.id==args.result.id){
                        changeMenuItemColor(menuItem,R.color.yellow)
                        savedRecipeId=savedReicpe.id
                        savedRecipe=true
                    }
                else{
                    changeMenuItemColor(menuItem,R.color.white)
                    }
            }
            catch (e:Exception){
                Log.d("DetailsActivity",e.message.toString())
            }
        })

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==android.R.id.home)
            finish()
        else if(item.itemId==R.id.favouriteIcon&&!savedRecipe){
            saveToFavourites(item)
        }else if(item.itemId==R.id.favouriteIcon&&savedRecipe){
            removeFromFavourites(item)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun saveToFavourites(item: MenuItem) {
        val favouritesEntity=FavouritesEntity(0,args.result)
        mainViewModel.insertFavouriteRecepies(favouritesEntity)
        changeMenuItemColor(item,R.color.yellow)
        Snackbar.make(detailsLayout,"Recipe saved",Snackbar.LENGTH_SHORT).setAction("Okay"){}.show()
        savedRecipe=true
    }
    private fun removeFromFavourites(item:MenuItem){
        val favouritesEntity=FavouritesEntity(savedRecipeId,args.result)
        mainViewModel.deleteFavouriteRecipes(favouritesEntity)
        Snackbar.make(detailsLayout,"Removed from Favorites",Snackbar.LENGTH_SHORT).setAction("Okay"){}.show()
        savedRecipe=false
    }

    private fun changeMenuItemColor(item: MenuItem, color: Int) {
        item.icon.setTint(ContextCompat.getColor(this,color))
    }
}