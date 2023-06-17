package com.example.androidtask.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androidtask.R
import com.example.androidtask.databinding.ActivityMainBinding
import com.example.androidtask.databinding.RedirectLayoutBinding
import com.example.androidtask.util.CommonUtil
import com.example.speedone.container.ContainerActivityForFragment
import com.example.speedone.container.ContainerActivityForFragment.Companion.INSERT
import com.example.speedone.container.ContainerActivityForFragment.Companion.LOAD
import com.example.speedone.container.ContainerActivityForFragment.Companion.WHICH_FRAGMENT
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RedirectActivityFirst : AppCompatActivity() {
    var binding :RedirectLayoutBinding? =null
    @Inject lateinit var util: CommonUtil
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = RedirectLayoutBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        binding?.addUser?.setOnClickListener {
          startActivity(Intent(this@RedirectActivityFirst,ContainerActivityForFragment::class.java)
              .putExtra(WHICH_FRAGMENT,
                  INSERT))
        }
        binding?.showUsers?.setOnClickListener {
            startActivity(Intent(this,ContainerActivityForFragment::class.java).putExtra(WHICH_FRAGMENT,
                LOAD))
        }
    }
    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 1) {
            supportFragmentManager.popBackStack()
        } else {
            finish()
        }

    }

}
