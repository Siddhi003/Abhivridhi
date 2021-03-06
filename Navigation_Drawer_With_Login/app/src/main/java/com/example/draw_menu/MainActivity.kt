package com.example.draw_menu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener{
    lateinit var homeFragment: HomeFragment
    lateinit var loginFragment: Login
    lateinit var donationFragment: DonationFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open, R.string.close)
        toggle.isDrawerIndicatorEnabled = true
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        nav_menu.setNavigationItemSelectedListener(this)
        changeFragment(HomeFragment())
        /*homeFragment = HomeFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, homeFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit() */
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        drawerLayout.closeDrawer(GravityCompat.START)
        /*if (item.itemId == R.id.home) {
            changeFragment(HomeFragment())
        }

        if (item.itemId == R.id.login) {
            loginFragment = Login()
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, loginFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit()
        } */
        when(item.itemId) {
            R.id.home -> {
                changeFragment(HomeFragment())
            }
            R.id.login -> {
                changeFragment(Login())
            }
            R.id.collaboration -> {
                changeFragment(Collaboration_Fragment())
            }
            R.id.donation -> {
                changeFragment(DonationFragment())
            }
            R.id.fundraiser -> {
                val intent = Intent(this, ScrollingActivity::class.java)
                startActivity(intent)
            }
        }
        return true
    }

    fun setToolbarTitle(title: String){
        supportActionBar?.title = title
    }

    fun changeFragment(frag: Fragment) {
        val fragment = supportFragmentManager.beginTransaction()
        fragment.replace(R.id.fragment_container,frag).commit()
    }

}


