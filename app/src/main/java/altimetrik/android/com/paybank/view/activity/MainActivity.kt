package altimetrik.android.com.paybank.view.activity

import altimetrik.android.com.paybank.R
import altimetrik.android.com.paybank.model.User
import altimetrik.android.com.paybank.utils.PreferenceHelper
import altimetrik.android.com.paybank.utils.PreferenceHelper.get
import altimetrik.android.com.paybank.view.fragment.AccountsFragment
import altimetrik.android.com.paybank.view.fragment.AddBeneficiaryFragment
import altimetrik.android.com.paybank.view.fragment.BeneficiaryFragment
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.nav_header_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)


        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
        initNavigationDrawer();
    }

    private fun initNavigationDrawer() {

        var prefs = PreferenceHelper.defaultPrefs(this);
        var userJson: String? = prefs["user"]
        var user = Gson().fromJson(userJson, User::class.java)
/*
        txtEmail.text = user.emailId
        txtTitle.text = user.loginid*/
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_accounts -> {
                supportFragmentManager
                        .beginTransaction()
                        .add(R.id.container, AccountsFragment.newInstance(), "AccountsFragment")
                        .commit()
            }
            R.id.nav_add_beneficiary -> {
                supportFragmentManager
                        .beginTransaction()
                        .add(R.id.container, BeneficiaryFragment.newInstance(), "BeneficiaryFragment")
                        .commit()
            }
            R.id.nav_transfer -> {

            }
            R.id.nav_statement -> {

            }
            R.id.nav_logout -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
