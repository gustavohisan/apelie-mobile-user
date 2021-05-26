package com.gustavohisan.apelieuser.main.presentation

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import com.gustavohisan.apelieuser.main.R
import com.gustavohisan.apelieuser.main.databinding.MainActivityBinding
import kotlinx.android.synthetic.main.main_activity.view.*

class MainActivity : AppCompatActivity() {

    private val binding: MainActivityBinding by lazy {
        MainActivityBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)
        binding.bottomNavigation.menu.add(Menu.NONE, 1, Menu.NONE, getString(R.string.home)).setIcon(R.drawable.ic_home)

//        binding.bottomNavigation.setOnNavigationItemSelectedListener {
//            when (it.itemId) {
//                1 -> binding.navHostFragment.
//            }
//        }
    }
}
