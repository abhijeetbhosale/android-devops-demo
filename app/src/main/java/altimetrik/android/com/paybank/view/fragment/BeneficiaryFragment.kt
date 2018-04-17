package altimetrik.android.com.paybank.view.fragment

import altimetrik.android.com.paybank.R
import altimetrik.android.com.paybank.adapter.BeneficiaryListAdapter
import altimetrik.android.com.paybank.model.AddBeneficiaryResponse
import altimetrik.android.com.paybank.model.Beneficiary
import altimetrik.android.com.paybank.model.User
import altimetrik.android.com.paybank.presenter.BeneficiaryPresenter
import altimetrik.android.com.paybank.utils.PreferenceHelper
import altimetrik.android.com.paybank.utils.PreferenceHelper.get
import altimetrik.android.com.paybank.view.BeneficiaryView
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_beneficiary_list.*

/**
 * Created by abhosale on 4/16/2018.
 */
class BeneficiaryFragment : BaseFragment<BeneficiaryPresenter>(), BeneficiaryView {


    var beneficiaryListAdapter: BeneficiaryListAdapter? = null

    override fun instantiatePresenter(): BeneficiaryPresenter {
        return BeneficiaryPresenter(this)
    }


    companion object {
        fun newInstance(): BeneficiaryFragment {
            return BeneficiaryFragment()
        }
    }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_beneficiary_list, container, false)


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        rvBeneficiaryList.layoutManager = LinearLayoutManager(activity)
        var prefs = PreferenceHelper.defaultPrefs(activity);
        var userJson: String? = prefs["user"]
        var user = Gson().fromJson(userJson, User::class.java)

        showProgress()
        presenter.fetchBeneficiaryList(user.customerid, user.sessionKey)

        btnAdd.setOnClickListener {
            activity.supportFragmentManager.beginTransaction()
                    .replace(R.id.container, AddBeneficiaryFragment.newInstance(), "AddBeneficiaryFragment").addToBackStack("AddBeneficiaryFragment")
                    .commit()
        }

    }

    override fun showError(error: String) {
    }

    override fun onSuccess(beneficiaryList: ArrayList<Beneficiary>?) {
        hideProgress()
        beneficiaryListAdapter = BeneficiaryListAdapter(beneficiaryList)
        rvBeneficiaryList.adapter = beneficiaryListAdapter
    }

    override fun onSuccess(beneficiaryList: AddBeneficiaryResponse?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}