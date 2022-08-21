package com.vama.ui.composeviews

import Results
import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.vama.R
import com.vama.ui.theme.Black
import com.vama.ui.theme.Blue
import com.vama.ui.theme.Gray_1
import com.vama.ui.theme.Gray_2
import com.vama.utils.AppConstant
import com.vama.utils.AppUtils
import com.vama.utils.AppUtils.openInBrowse
import com.vama.utils.FontFamilyUtils
import com.vama.utils.SharedPreferenceHelper

@Composable
fun showDetails(context: Context, results: Results) {
    Column(
        modifier = Modifier
    ) {
        Image(
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxWidth()
                .height(390.dp),
            painter = rememberAsyncImagePainter(results.artworkUrl100),
            contentDescription = null
        )
        Surface(
            modifier = Modifier
                .fillMaxWidth()
        ) {

            Column(
                Modifier
                    .padding(15.dp)
            ) {
                results.artistName?.let { it1 ->
                    Text(
                        text = it1,
                        color = Gray_2,
                        fontSize = 18.sp,
                        fontFamily = FontFamilyUtils.sfProTextFamily,
                        fontWeight = FontWeight.Normal
                    )
                }

                results.name?.let { it1 ->
                    Text(
                        text = it1,
                        color = Black,
                        fontSize = 34.sp,
                        fontFamily = FontFamilyUtils.sfProDisplayFamily,
                        fontWeight = FontWeight.Bold
                    )
                }
                results?.genresName?.let {
                    OutlinedButton(
                        onClick = { },
                        contentPadding = PaddingValues(10.dp, 5.dp),
                        border = BorderStroke(1.dp, Blue),
                        shape = RoundedCornerShape(50),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = Blue),
                        modifier = Modifier
                            .defaultMinSize(minWidth = 1.dp, minHeight = 1.dp)
                    ) {
                        Text(
                            text = it, color = Blue, fontSize = 12.sp,
                            style = TextStyle(
                                fontFamily = FontFamilyUtils.sfProTextFamily,
                                fontWeight = FontWeight.Medium
                            )
                        )
                    }
                }
            }
        }

        Box(Modifier.fillMaxSize()) {

            Surface(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(0.dp, 30.dp)
            ) {

                Column(
                    Modifier
                        .padding(0.dp, 15.dp)
                ) {
                    results.releaseDate?.let { it1 ->
                        Text(
                            text = context.getString(R.string.released) + " " + AppUtils.parseDate(
                                it1
                            ),
                            color = Gray_1,
                            fontSize = 12.sp,
                            fontFamily = FontFamilyUtils.sfProTextFamily,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                    }
                    SharedPreferenceHelper.getStringPreferences(AppConstant.SP_COPYRIGHT)?.let {
                        Text(
                            text = it,
                            color = Gray_1,
                            fontSize = 12.sp,
                            fontFamily = FontFamilyUtils.sfProTextFamily,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                    }

                    Button(
                        onClick = {
                            if (results.url != null) {
                                openInBrowse(context, results.url)
                            }
                        },
                        contentPadding = PaddingValues(10.dp, 5.dp),
                        modifier = Modifier
                            .padding(0.dp, 30.dp, 0.dp, 10.dp)
                            .clip(RoundedCornerShape(15.dp))
                            .align(Alignment.CenterHorizontally),
                        enabled = true,
                        colors = ButtonDefaults.buttonColors(backgroundColor = Blue),
                    ) {
                        Text(
                            modifier = Modifier.padding(8.dp),
                            text = context.getString(R.string.visit_the_album),
                            color = Color.White,
                            fontSize = 16.sp,
                            style = TextStyle(
                                fontFamily = FontFamilyUtils.sfProTextFamily,
                                fontWeight = FontWeight.SemiBold
                            )
                        )
                    }
                }
            }
        }
    }
}