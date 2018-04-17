package altimetrik.android.com.paybank.presenter

import altimetrik.android.com.paybank.model.AddBeneficiaryRequest
import altimetrik.android.com.paybank.network.PayBankApi
import altimetrik.android.com.paybank.view.BeneficiaryView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by abhosale on 4/16/2018.
 */
class BeneficiaryPresenter(view: BeneficiaryView) : BasePresenter<BeneficiaryView>(view) {


    fun fetchBeneficiaryList(customerId: String, sessionKey: String) {
        val apiService = PayBankApi.create()
        apiService.fetchBeneficiaryList(customerId, sessionKey).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).doOnTerminate { }.subscribe(
                        { beneficiaryList ->
                            view.onSuccess(beneficiaryList)
                        },
                        {
                            view.showError("Beneficiary list fetching failed")

                        })

    }

    fun addBeneficiary(customerId: String, addBeneficiaryRequest: AddBeneficiaryRequest) {

        val apiService = PayBankApi.create()
        apiService.addBeneficiary(customerId, addBeneficiaryRequest).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).doOnTerminate { }.subscribe(
                        { beneficiary ->
                            view.onSuccess(beneficiary)
                        },
                        {
                            view.showError("Add Beneficiary failed")

                        })

    }
}