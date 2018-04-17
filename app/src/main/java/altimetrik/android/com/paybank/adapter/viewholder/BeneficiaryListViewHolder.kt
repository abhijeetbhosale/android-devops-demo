package altimetrik.android.com.paybank.adapter.viewholder

import altimetrik.android.com.paybank.model.Beneficiary
import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.beneficiary_list_row_item.view.*

/**
 * Created by abhosale on 4/16/2018.
 */
class BeneficiaryListViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    fun bindItems(beneficiary: Beneficiary?) = with(itemView) {

        itemView.txtBeneficiaryName.text = beneficiary?.nickname.toString();
        itemView.txtAccountNumber.text = beneficiary?.accountid.toString();
    }


}