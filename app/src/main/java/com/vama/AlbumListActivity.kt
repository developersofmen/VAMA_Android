package com.vama

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vama.ui.main.list.AlbumsListFragment

class AlbumListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_albumlist)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, AlbumsListFragment.newInstance())
                .commitNow()
        }
    }
}