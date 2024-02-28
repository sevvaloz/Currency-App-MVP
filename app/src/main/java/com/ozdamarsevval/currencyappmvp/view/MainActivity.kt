package com.ozdamarsevval.currencyappmvp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ozdamarsevval.currencyappmvp.R
import com.ozdamarsevval.currencyappmvp.view.home.HomeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val homeFragment = HomeFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.constraintlayout, homeFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}