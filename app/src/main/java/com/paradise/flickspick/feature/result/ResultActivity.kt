@file:OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)

package com.paradise.flickspick.feature.result

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.paradise.flickspick.R
import com.paradise.flickspick.common.component.ComposeToBitmap
import com.paradise.flickspick.common.component.PrimaryLargeButton
import com.paradise.flickspick.common.component.PrimarySmallButton
import com.paradise.flickspick.common.component.RowStar
import com.paradise.flickspick.common.component.WhiteTag
import com.paradise.flickspick.common.layout.Spacer
import com.paradise.flickspick.common.layout.TopAppBar
import com.paradise.flickspick.common.layout.fillMaxScreenWidth
import com.paradise.flickspick.common.style.PickColor
import com.paradise.flickspick.common.style.PickDisplay1
import com.paradise.flickspick.common.style.PickHeadline
import com.paradise.flickspick.common.style.PickSubhead1
import com.paradise.flickspick.feature.ResultShare
import com.paradise.flickspick.feature.home.HomeActivity
import com.paradise.flickspick.feature.home.SimpleMovie
import com.paradise.flickspick.feature.home.SmallMovieContent
import com.paradise.flickspick.util.FileUtil
import com.paradise.flickspick.util.ShareUtil
import com.paradise.flickspick.util.rememberToast
import com.paradise.flickspick.util.startActivityWithAnimation
import kotlin.math.absoluteValue

class ResultActivity : ComponentActivity() {

    private val vm: ResultViewModel = ResultViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val state = vm.state.collectAsState().value

            Scaffold(
                topBar = {
                    TopAppBar(
                        leadingContent = {
                            Image(
                                painter = painterResource(id = R.drawable.ic_back_24),
                                contentDescription = null,
                            )
                        },
                    )
                },
                bottomBar = {
                    PrimaryLargeButton(
                        text = "홈으로 이동하기",
                        enabled = true,
                        shape = RoundedCornerShape(0.dp),
                    ) {
                        navigateToHome()
                    }
                }
            ) { paddingValues ->
                ResultScreen(
                    state = state,
                    paddingValues = paddingValues,
                )
            }
        }
    }

    private fun navigateToHome() {
        startActivityWithAnimation<HomeActivity>()
    }
}

@Composable
private fun ResultScreen(
    state: ResultState,
    paddingValues: PaddingValues
) {
    val coroutineScope = rememberCoroutineScope()
    val pageCount = state.movies.count()
    val pagerState = rememberPagerState(initialPage = 0)
    val (currentPage, changePage) = remember { mutableStateOf(0) }
    val targetMovie = state.movies[currentPage]

    val context = LocalContext.current
    val imageLoader =
        ImageLoader.Builder(context).allowHardware(false) // Disallow hardware bitmaps.
            .build()

    val shareMoviePainter = state.movies.map { movie ->
        val painter = rememberAsyncImagePainter(
            model = movie.image,
            imageLoader = imageLoader,
        )
        Pair(painter, movie.name)
    }

    val snapshot = ComposeToBitmap {
        ResultShare(
            nickname = state.nickname,
            typename = state.typename,
            tag = state.tag,
            recommendMovie = shareMoviePainter
        )
    }

    val toast = rememberToast()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(color = PickColor.Black)
            .padding(horizontal = 20.dp)
            .padding(paddingValues),
    ) {
        Spacer(space = 24.dp)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .drawBehind {
                    val gradient = Brush.linearGradient(
                        colors = listOf(
                            Color.Black,  // 블랙
                            Color(0xFFBBBBBB)  // 옅은 회색
                        ),
                        start = Offset(0f, size.height),  // 왼쪽 하단
                        end = Offset(size.width, 0f)  // 오른쪽 상단
                    )
                    drawRect(
                        brush = gradient,
                        size = size
                    )
                }
                .border(
                    width = 0.5.dp,
                    brush = Brush.linearGradient(
                        colors = listOf(
                            PickColor.Primary,  // 블랙
                            Color(0xFFFAFFCC)
                        ),
                        start = Offset.Zero,
                        end = Offset.Infinite
                    ),
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(horizontal = 28.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(space = 50.dp)
            Image(
                painter = painterResource(id = R.drawable.ic_tag_105_101),
                contentDescription = null,
            )
            Spacer(space = 16.dp)
            PickDisplay1(
                text = "${state.nickname}님은\n${state.typename}",
                color = PickColor.White,
                align = TextAlign.Center,
            )
            Spacer(space = 42.dp)
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                state.tag.forEach { tag ->
                    WhiteTag(
                        modifier = Modifier.weight(1f),
                        text = tag,
                    )
                }
            }
            Spacer(space = 24.dp)
            PrimarySmallButton(text = "공유하기", enabled = true) {
                val bitmap = snapshot.invoke()
                val file = FileUtil.imageExternalSave(bitmap)
                kotlin.runCatching {
                    ShareUtil.shareInstagram(
                        context = context,
                        path = file,
                    )
                }.onFailure {
                    toast.invoke("인스타 공유하기에 실패했습니다! ${it.message}")
                }
            }
            Spacer(space = 24.dp)
        }
        Spacer(space = 24.dp)
        PickHeadline(text = "${state.nickname}님을 위한 추천영상", color = PickColor.White)
        Spacer(space = 36.dp)
        HorizontalPager(
            modifier = Modifier.fillMaxScreenWidth(),
            state = pagerState,
            pageCount = pageCount,
            contentPadding = PaddingValues(
                horizontal = 16.dp,
            )
        ) { page ->
            changePage(page)
            Box(
                Modifier
                    .fillMaxWidth()
                    .pagerCubeInDepthTransition(page, pagerState)
            ) {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 14.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .aspectRatio(3f / 4f),
                    model = state.movies[page].image,
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
            }
        }
        Spacer(space = 16.dp)
        Column(
            modifier = Modifier.padding(horizontal = 10.dp)
        ) {
            MovieRecommendSection(state = targetMovie)
        }
        Spacer(space = 56.dp)
        LazyRow(
            modifier = Modifier.fillMaxScreenWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            item {
                androidx.compose.foundation.layout.Spacer(modifier = Modifier.width(16.dp))
            }
            items(state.movies) { item ->
                SmallMovieContent(
                    simpleMovie = SimpleMovie(
                        name = item.name,
                        image = item.image,
                        starNum = item.starNum,
                        description = item.description,
                    )
                )
            }
            item {
                androidx.compose.foundation.layout.Spacer(modifier = Modifier.width(16.dp))
            }
        }
    }
}

@Composable
private fun ColumnScope.MovieRecommendSection(
    state: Movie,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        PickHeadline(text = state.name, color = Color.White)
        Spacer(space = 8.dp)
        RowStar(starNum = state.starNum)
        Spacer(weight = 1f)
        Image(
            painter = painterResource(id = R.drawable.ic_filled_movie),
            contentDescription = null,
        )

    }
    Spacer(space = 8.dp)
    PickSubhead1(text = state.description, color = PickColor.Gray05)
    Spacer(space = 32.dp)
    PickHeadline(text = "추천 드리는 이유", color = PickColor.White)
    Spacer(space = 8.dp)
    PickSubhead1(
        text = state.reason,
        color = PickColor.Gray07
    )
    Spacer(space = 24.dp)
    PickHeadline(text = "줄거리", color = PickColor.White)
    Spacer(space = 16.dp)
    PickSubhead1(
        text = state.plot,
        color = PickColor.Gray07
    )
}

@OptIn(ExperimentalFoundationApi::class)
fun PagerState.calculateCurrentOffsetForPage(page: Int): Float {
    return (currentPage - page) + currentPageOffsetFraction
}

@OptIn(ExperimentalFoundationApi::class)
fun Modifier.pagerCubeInDepthTransition(page: Int, pagerState: PagerState) = graphicsLayer {
    cameraDistance = 32f
    val pageOffset = pagerState.calculateCurrentOffsetForPage(page)

    if (pageOffset < -1f) {
        alpha = 0f
    } else if (pageOffset <= 0) {
        alpha = 1f
        transformOrigin = TransformOrigin(0f, 0.5f)
        rotationY = -90f * pageOffset.absoluteValue

    } else if (pageOffset <= 1) {
        alpha = 1f
        transformOrigin = TransformOrigin(1f, 0.5f)
        rotationY = 90f * pageOffset.absoluteValue
    } else {
        alpha = 0f
    }

    if (pageOffset.absoluteValue <= 0.5) {
        scaleY = 0.4f.coerceAtLeast(1 - pageOffset.absoluteValue)
    } else if (pageOffset.absoluteValue <= 1) {
        scaleY = 0.4f.coerceAtLeast(1 - pageOffset.absoluteValue)
    }
}