package altimetrik.android.com.paybank.view.activity

import altimetrik.android.com.paybank.R
import altimetrik.android.com.paybank.presenter.BasePresenter
import altimetrik.android.com.paybank.view.BaseView
import android.app.ProgressDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * Created by abhosale on 4/12/2018.
 */
abstract class BaseActivity<P : BasePresenter<BaseView>> : AppCompatActivity() {

    protected lateinit var presenter: P
    var dialog: ProgressDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = instantiatePresenter()
        dialog = ProgressDialog(this)
    }

    protected abstract fun instantiatePresenter(): P


    fun showProgress() {

        dialog?.setMessage("Please wait")
        dialog?.setTitle(getString(R.string.app_name))
        dialog?.setCancelable(false)
        dialog?.isIndeterminate = true
        dialog?.show()
    }

    fun hideProgress() {
        dialog?.dismiss()
    }
}