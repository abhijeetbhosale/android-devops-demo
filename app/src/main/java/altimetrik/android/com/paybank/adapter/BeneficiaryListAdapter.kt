package altimetrik.android.com.paybank.adapter

import altimetrik.android.com.paybank.R
import altimetrik.android.com.paybank.adapter.viewholder.BeneficiaryListViewHolder
import altimetrik.android.com.paybank.model.Beneficiary
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

/**
 * Created by abhosale on 4/16/2018.
 */
class BeneficiaryListAdapter(private val beneficiaryList: ArrayList<Beneficiary>?) : RecyclerView.Adapter<BeneficiaryListViewHolder>() {

    override fun onBindViewHolder(holder: BeneficiaryListViewHolder?, position: Int) {
        holder?.bindItems(beneficiaryList?.get(position))
    }

    override fun getItemCount(): Int {
        return beneficiaryList?.size!!
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BeneficiaryListViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.beneficiary_list_row_item, parent, false)
        return BeneficiaryListViewHolder(view)
    }
}