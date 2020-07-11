package com.growd25.weather.utils

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment

fun Fragment.toast(text:CharSequence){
    context?.toast(text)
}
fun Fragment.toast(@StringRes stringResId: Int){
    context?.toast(stringResId)
}

