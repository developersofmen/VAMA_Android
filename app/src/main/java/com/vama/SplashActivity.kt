package com.vama

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.vama.ui.composeviews.SplashLogo
import com.vama.ui.theme.VAMAAndroidTheme

class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VAMAAndroidTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    SplashLogo()
                    navigateToAlbumList()
                }
            }
        }
    }

    private fun navigateToAlbumList() {
        super.onStart()
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this@SplashActivity, AlbumListActivity::class.java)
            startActivity(intent)
        }, 1000)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    VAMAAndroidTheme {
        SplashLogo()
    }
}