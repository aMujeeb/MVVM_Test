package com.example.viewholdtet.views.activity.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.viewholdtet.R
import com.example.viewholdtet.databinding.ActivitySplashBinding
import com.example.viewholdtet.views.activity.login.LoginActivity
import com.example.viewholdtet.utils.LoggerUtils
import com.example.viewholdtet.view_model.SplashViewModel

class SplashActivity : AppCompatActivity() {

    private lateinit var mSplashBinding : ActivitySplashBinding
    private lateinit var mSplashViewModel : SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mSplashViewModel = ViewModelProvider(this).get(SplashViewModel:: class.java)
        mSplashBinding = DataBindingUtil.setContentView(this, R.layout.activity_splash)
        mSplashBinding.lifecycleOwner = this
        mSplashBinding.splashVModel = mSplashViewModel
        setUpObservers()
    }

    private fun setUpObservers() {
        mSplashViewModel.mTokenObserver.observe(this, Observer<String>{ t->
            if(t != null){
                LoggerUtils.logMessage("Token Received")
            }
            startActivity(Intent(this, LoginActivity::class.java))
            finishAffinity()
        })
    }

    override fun onResume() {
        super.onResume()
        mSplashViewModel.requestNewSessionToken()
    }
}