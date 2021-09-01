package com.example.foody.ui.ui.bottomsheet

import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.foody.R
import com.example.foody.ui.fragments.recipes.RecipesFragmentArgs
import com.example.foody.util.Constants.Companion.DEFAULT_DIET_TYPE
import com.example.foody.util.Constants.Companion.DEFAULT_MEAL_TYPE
import com.example.foody.viewmodels.RecipesViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import kotlinx.android.synthetic.main.fragment_recepies_bottom_sheet.view.*
import java.lang.Exception
import java.util.*

class RecepiesBottomSheet :BottomSheetDialogFragment() {

    private lateinit var recepiesViewModel: RecipesViewModel
    private var mealtypeChip=DEFAULT_MEAL_TYPE
    private var mealtypeChipId= 0
    private var diettypeChip= DEFAULT_DIET_TYPE
    private var diettypeChipId= 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        recepiesViewModel=ViewModelProvider(requireActivity()).get(RecipesViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val mView=inflater.inflate(R.layout.fragment_recepies_bottom_sheet, container, false)


        recepiesViewModel.readMealAndDietType.asLiveData().observe(viewLifecycleOwner,{value->
            mealtypeChip=value.selectedMealType
            diettypeChip=value.selectedDietType
            updateChip(value.selectedMealTypeId,mView.meal_type_chipGroup)
            updateChip(value.selectedDietTypeId,mView.diet_type_chipGroup)
        })
        mView.meal_type_chipGroup.setOnCheckedChangeListener{group,selectedChipId->
            val chip=group.findViewById<Chip>(selectedChipId)
            val selectedMealType=chip.text.toString().toLowerCase(Locale.ROOT)
            mealtypeChip=selectedMealType
            mealtypeChipId=selectedChipId
        }
        mView.diet_type_chipGroup.setOnCheckedChangeListener{group,selectedChipId->
            val chip=group.findViewById<Chip>(selectedChipId)
            val selectedDietType=chip.text.toString().toLowerCase(Locale.ROOT)
            diettypeChip=selectedDietType
            diettypeChipId=selectedChipId
        }

        mView.SectionSubmitButton.setOnClickListener{
            recepiesViewModel.saveMealAndDietType(mealtypeChip,mealtypeChipId,diettypeChip,diettypeChipId)
            val action=RecepiesBottomSheetDirections.actionRecepiesBottomSheetToRecipesFragment(true)
            findNavController().navigate(action)
        }
        return mView
    }

    private fun updateChip(chipId: Int, Chipgroup: ChipGroup) {
        if(chipId!=0){
            try{
                    Log.d("BOTTOM_SHEET","Selected Chip Id is"+chipId)
                    Chipgroup.findViewById<Chip>(chipId).isChecked=true
            }catch (e:Exception){
                Log.d("RecipesBottomSheet",e.message.toString())
            }
        }
    }
}