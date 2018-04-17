package altimetrik.android.com.paybank.view.activity

import altimetrik.android.com.paybank.R
import altimetrik.android.com.paybank.model.User
import altimetrik.android.com.paybank.presenter.LoginPresenter
import altimetrik.android.com.paybank.utils.PreferenceHelper
import altimetrik.android.com.paybank.utils.PreferenceHelper.set
import altimetrik.android.com.paybank.view.LoginView
import android.content.Intent
import android.os.Bundle
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_login.*

/**
 * Created by abhosale on 4/12/2018.
 */
class LoginActivity : BaseActivity<LoginPresenter>(), LoginView {
    override fun emptyPassword() {

        edtPassword.error="Please enter password"
    }

    override fun emptyUsername() {
        edtUsername.error="Please enter username"
    }

    override fun passwordLengthInvalid() {
        edtUsername.error="Password length must be more than 6 characters"
    }

    override fun instantiatePresenter(): LoginPresenter {
        return LoginPresenter(this);
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnSignIn.setOnClickListener {

            if (presenter.validateInput(edtPassword.text.toString(), edtUsername.text.toString())) {
                showProgress()
                presenter.doLogin(edtPassword.text.toString(), edtUsername.text.toString())
            }
        }


    }

    private fun openHomeScreen() {

        startActivity(Intent(this, MainActivity::class.java))
        finish()

    }

    override fun showError(error: String) {
        hideProgress()
    }

    override fun onSuccess(user: User) {
        hideProgress()
        val prefs = PreferenceHelper.defaultPrefs(this)
        prefs["user"] = Gson().toJson(user)
        openHomeScreen()


    }


}
