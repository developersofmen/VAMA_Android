package com.vama.ui.composeviews

import Results
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.FragmentManager
import coil.compose.rememberAsyncImagePainter
import com.vama.R
import com.vama.ui.main.details.AlbumDetailsFragment
import com.vama.ui.theme.DarkGraySemiTrans_1
import com.vama.ui.theme.DarkGraySemiTrans_2
import com.vama.ui.theme.Gray_1
import com.vama.utils.AppUtils
import com.vama.utils.FontFamilyUtils
import kotlin.math.min

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun gridView(albumsList: List<Results>, fragmentManager: FragmentManager) {
    val context = LocalContext.current
    val scrollState = rememberLazyListState()
    val scrollOffset: Float = min(
        1f,
        1 - (scrollState.firstVisibleItemScrollOffset / 600f + scrollState.firstVisibleItemIndex)
    )
    Surface(color = MaterialTheme.colors.background) {
        Column {
            Surface {
                CollapsingToolbar(context, scrollOffset)
            }
            Spacer(modifier = Modifier.height(2.dp))
            LazyVerticalGrid(
                cells = GridCells.Fixed(2),
                modifier = Modifier.padding(10.dp),
                state = scrollState,
                contentPadding = PaddingValues(bottom = 16.dp)
            ) {
                items(albumsList) { item ->
                    Column(modifier = Modifier
                        .padding(5.dp)
                        .clickable {
                            var results = item
                            navigateToDetailsScreen(results, fragmentManager)
                        }) {
                        Box {
                            Image(
                                painter = rememberAsyncImagePainter(item.artworkUrl100),
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
                                    .align(Alignment.BottomStart)
                            ) {
                                Column(
                                    verticalArrangement = Arrangement.Bottom,
                                    horizontalAlignment = Alignment.Start,
                                    modifier = Modifier
                                        .padding(15.dp, 0.dp, 0.dp, 15.dp)
                                        .background(Color.Transparent)
                                ) {
                                    item.name?.let { it1 ->
                                        Text(
                                            text = it1,
                                            color = Color.White,
                                            fontSize = 16.sp,
                                            fontFamily = FontFamilyUtils.sfProTextFamily,
                                            fontWeight = FontWeight.SemiBold
                                        )
                                    }
                                    item.artistName?.let { it1 ->
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
