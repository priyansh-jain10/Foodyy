package com.example.foody.viewmodels

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.foody.data.DataStoreRepository
import com.example.foody.util.Constants.Companion.API_KEY
import com.example.foody.util.Constants.Companion.DEFAULT_DIET_TYPE
import com.example.foody.util.Constants.Companion.DEFAULT_MEAL_TYPE
import com.example.foody.util.Constants.Companion.DEFAULT_RECEPIE_NUMBER
import com.example.foody.util.Constants.Companion.QUERY_ADD_RECIPE_INFORMATION
import com.example.foody.util.Constants.Companion.QUERY_API_KEY
import com.example.foody.util.Constants.Companion.QUERY_DIET
import com.example.foody.util.Constants.Companion.QUERY_FILL_INGREDIENTS
import com.example.foody.util.Constants.Companion.QUERY_NUMBER
import com.example.foody.util.Constants.Companion.QUERY_SEARCH
import com.example.foody.util.Constants.Companion.QUERY_TYPE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class RecipesViewModel  @ViewModelInject constructor
    (application: Application,
     private val dataStoreRepository: DataStoreRepository)
    : AndroidViewModel(application) {
    private var mealType= DEFAULT_MEAL_TYPE
    private var dietType= DEFAULT_DIET_TYPE
    val readMealAndDietType=dataStoreRepository.readMealAndDietType
    fun saveMealAndDietType(mealType:String,mealTypeId:Int,dietType:String,dietTypeId:Int){
        viewModelScope.launch(Dispatchers.IO) {dataStoreRepository.
        storeDataInDataPreferences(mealType, mealTypeId, dietType, dietTypeId)  }
    }

    fun applyQueries(): HashMap<String, String> {
        val queries: HashMap<String, String> = HashMap()

        viewModelScope.launch { readMealAndDietType.collect { value->
            mealType=value.selectedMealType
            dietType=value.selectedDietType} }

        queries[QUERY_NUMBER] = DEFAULT_RECEPIE_NUMBER.toString()
        queries[QUERY_API_KEY] = API_KEY
        queries[QUERY_TYPE] = mealType
        queries[QUERY_DIET] = dietType
        queries[QUERY_ADD_RECIPE_INFORMATION] = "true"
        queries[QUERY_FILL_INGREDIENTS] = "true"

        return queries
    }

    fun applySearchQueries(searchQuery:String):HashMap<String,String>{
        val query:HashMap<String,String> =HashMap();
        query[QUERY_SEARCH]=searchQuery
        query[QUERY_NUMBER]= DEFAULT_RECEPIE_NUMBER.toString()
        query[QUERY_API_KEY]= API_KEY
        query[QUERY_ADD_RECIPE_INFORMATION]="true"
        query[QUERY_FILL_INGREDIENTS]="true"
        return query
    }



}