package com.sa.kotlin_cleanarch.sample.view.base

import android.os.Bundle

public interface OnFragmentListener {
    fun showFragment(bundle: Bundle, tag: String)
}