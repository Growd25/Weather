package com.growd25.weather.utils

import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction


fun AppCompatActivity.addFragment(@IdRes containerId: Int, fragment: Fragment) {
    supportFragmentManager.transact {
        add(containerId, fragment)
    }
}

fun AppCompatActivity.replaceFragment(
    @IdRes containerId: Int,
    fragment: Fragment,
    addToBackStack: Boolean = true
) {
    supportFragmentManager.transact {
        if (addToBackStack) {
            addToBackStack(null)
        }
        replace(containerId, fragment)
    }
}


private inline fun FragmentManager.transact(action: FragmentTransaction.() -> Unit) {
    beginTransaction().apply(action).commit()
}
