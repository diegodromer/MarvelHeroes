package com.diegolima.marvelheroes.ui

import androidx.fragment.app.Fragment
import com.diegolima.marvelheroes.R

object MainRouter {

    fun goToFragment(activity: MainActivity, fragment: Fragment){
        activity.supportFragmentManager
            .beginTransaction()
            .replace(R.id.mainContainer, fragment)
            .commit()
    }

}