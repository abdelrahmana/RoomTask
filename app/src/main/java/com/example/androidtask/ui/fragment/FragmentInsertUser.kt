package com.example.androidtask.ui.fragment

import android.app.Dialog
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.androidtask.R
import com.example.androidtask.data.model.Constants
import com.example.androidtask.data.model.UserData
import com.example.androidtask.databinding.FragmentInsertUserBinding
import com.example.androidtask.util.CommonUtil
import com.google.android.material.card.MaterialCardView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class FragmentInsertUser : Fragment() {
    var binding : FragmentInsertUserBinding? =null
    val viewModel : UserViewModel by viewModels()
    @Inject lateinit var util : CommonUtil
    @Inject lateinit var progressDialog : Dialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    var gender = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentInsertUserBinding.inflate(layoutInflater,container,false)
        binding?.saveButton?.setOnClickListener{
       //     lifecycleScope.launch {
            var error = ""
          error =
              checkDataIsEmpty(binding?.userNameEditText?.text.toString(),getString(R.string.name_required))+
                      checkDataIsEmpty(binding?.ageEditText?.text.toString(),getString(R.string.age_is_required))+
                      checkDataIsEmpty(binding?.jobTitle?.text.toString(),getString(R.string.job_title_is_required))+
                      checkDataIsEmpty(gender,getString(R.string.gender_required))
            if (error.isEmpty())
                viewModel.postInsertUser(UserData(System.currentTimeMillis()
                    ,binding?.userNameEditText?.text.toString(),binding?.ageEditText?.text.toString(),
                binding?.jobTitle?.text.toString(),gender))
                else
                util.showSnackMessages(requireActivity(),error)
    //  }
        }
        setOnclickGender(binding!!.manCard,R.color.teal_200,binding!!.femaleCard,R.color.card_color
            ,Constants.MALE)
        setOnclickGender(binding!!.femaleCard,R.color.teal_200,binding!!.manCard,R.color.card_color,
            Constants.FEMAILE)
        observerListener()
        return binding!!.root
    }
    fun observerListener() {
        viewModel.insertLiveData.observe(viewLifecycleOwner, Observer{ // for sending to maintenance
           it?.let {
               Handler(Looper.getMainLooper()).postDelayed({
                   requireActivity().onBackPressedDispatcher.onBackPressed()
               },800)
               util.showSnackMessages(requireActivity(),it,R.color.green_color)

           }
        })
        viewModel.networkLoader.observe(viewLifecycleOwner, Observer{
            it?.setDialog(progressDialog)
        })

        viewModel.errorViewModel.observe(viewLifecycleOwner) {
            it?.let { error ->
                util.showSnackMessages(requireActivity(), error)

            }
        }
    }
    private fun setOnclickGender(
        selectedCard: MaterialCardView,
        newTint: Int,
        unselectedCard: MaterialCardView,
        unselectedColor: Int,
        selectedGender: String
    ) {
        selectedCard.setOnClickListener{
            it.setBackgroundTintList(ContextCompat.getColorStateList(requireContext(),newTint))
            unselectedCard.setBackgroundTintList(ContextCompat.getColorStateList(requireContext(),unselectedColor))
            this.gender = selectedGender
        }
    }
    fun checkDataIsEmpty(currentViewString: String, errorMessage: String): String {
        if (currentViewString.isEmpty())
            return "$errorMessage\n"
        else
            return ""
    }


}