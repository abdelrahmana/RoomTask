package com.example.androidtask.ui.fragment.adaptor

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.androidtask.data.model.UserData
import com.example.androidtask.databinding.OneItemUserBinding



class AdaptorUsers(
    var context: Context, var arrayList: ArrayList<UserData?>) :
    RecyclerView.Adapter<AdaptorUsers.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {


        val binding = OneItemUserBinding.inflate(LayoutInflater.from(context),parent,false)

        return ViewHolder(
            binding
        )

    }

    override fun getItemCount(): Int {

        return arrayList.size

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bindItems(arrayList[position])

    }
   inner class ViewHolder(val itemViewBinding: OneItemUserBinding) : RecyclerView.ViewHolder(itemViewBinding.root) {
       fun bindItems(
           currentUser: UserData?
       ) {
           itemViewBinding.user.text = currentUser?.userName
           itemViewBinding.age.text = currentUser?.age
           itemViewBinding.job.text = currentUser?.job
           itemViewBinding.gender.text = currentUser?.gender

       }
   }


}