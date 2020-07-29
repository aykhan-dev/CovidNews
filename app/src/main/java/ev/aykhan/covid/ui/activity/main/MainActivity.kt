package ev.aykhan.covid.ui.activity.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import ev.aykhan.covid.R
import ev.aykhan.covid.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val navController by lazy { findNavController(R.id.nav_host_fragment) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        configureToolbar()
        configureBottomNavigationView()
    }

    private fun configureToolbar(): Unit = with(binding) {
        toolbar.setupWithNavController(navController)
    }

    private fun configureBottomNavigationView(): Unit = with(binding) {
        bottomNavigationView.setOnNavigationItemReselectedListener { } //DO NOTHING
        bottomNavigationView.setupWithNavController(navController)
    }

}