package altimetrik.android.com.paybank.view

import altimetrik.android.com.paybank.model.User

/**
 * Created by abhosale on 4/12/2018.
 */
interface LoginView : BaseView {

    fun onSuccess(user: User)
    fun showError(error: String)
    fun emptyUsername()
    fun emptyPassword()
    fun passwordLengthInvalid()
}