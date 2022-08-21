package com.vama.ui.composeviews

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vama.R
import com.vama.ui.main.list.AlbumListModel
import com.vama.ui.main.list.AlbumsListFragment
import com.vama.ui.theme.Blue
import com.vama.utils.FontFamilyUtils

@Composable
fun showEmptyView(
    context: Context,
    message: String,
    viewModel: AlbumListModel,
    fragment: AlbumsListFragment
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(150.dp)
            .height(150.dp)
            .wrapContentHeight()
    ) {
        Text(
            text = message,
            fontSize = 20.sp,
            fontFamily = FontFamilyUtils.sfProTextFamily, fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center
        )
        Button(
            onClick = {
                fragment.checkInternetAndDisplayUI()
                viewModel.getAlbum()
            },
            modifier = Modifier.padding(all = Dp(10F)),
            enabled = true,
            colors = ButtonDefaults.buttonColors(backgroundColor = Blue),
            shape = MaterialTheme.shapes.medium
        ) {
            Text(
                text = context.getString(R.string.retry), color = Color.White,
                fontSize = 15.sp,
                fontFamily = FontFamilyUtils.sfProTextFamily,
                fontWeight = FontWeight.SemiBold,
                style = TextStyle(
                    fontFamily = FontFamilyUtils.sfProTextFamily,
                    fontWeight = FontWeight.SemiBold
                )
            )
        }
    }
}