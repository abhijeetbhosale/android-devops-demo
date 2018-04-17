package altimetrik.android.com.paybank.view.fragment

import altimetrik.android.com.paybank.R
import altimetrik.android.com.paybank.adapter.AccountListAdapter
import altimetrik.android.com.paybank.model.AddBeneficiaryRequest
import altimetrik.android.com.paybank.model.AddBeneficiaryResponse
import altimetrik.android.com.paybank.model.Beneficiary
import altimetrik.android.com.paybank.model.User
import altimetrik.android.com.paybank.presenter.BeneficiaryPresenter
import altimetrik.android.com.paybank.utils.PreferenceHelper
import altimetrik.android.com.paybank.view.BeneficiaryView
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_add_beneficiary.*
import altimetrik.android.com.paybank.utils.PreferenceHelper.get
import android.widget.Toast
import com.google.gson.Gson

/**
 * Created by abhosale on 4/16/2018.
 */
class AddBeneficiaryFragment : BaseFragment<BeneficiaryPresenter>(), BeneficiaryView {


    override fun instantiatePresenter(): BeneficiaryPresenter {
        return BeneficiaryPresenter(this)
    }


    companion object {
        fun newInstance(): AddBeneficiaryFragment {
            return AddBeneficiaryFragment()
        }
    }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_add_beneficiary, container, false)


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        btnSubmit.setOnClickListener {

            showProgress()
            var prefs = PreferenceHelper.defaultPrefs(activity);
            var userJson: String? = prefs["user"]
            var user = Gson().fromJson(userJson, User::class.java)

            var addBeneficiaryRequest = AddBeneficiaryRequest()
            addBeneficiaryRequest.accountid = edtAccountNumber.text.toString().toLong()
            addBeneficiaryRequest.nickname = edtAccountHolderName.text.toString()
            presenter.addBeneficiary(user.customerid, addBeneficiaryRequest)

        }

    }

    override fun showError(error: String) {
    }

    override fun onSuccess(accounts: ArrayList<Beneficiary>?) {
    }

    override fun onSuccess(beneficiaryList: AddBeneficiaryResponse?) {
        Toast.makeText(activity, "Added", Toast.LENGTH_SHORT).show()
        hideProgress()

        edtAccountHolderName.setText("")
        edtAccountNumber.setText("")



    }

}