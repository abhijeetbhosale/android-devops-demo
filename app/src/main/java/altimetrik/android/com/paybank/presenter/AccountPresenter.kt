package altimetrik.android.com.paybank.presenter

import altimetrik.android.com.paybank.network.PayBankApi
import altimetrik.android.com.paybank.view.AccountView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by abhosale on 4/16/2018.
 */
class AccountPresenter(view: AccountView) : BasePresenter<AccountView>(view) {


    fun fetchAccounts(customerId: String, sessionKey: String) {
        val apiService = PayBankApi.create()
        apiService.fetchAccounts(customerId, sessionKey).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).doOnTerminate { }.subscribe(
                        { accounts ->
                            view.onSuccess(accounts)
                        },
                        {
                            view.showError("Accounts list fetching failed")

                        })

    }
}