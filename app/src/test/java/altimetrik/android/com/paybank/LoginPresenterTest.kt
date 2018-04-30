package altimetrik.android.com.paybank

import altimetrik.android.com.paybank.model.User
import altimetrik.android.com.paybank.presenter.LoginPresenter
import altimetrik.android.com.paybank.view.LoginView
import org.junit.Before
import org.junit.Test

class LoginPresenterTest : LoginView {

    override fun passwordLengthInvalid() {
        assert(true)
    }

    override fun emptyPassword() {
    }

    override fun emptyUsername() {
    }

    override fun showError(error: String) {
    }

    override fun onSuccess(user: User) {
    }


    lateinit var loginPresenter: LoginPresenter


    @Before
    fun setUp(){

        loginPresenter=LoginPresenter(this);

    }

    @Test
    fun invalidPasswordLengthValid() {
       loginPresenter.validatePasswordLength("abhi")
    }


    @Test
    fun validPasswordLengthValid() {
        assert( loginPresenter.validatePasswordLength("abhijeet"))
    }
}