package altimetrik.android.com.paybank.model

import java.io.Serializable

/**
 * Created by abhosale on 4/12/2018.
 */
class User : Serializable {

    var loginid: String = "";
    var password: String = "";
    var customerid: String = "";
    var logintype: String = "";
    var emailId: String = "";
    var secretKey: String = "";
    var sessionKey: String = "";
    var links: Links? = null;


}