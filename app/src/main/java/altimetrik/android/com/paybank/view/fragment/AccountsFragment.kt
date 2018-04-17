package altimetrik.android.com.paybank.view.fragment

import altimetrik.android.com.paybank.R
import altimetrik.android.com.paybank.adapter.AccountListAdapter
import altimetrik.android.com.paybank.model.Account
import altimetrik.android.com.paybank.model.User
import altimetrik.android.com.paybank.presenter.AccountPresenter
import altimetrik.android.com.paybank.utils.PreferenceHelper
import altimetrik.android.com.paybank.utils.PreferenceHelper.get
import altimetrik.android.com.paybank.view.AccountView
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_accounts.*

/**
 * Created by abhosale on 4/16/2018.
 */
class AccountsFragment : BaseFragment<AccountPresenter>(), AccountView {

    var accountListAdapter: AccountListAdapter? = null

    override fun instantiatePresenter(): AccountPresenter {
        return AccountPresenter(this)
    }


    companion object {
        fun newInstance(): AccountsFragment {
            return AccountsFragment()
        }
    }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_accounts, container, false)


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        rvAccountList.layoutManager = LinearLayoutManager(activity)
        showProgress()
        var prefs = PreferenceHelper.defaultPrefs(activity);
        var userJson: String? = prefs["user"]
        var user = Gson().fromJson(userJson, User::class.java)

        presenter.fetchAccounts(user.customerid, user.sessionKey)

    }

    override fun showError(error: String) {
    }

    override fun onSuccess(accounts: ArrayList<Account>) {
        hideProgress()
        accountListAdapter = AccountListAdapter(accounts)
        rvAccountList.adapter = accountListAdapter
    }

}