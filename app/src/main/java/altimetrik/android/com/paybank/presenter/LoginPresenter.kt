package altimetrik.android.com.paybank.presenter

import altimetrik.android.com.paybank.network.PayBankApi
import altimetrik.android.com.paybank.view.LoginView
import android.text.TextUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by abhosale on 4/12/2018.
 */
class LoginPresenter(view: LoginView) : BasePresenter<LoginView>(view) {


    fun validateInput(password: String, username: String): Boolean {
        if (TextUtils.isEmpty(username)) {
            view.emptyUsername()
            return false;
        }

        if (TextUtils.isEmpty(password)) {
            view.emptyPassword()
            return false
        }

        return validatePasswordLength(password)


    }


    fun validatePasswordLength(password: String): Boolean {
        if (password.length <=  6) {
            view.passwordLengthInvalid()
            return false
        }
        return true
    }

    fun doLogin(password: String, username: String) {

        val apiService = PayBankApi.create()
        apiService.doLogin(password, username).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).doOnTerminate { }.subscribe(
                        { user ->
                            view.onSuccess(user)
                        },
                        {
                            view.showError("Login failed")

                        })

    }
}