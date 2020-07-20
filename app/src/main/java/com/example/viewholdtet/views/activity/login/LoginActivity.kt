package com.example.viewholdtet.views.activity.login

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.viewholdtet.views.activity.main.MainActivity
import com.example.viewholdtet.R
import com.example.viewholdtet.databinding.ActivityLoginBinding
import com.example.viewholdtet.utils.ViewTesterConstants
import com.example.viewholdtet.view_model.LoginViewModel
import com.example.viewholdtet.views.activity.BaseActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var loginBinding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        loginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        loginBinding.lifecycleOwner = this
        loginBinding.loginViewModel = loginViewModel
        setUpObservers()
    }

    private fun setUpObservers() {
        loginViewModel.mErrorObserver.observe(this, Observer<Int> { t ->
            if (t == ViewTesterConstants.USER_NAME_ERROR) {
                loginBinding.mTxtUserName.error = getString(R.string.user_name_error)
                loginBinding.mTxtUserName.requestFocus()
            } else if (t == ViewTesterConstants.PASSWORD_ERROR) {
                loginBinding.mTxtPassword.error = getString(R.string.password_error)
                loginBinding.mTxtPassword.requestFocus()
            }
        })

        loginViewModel.mTokenObserver.observe(this, Observer<String> { t ->
            if(t != null) {
                startActivity(Intent(this, MainActivity::class.java))
                finishAffinity()
            } else {
                Toast.makeText(this,"Wrong Credentials", Toast.LENGTH_LONG).show()
            }
        })

        loginViewModel.mProgress.observe(this, Observer<Boolean>{ t->
            if(t){
                showProgress()
            } else {
                hideProgress()
            }
        })
    }

    override fun showProgress() {
        Handler(Looper.getMainLooper()).post {
            mLoginProgress.visibility = View.VISIBLE
        }
    }

    override fun hideProgress() {
        Handler(Looper.getMainLooper()).post {
            mLoginProgress.visibility = View.GONE
        }
    }
}