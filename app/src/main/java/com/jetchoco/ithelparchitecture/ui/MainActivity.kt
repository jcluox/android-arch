package com.jetchoco.ithelparchitecture.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jetchoco.ithelparchitecture.R
import dagger.android.AndroidInjection

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tag = RepoFragment.TAG
        if (supportFragmentManager.findFragmentByTag(tag) == null) {
            val fragment: RepoFragment = RepoFragment.newInstance()
            supportFragmentManager
                .beginTransaction()
                .add(R.id.container, fragment, tag)
                .commit()
        }
    }
}
