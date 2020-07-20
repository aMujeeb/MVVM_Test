package com.example.viewholdtet.views.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.viewholdtet.views.activity.BaseActivity

open class BaseFragment : Fragment() {
    private var mBaseActivity: BaseActivity? = null
    private var mBackNavigationEnabled = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBaseActivity = this!!.getSupportActivity()!!
    }

    open fun getSupportActivity(): BaseActivity? {
        val activity = activity
        return if (activity != null && activity is BaseActivity) {
            activity
        } else {
            return null
        }
    }

    protected fun hideKeyBoard(view: View) {
        mBaseActivity?.hideSoftKeyBoard(view)
    }

    fun setBackNavigationEnabled(enabled: Boolean) {
        this.mBackNavigationEnabled = enabled
        if (enabled) {
            mBaseActivity?.enableBackNavigation()
        } else {
            mBaseActivity?.disableBackNavigation()
        }
    }

    open fun isBackNavigationEnabled(): Boolean {
        return mBackNavigationEnabled
    }

    open fun onBackPressed() {

    }

    open fun goBack() {
        if (this.mBackNavigationEnabled) {
            return
        }
        mBaseActivity?.onBackPressed()
    }

    open fun showProgress() {
        mBaseActivity?.showProgress()
    }

    open fun hideProgress() {
        mBaseActivity?.hideProgress()
    }

    open fun navigateToHome() {
        mBaseActivity?.navigateToHome()
    }

    open fun displayGeneralErrorMessage(errMessage: String) {
        mBaseActivity?.displayGeneralErrorMessage(errMessage)
    }

    open fun displayGeneralErrorMessage(resId: Int) {
        mBaseActivity?.displayGeneralErrorMessage(resId)
    }
}