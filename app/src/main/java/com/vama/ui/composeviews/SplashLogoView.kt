package com.vama.ui.composeviews

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.vama.R

@Composable
fun SplashLogo() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(150.dp)
            .height(150.dp)
            .background(Color.White)
            .wrapContentHeight()
    ) {
        Image(
            painter = painterResource(id = R.mipmap.vama_logo),
            contentDescription = null,
            modifier = Modifier.clip(RoundedCornerShape(10.dp)),
        )
    }
}