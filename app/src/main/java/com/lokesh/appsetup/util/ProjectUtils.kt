package com.lokesh.appsetup.util

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.View.*
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.lifecycle.MutableLiveData
import com.lokesh.appsetup.application.App

val globalLiveInternetCheck: MutableLiveData<Boolean> = MutableLiveData(false)

fun getStringResource(@StringRes res: Int) = App.getAppContext().getString(res)

fun Context.showToast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

fun Activity.hideKeyboard() {
    this.currentFocus?.let { view ->
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(view.windowToken, 0)
    }
}

fun View.goneView() {
    visibility = GONE
}

fun View.hideView() {
    visibility = INVISIBLE
}

fun View.showView() {
    visibility = VISIBLE
}

infix fun View.onClick(click: () -> Unit) {
    setOnClickListener { click() }
}
