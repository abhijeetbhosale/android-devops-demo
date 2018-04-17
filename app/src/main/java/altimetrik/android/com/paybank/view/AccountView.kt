package altimetrik.android.com.paybank.view

import altimetrik.android.com.paybank.model.Account

/**
 * Created by abhosale on 4/12/2018.
 */
interface AccountView : BaseView {

    fun onSuccess(accounts: ArrayList<Account>)
    fun showError(error: String)
}