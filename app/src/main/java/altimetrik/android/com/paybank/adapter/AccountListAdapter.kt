package altimetrik.android.com.paybank.adapter

import altimetrik.android.com.paybank.R
import altimetrik.android.com.paybank.adapter.viewholder.AccountListViewHolder
import altimetrik.android.com.paybank.model.Account
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

/**
 * Created by abhosale on 4/16/2018.
 */
class AccountListAdapter(private val accounts: ArrayList<Account>) : RecyclerView.Adapter<AccountListViewHolder>() {

    override fun onBindViewHolder(holder: AccountListViewHolder?, position: Int) {
        holder?.bindItems(accounts[position])
    }

    override fun getItemCount(): Int {
        return accounts.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): AccountListViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.accounts_list_row_item, parent, false)
        return AccountListViewHolder(view)
    }
}