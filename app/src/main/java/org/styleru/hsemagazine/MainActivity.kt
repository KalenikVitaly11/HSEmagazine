package org.styleru.hsemagazine

import android.app.FragmentManager
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.SwitchCompat
import android.util.Log
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.custom_action_bar.*
import org.styleru.hsemagazine.fragments.*
import java.util.*
import com.crashlytics.android.Crashlytics
import io.fabric.sdk.android.Fabric




class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var languageSwitch:SwitchCompat


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Fabric.with(this, Crashlytics())
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
        loadCustomActionBar()
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, JournalFragment()).commit()

        languageSwitch = nav_view.menu.getItem(8).actionView as SwitchCompat
        val res = resources
        val conf = res.configuration
        languageSwitch.setOnClickListener {
            switchLanguage()
        }

        if(conf.locale.language == "en")
            languageSwitch.isChecked = true
        else
            languageSwitch.isChecked = false


    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        when (item.itemId) {
            R.id.nav_journal -> {
                supportFragmentManager.beginTransaction().replace(R.id.fragment_container, JournalFragment()).commit()
            }
            R.id.nav_collegium -> {
                supportFragmentManager.beginTransaction().replace(R.id.fragment_container, CollegiumFragment()).commit()
            }
            R.id.nav_releases -> {
                supportFragmentManager.beginTransaction().replace(R.id.fragment_container, PrevReleasesFragment()).commit()
            }
            R.id.nav_for_authors -> {
                supportFragmentManager.beginTransaction().replace(R.id.fragment_container, ForAuthorsFragment()).commit()
            }
            R.id.nav_reviews -> {
                supportFragmentManager.beginTransaction().replace(R.id.fragment_container, ReviewsFragment()).commit()
            }
            R.id.nav_ethics -> {
                supportFragmentManager.beginTransaction().replace(R.id.fragment_container, EthicsFragment()).commit()
            }
            R.id.nav_subscription -> {
                supportFragmentManager.beginTransaction().replace(R.id.fragment_container, SubscriptionFragment()).commit()
            }
            R.id.nav_contacts -> {
                supportFragmentManager.beginTransaction().replace(R.id.fragment_container, ContactsFragment()).commit()
            }
            R.id.change_language -> {
                Log.d("myLogs", "Clicked")
                switchLanguage()
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun loadCustomActionBar(){
        supportActionBar!!.apply {
            elevation = 12f
            setDisplayShowCustomEnabled(true)
            setDisplayShowTitleEnabled(false)
            setCustomView(R.layout.custom_action_bar)
        }
        setCustomTitle(resources.getString(R.string.journal))
    }

    fun switchLanguage(){
        val res = resources
        val conf = res.configuration
        // Change locale settings in the app.
        val dm = res.displayMetrics
        Log.d("myLogs", conf.locale.language)
        if(conf.locale.language == "en")
            conf.setLocale(Locale("ru")) // API 17+ only.
        else
            conf.setLocale(Locale("en")) // API 17+ only.
        // Use conf.locale = new Locale(...) if targeting lower versions
        res.updateConfiguration(conf, dm)
        recreate()
        nav_view.menu.getItem(0).isChecked = true

        if(conf.locale.language == "en")
            languageSwitch.isChecked = true
        else
            languageSwitch.isChecked = false
    }

    fun setCustomTitle(text:String){
        actionBarText.text = text
    }
}
