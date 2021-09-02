package com.example.foody.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.foody.models.Result
import com.example.foody.util.Constants.Companion.FAVORITE_RECIPES_TABLE


@Entity(tableName =FAVORITE_RECIPES_TABLE)
class FavouritesEntity (
    @PrimaryKey(autoGenerate = true)
    var id:Int,var result: Result)

