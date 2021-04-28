package com.gustavohisan.apelieuser.register.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gustavohisan.apelieuser.register.R
import com.gustavohisan.apelieuser.register.databinding.RegisterActivityBinding

/**
 * Register activity.
 */
internal class RegisterActivity : AppCompatActivity() {

    private val binding by lazy { RegisterActivityBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val view = binding.root
        setContentView(view)
        setupToolbar()
    }

    private fun setupToolbar() {

        setSupportActionBar(binding.registerToolbar)
        supportActionBar?.setTitle(R.string.register_toolbar_title)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(true)
    }
}