package com.jetchoco.ithelparchitecture.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.jetchoco.ithelparchitecture.R
import com.jetchoco.ithelparchitecture.api.GithubService
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }

    // Dagger test
    @Inject
    lateinit var githubService: GithubService

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tag= RepoFragment.TAG
        if (supportFragmentManager.findFragmentByTag(tag) == null) {
            val fragment: RepoFragment = RepoFragment.newInstance()
            supportFragmentManager.beginTransaction()
                .add(R.id.container, fragment, tag)
                .commit()
        }

        // Dagger test
        if (githubService != null) {
            Log.d(TAG, "Hello dagger!")
        }
    }
}
