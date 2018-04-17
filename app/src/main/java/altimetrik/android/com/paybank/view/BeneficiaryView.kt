package altimetrik.android.com.paybank.view

import altimetrik.android.com.paybank.model.AddBeneficiaryResponse
import altimetrik.android.com.paybank.model.Beneficiary

/**
 * Created by abhosale on 4/12/2018.
 */
interface BeneficiaryView : BaseView {

    fun onSuccess(beneficiaryList: ArrayList<Beneficiary>?)
    fun showError(error: String)
    fun onSuccess(beneficiaryList: AddBeneficiaryResponse?)
}