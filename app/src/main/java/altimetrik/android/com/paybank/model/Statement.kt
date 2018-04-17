package altimetrik.android.com.paybank.model

/**
 * Created by abhosale on 4/16/2018.
 */
class Statement {

    var transactionid: Long = 0
    var cust_accountid: Long = 0
    var benf_accountid: Long = 0
    var amount: Double = 0.toDouble()
    var description: String? =null
    var transactiontype: String? =null
    var time_stamp: Long = 0

}