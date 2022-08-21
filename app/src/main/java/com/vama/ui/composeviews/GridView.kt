package com.sofmen.composedemo

import Results
import android.content.Context
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.FragmentManager
import coil.compose.rememberAsyncImagePainter
import com.vama.R
import com.vama.ui.main.details.AlbumDetailsFragment
import com.vama.ui.theme.*
import com.vama.utils.AppUtils
import com.vama.utils.FontFamilyUtils

@Composable
fun gridViewComponent(albumsList: List<Results>, fragmentManager: FragmentManager) {
    VAMAAndroidTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.White
        ) {
            Column {
                gridView(LocalContext.current, albumsList, fragmentManager)
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
fun gridView(context: Context, albumsList: List<Results>, fragmentManager: FragmentManager) {
    Text(
        text = context.getString(R.string.top_albums),
        modifier = Modifier.padding(15.dp, 15.dp, 10.dp, 0.dp),
        style = TextStyle(
            color = Black,
        ), fontFamily = FontFamilyUtils.sfProDisplayFamily, fontWeight = FontWeight.Bold,
        fontSize = 34.sp
    )
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        LazyVerticalGrid(
            cells = GridCells.Fixed(2),
            modifier = Modifier.padding(10.dp)
        ) {
            items(albumsList.size) {
                Column(modifier = Modifier
                    .padding(5.dp)
                    .clickable {
                        var results = albumsList[it]
                        navigateToDetailsScreen(results, fragmentManager)
                    }) {
                    Box() {
                        Image(
                            painter = rememberAsyncImagePainter(albumsList[it].artworkUrl100),
                            contentDescription = null,
                            modifier = Modifier
                                .height(AppUtils.getWidth(context).dp)
                                .width(AppUtils.getWidth(context).dp)
                                .clip(RoundedCornerShape(15.dp))
                        )
                        Surface(
                            color = Color.Transparent,
                            modifier = Modifier
                                .height(AppUtils.getWidth(context).dp)
                                .width(AppUtils.getWidth(context).dp)
                                .clip(RoundedCornerShape(15.dp))
                                .background(
                                    brush = Brush.verticalGradient(
                                        colors = listOf(
                                            DarkGraySemiTrans_1,
                                            DarkGraySemiTrans_2
                                        )
                                    )
                                )
                                .align(BottomStart)
                        ) {
                            Column(
                                verticalArrangement = Arrangement.Bottom,
                                horizontalAlignment = Alignment.Start,
                                modifier = Modifier
                                    .padding(15.dp, 0.dp, 0.dp, 15.dp)
                                    .background(Color.Transparent)
                            ) {
                                albumsList[it].name?.let { it1 ->
                                    Text(
                                        text = it1,
                                        color = Color.White,
                                        fontSize = 16.sp,
                                        fontFamily = FontFamilyUtils.sfProTextFamily,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                }
                                albumsList[it].artistName?.let { it1 ->
                                    Text(
                                        text = it1,
                                        color = Gray_1,
                                        fontSize = 12.sp,
                                        fontFamily = FontFamilyUtils.sfProTextFamily,
                                        fontWeight = FontWeight.Medium,
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

private fun navigateToDetailsScreen(results: Results, fragmentManager: FragmentManager) {
    var fragment = AlbumDetailsFragment.newInstance(results)
    var fragmentTransaction = fragmentManager.beginTransaction()
    fragmentTransaction.replace(
        R.id.container,
        fragment,
        fragment.javaClass.simpleName
    )
    fragmentTransaction.addToBackStack(fragment.javaClass.simpleName)
    fragmentTransaction.commit()
}