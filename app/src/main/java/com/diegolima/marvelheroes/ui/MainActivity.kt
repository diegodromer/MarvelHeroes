package com.diegolima.marvelheroes.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.diegolima.marvelheroes.R
import com.diegolima.marvelheroes.ui.characterlist.CharacterListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MainRouter.goToFragment(
            activity = this@MainActivity,
            fragment = CharacterListFragment()
        )

    }
}