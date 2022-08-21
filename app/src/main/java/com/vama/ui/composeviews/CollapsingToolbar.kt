package com.vama.ui.composeviews

import android.content.Context
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import com.vama.R
import com.vama.ui.theme.Black
import com.vama.utils.FontFamilyUtils

@Composable
fun CollapsingToolbar(
    context: Context, scrollOffset: Float,
) {
    val imageSize by animateDpAsState(targetValue = max(16.dp, 34.dp * scrollOffset))
    val imageSizeI: Float = max(16.dp, imageSize).value
    Row {
        Text(
            text = context.getString(R.string.top_albums),
            modifier = Modifier.padding(15.dp, 15.dp, 10.dp, 0.dp),
            style = TextStyle(
                color = Black,
            ),
            fontFamily = FontFamilyUtils.sfProDisplayFamily, fontWeight = FontWeight.Bold,
            fontSize = imageSizeI.sp,
        )
    }
}