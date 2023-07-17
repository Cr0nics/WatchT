package com.example.watcht.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.*
import com.example.watcht.R
import com.example.watcht.databinding.ActivityMainBinding
import com.example.watcht.ui.view.PopularMovies.MovieListFragment
import com.example.watcht.ui.view.menuDetails.about.AboutMeFragment
import com.example.watcht.ui.view.menuDetails.contact.ContactMeFragment
import com.example.watcht.ui.view.menuDetails.settings.SettingsFragment
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navigationView: NavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.nav_view)

        navController = this.findNavController(R.id.fragment_container)
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.myListFragment,
                R.id.movieListFragment,
                R.id.settingsFragment,
                R.id.contactMeFragment,
                R.id.aboutMeFragment
            )
        )
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
        NavigationUI.setupWithNavController(navigationView, navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.fragment_container)
        return NavigationUI.navigateUp(navController, drawerLayout)
    }

}