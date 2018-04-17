package altimetrik.android.com.paybank.network

import altimetrik.android.com.paybank.model.*
import altimetrik.android.com.paybank.utils.Constants
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.http.*


/**
 * Created by abhosale on 4/12/2018.
 */
interface PayBankApi {

    @GET("https://pay-bank-login.cfapps.io/pay-bank/api/v1/login/")
    fun doLogin(@Query("password") password: String, @Query("userName") username: String): Observable<User>

    //Beneficiary list
    @GET("https://pay-bank-beneficiary.cfapps.io/pay-bank/api/v1/banks/1/branches/1/customers/{customerId}/beneficiaries")
    fun fetchBeneficiaryList(@Path("customerId") customerId: String, @Query("sessionKey") sessionKey: String): Observable<ArrayList<Beneficiary>>

    //Account list
    @GET("https://pay-bank-transaction.cfapps.io/pay-bank/api/v1/banks/1/branches/1/customers/{customerId}/accounts")
    fun fetchAccounts(@Path("customerId") customerId: String, @Query("sessionKey") sessionKey: String): Observable<ArrayList<Account>>

    //Statement
    @GET("https://pay-bank-transaction.cfapps.io/pay-bank/api/v1/banks/1/branches/1/customers/{customerId}/accounts/{accountId}/transactions/")
    fun fetchStatement(@Path("customerId") customerId: String, @Path("accountId") accountId: String): Observable<User>


    @POST("https://pay-bank-beneficiary.cfapps.io/pay-bank/api/v1/banks/1/branches/1/customers/{customerId}/beneficiaries")
    fun addBeneficiary(@Path("customerId") customerId: String, @Body addBeneficiaryRequest: AddBeneficiaryRequest): Observable<AddBeneficiaryResponse>

    companion object Factory {
        fun create(): PayBankApi {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            val httpClient = OkHttpClient.Builder().addInterceptor(logging).build()
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(Constants.BASE_URL)
                    .client(httpClient)
                    .build()

            return retrofit.create(PayBankApi::class.java);
        }
    }

}