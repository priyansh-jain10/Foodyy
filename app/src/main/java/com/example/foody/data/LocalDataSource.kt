package com.example.foody.data

import com.example.foody.data.database.FavouritesEntity
import com.example.foody.data.database.RecipesDao
import com.example.foody.data.database.RecipesEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val recipesDao: RecipesDao
) {

    fun readDatabase(): Flow<List<RecipesEntity>> {
        return recipesDao.readRecipes()
    }

    fun readFavoriteRecipes(): Flow<List<FavouritesEntity>> {
        return recipesDao.readFavoriteRecipes()
    }

    suspend fun insertRecipes(recipesEntity: RecipesEntity) {
        recipesDao.insertRecipes(recipesEntity)
    }

    suspend fun insertFavouriteRecipes(favouritesEntity: FavouritesEntity) {
        recipesDao.insertFavouriteRecipes(favouritesEntity)
    }

    suspend fun deleteFavoriteRecipe(favoritesEntity: FavouritesEntity) {
        recipesDao.deleteFavoriteRecipe(favoritesEntity)
    }

    suspend fun deleteAllFavoriteRecipes() {
        recipesDao.deleteAllFavoriteRecipes()

    }
}