package com.example.speedone.container

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.androidtask.R
import com.example.androidtask.databinding.ActivityContainerForFragmentBinding
import com.example.androidtask.ui.fragment.FragmentInsertUser
import com.example.androidtask.ui.fragment.UsersFragmentShow
import com.example.androidtask.util.CommonUtil
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

// this actiivity is used to add fragments inside it
@AndroidEntryPoint
class ContainerActivityForFragment : AppCompatActivity() {

    @Inject lateinit var util : CommonUtil
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityContainerForFragmentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var currentFragment  : Fragment= FragmentInsertUser() // login fragment
        var caseTag = INSERT
        if (intent?.getStringExtra(WHICH_FRAGMENT) ?: INSERT == LOAD) {
            currentFragment = UsersFragmentShow()
            caseTag = LOAD
        }

            util.changeFragmentBack(
                this,
                currentFragment,
                caseTag,
                Bundle(),
                R.id.fragment_container_navigation
            )

        onBackPressedDispatcher.addCallback(this, object: OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                setWhenBackPressed()
            }
        })

    }
  fun  setWhenBackPressed(){
      val count = supportFragmentManager.backStackEntryCount

      if (count > 1) {
          supportFragmentManager.popBackStack() // go to login
      }else {
          finish()
      }
    }

    companion object {
        val WHICH_FRAGMENT = "current_fragment"
        val INSERT = "INSERT"
        val LOAD = "LOAD"
    }

    override fun onBackPressed() {
        onBackPressedDispatcher.onBackPressed()

    }


}
