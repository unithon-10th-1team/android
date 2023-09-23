@file:OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)

package com.paradise.flickspick.feature.result

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.paradise.flickspick.R
import com.paradise.flickspick.common.component.PrimaryLargeButton
import com.paradise.flickspick.common.component.RowStar
import com.paradise.flickspick.common.component.SmallTag
import com.paradise.flickspick.common.layout.Spacer
import com.paradise.flickspick.common.layout.TopAppBar
import com.paradise.flickspick.common.layout.fillMaxScreenWidth
import com.paradise.flickspick.common.style.PickColor
import com.paradise.flickspick.common.style.PickDisplay1
import com.paradise.flickspick.common.style.PickHeadline
import com.paradise.flickspick.common.style.PickSubhead1

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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(color = PickColor.Black)
            .padding(horizontal = 20.dp)
            .padding(paddingValues),
    ) {
        Spacer(space = 24.dp)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            PickDisplay1(
                text = "${state.nickname}님은\n${state.typename}",
                color = PickColor.White
            )
            Image(
                painter = painterResource(id = R.drawable.ic_tag_105_101),
                contentDescription = null,
            )
        }
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
        Spacer(space = 16.dp)
        Column(
            modifier = Modifier.padding(horizontal = 10.dp)
        ) {
            MovieRecommendSection(state = targetMovie)
        }
        Spacer(space = 56.dp)
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
    PickSubhead1(text = state.description, color = PickColor.Gray08)
    Spacer(space = 32.dp)
    PickHeadline(text = "추천 드리는 이유", color = PickColor.White)
    Spacer(space = 8.dp)
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        state.tag.forEach { tar ->
            SmallTag(text = tar)
        }
    }
    Spacer(space = 16.dp)
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
