package com.example.androidtask.ui.fragment

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidtask.R
import com.example.androidtask.data.model.UserData
import com.example.androidtask.databinding.FragmentInsertUserBinding
import com.example.androidtask.databinding.UserInfoLayoutBinding
import com.example.androidtask.ui.fragment.adaptor.AdaptorUsers
import com.example.androidtask.util.CommonUtil
import com.example.androidtask.util.setVisibilty
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class UsersFragmentShow : Fragment() {
    var binding : UserInfoLayoutBinding? =null
    val viewModel : UserViewModel by viewModels()
    @Inject lateinit var progressDialog : Dialog
    @Inject lateinit var util : CommonUtil
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    var adaptor :  AdaptorUsers? =null
    var arrayList = ArrayList<UserData?>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = UserInfoLayoutBinding.inflate(layoutInflater,container,false)
        adaptor = AdaptorUsers(requireContext(), arrayList)
       util.setRecycleView(binding?.usersRecycle,adaptor!!
           ,LinearLayoutManager.VERTICAL
           ,requireContext(),
           null,false)
        viewModel.getUsersList() // will request user info
        observerListener()
        return binding!!.root
    }

    fun observerListener() {
        viewModel.usersLiveData.observe(viewLifecycleOwner, Observer{ // for sending to maintenance
            it?.let {
                arrayList.addAll(it)
                adaptor?.notifyDataSetChanged()
                   binding?.noResult?.setVisibilty(checkIfArrayIsEmpty(arrayList))
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

    private fun checkIfArrayIsEmpty(arrayList: ArrayList<UserData?>): Int {
        return if (arrayList.isNotEmpty())View.GONE else View.VISIBLE
    }
}