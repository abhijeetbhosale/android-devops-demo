package altimetrik.android.com.paybank.adapter.viewholder

import altimetrik.android.com.paybank.model.Account
import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.accounts_list_row_item.view.*

/**
 * Created by abhosale on 4/16/2018.
 */
class AccountListViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    fun bindItems(account: Account) = with(itemView) {

        itemView.txtAccountNumber.text = account.accountid.toString();
        itemView.txtAccountType.text = account.accounttype;
        itemView.txtAmount.text = account.amount.toString();
    }


}