package com.example.viewholdtet.views.activity

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.example.viewholdtet.R
import com.example.viewholdtet.view_model.BaseViewModel
import kotlinx.android.synthetic.main.dialog_general_error.view.*

open class BaseActivity : AppCompatActivity() {

    private var mAlertDialog: android.app.AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun setBaseContentView(layoutResID: Int) {
        //var mMainBaseLayout = layoutInflater.inflate(R.layout.activity_base, null) as ConstraintLayout
        //var mMainFrameLayout = mMainBaseLayout.findViewById<View>(R.id.mBase_Container)
        //layoutInflater.inflate(layoutResID, mBase_Container, true)
        //super.setContentView(layoutResID)

    }

    fun hideSoftKeyBoard(view: View?) {
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    open fun showProgress() {}
    open fun hideProgress() {}
    open fun displayGeneralErrorMessage(message: Int) {
        displayGeneralErrorMessage(resources.getString(message))
    }

    open fun displayGeneralErrorMessage(message: String) {
        val alertBuilder = android.app.AlertDialog.Builder(this)
        var inflater: LayoutInflater = this.layoutInflater
        var dialogView = inflater.inflate(R.layout.dialog_general_error, null)
        alertBuilder.setView(dialogView)
        dialogView.mLblMessage.text = message
        dialogView.mBtnOk.setOnClickListener {
            if (mAlertDialog != null) {
                mAlertDialog!!.dismiss()
            }
        }
        mAlertDialog = alertBuilder.create();
        mAlertDialog!!.show()
    }

    fun enableBackNavigation() {}
    fun disableBackNavigation() {}
    fun navigateToHome() {}
}