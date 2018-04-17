package altimetrik.android.com.paybank.view.fragment

import altimetrik.android.com.paybank.R
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by abhosale on 4/16/2018.
 */
class TransferFragment : Fragment() {

    companion object {
        fun newInstance(): TransferFragment {
            return TransferFragment()
        }
    }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_accounts, container, false)


    }
}