package altimetrik.android.com.paybank.view.fragment

import altimetrik.android.com.paybank.R
import altimetrik.android.com.paybank.presenter.BasePresenter
import altimetrik.android.com.paybank.view.BaseView
import android.app.ProgressDialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity

/**
 * Created by abhosale on 4/16/2018.
 */
abstract class BaseFragment<P : BasePresenter<BaseView>> : Fragment() {

    protected lateinit var presenter: P
    var dialog: ProgressDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = instantiatePresenter()
        dialog = ProgressDialog(activity)
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