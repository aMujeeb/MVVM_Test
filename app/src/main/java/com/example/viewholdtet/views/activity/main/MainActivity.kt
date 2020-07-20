package com.example.viewholdtet.views.activity.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.viewholdtet.R
import com.example.viewholdtet.databinding.MainActivityBinding
import com.example.viewholdtet.view_model.MainViewModel
import com.example.viewholdtet.views.activity.BaseActivity
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : BaseActivity() {

    private lateinit var mMainViewModel: MainViewModel
    private lateinit var mMainBinding : MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.main_activity)
        /*if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow()
        }*/

        mMainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        mMainBinding = DataBindingUtil.setContentView(this, R.layout.main_activity)
        mMainBinding.lifecycleOwner = this
        mMainBinding.mainViewModel = mMainViewModel

        setSupportActionBar(toolbar)
        NavigationUI.setupActionBarWithNavController(this, findNavController(R.id.main_nav_fragment))
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.main_nav_fragment).navigateUp()
    }

    override fun showProgress() {
        mMainBinding.mMainProgress.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        mMainBinding.mMainProgress.visibility = View.GONE
    }
}