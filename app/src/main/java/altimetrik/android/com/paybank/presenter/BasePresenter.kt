package altimetrik.android.com.paybank.presenter

import altimetrik.android.com.paybank.view.BaseView

/**
 * Created by abhosale on 4/12/2018.
 */
abstract class BasePresenter<out V : BaseView>(protected val view: V) {


}