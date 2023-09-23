@file:OptIn(
    ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class,
    ExperimentalAnimationApi::class
)

package com.paradise.flickspick.feature.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.animation.Crossfade
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.paradise.flickspick.R
import com.paradise.flickspick.common.component.OutlineLargeButton
import com.paradise.flickspick.common.component.PrimaryLargeButton
import com.paradise.flickspick.common.component.RowStar
import com.paradise.flickspick.common.component.SmallTag
import com.paradise.flickspick.common.layout.Spacer
import com.paradise.flickspick.common.layout.TopAppBar
import com.paradise.flickspick.common.layout.fillMaxScreenWidth
import com.paradise.flickspick.common.style.PickBody1
import com.paradise.flickspick.common.style.PickColor
import com.paradise.flickspick.common.style.PickDisplay1
import com.paradise.flickspick.common.style.PickHeadline
import com.paradise.flickspick.common.style.PickSubhead1
import com.paradise.flickspick.common.style.PickSubhead3
import com.paradise.flickspick.util.pickClickable

class HomeActivity : ComponentActivity() {

    private val vm: HomeViewModel = HomeViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val state = vm.state.collectAsState().value
            val (index, changedIndex) = remember { mutableStateOf(0) }

            Scaffold(
                bottomBar = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                            .background(PickColor.Black),
                    ) {
                        BottomNavSelectItem(
                            icon = R.drawable.ic_home_disable,
                            selectIcon = R.drawable.ic_home_enable,
                            title = "홈",
                            index = 0,
                            selectedIndex = { changedIndex(0) },
                            isSelected = index == 0,
                        )
                        BottomNavSelectItem(
                            icon = R.drawable.ic_mypage_disable,
                            selectIcon = R.drawable.ic_mypage_enable,
                            title = "마이페이지",
                            index = 1,
                            selectedIndex = { changedIndex(1) },
                            isSelected = index == 1,
                        )
                    }
                }
            ) { paddingValues ->
                Crossfade(targetState = index, label = "") { index ->
                    when (index) {
                        0 -> HomeScreen(state = state, paddingValues = paddingValues)
                        1 -> MyPageScreen(state = state)
                    }
                }
            }
        }
    }
}

@Composable
fun MyPageScreen(
    state: HomeState,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(color = PickColor.Black)
            .padding(horizontal = 16.dp),
    ) {
        TopAppBar(text = "마이페이지")
        Spacer(space = 16.dp)
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            AsyncImage(
                modifier = Modifier
                    .clip(CircleShape)
                    .size(80.dp),
                model = state.imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Spacer(space = 32.dp)
            PickHeadline(text = "안녕하세요, ", color = PickColor.White)
            PickHeadline(text = state.nickname, color = PickColor.Primary)
            PickHeadline(text = "님", color = PickColor.White)
        }
        Spacer(space = 16.dp)
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            PickSubhead3(text = "사용하시는 OTT 서비스", color = PickColor.White)
            Spacer(space = 8.dp)
            Image(painter = painterResource(id = R.drawable.ic_next_24), contentDescription = null)
        }
        Spacer(space = 16.dp)
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            state.usingOtt.forEach { ott ->
                Image(
                    modifier = Modifier.size(48.dp),
                    painter = painterResource(id = ott.icon),
                    contentDescription = null,
                )
            }
        }
        Spacer(space = 32.dp)
        PickSubhead3(text = "추천 드리는 이유", color = PickColor.White)
        Spacer(space = 32.dp)
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            state.tag.forEach { tar ->
                SmallTag(text = tar)
            }
        }
        Spacer(space = 32.dp)
        PickSubhead3(text = "추천 영화", color = PickColor.White)
        Spacer(space = 16.dp)
        LazyRow(
            modifier = Modifier.fillMaxScreenWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            item {
                androidx.compose.foundation.layout.Spacer(modifier = Modifier.width(16.dp))
            }
            items(state.recommends) { item ->
                SmallMovieContent(simpleMovie = item)
            }
            item {
                androidx.compose.foundation.layout.Spacer(modifier = Modifier.width(16.dp))
            }
        }
        Spacer(space = 56.dp)
    }
}

@Composable
fun HomeScreen(
    state: HomeState,
    paddingValues: PaddingValues,
) {
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = PickColor.Black)
            .padding(
                horizontal = 20.dp
            )
            .padding(paddingValues)
            .verticalScroll(rememberScrollState()),
    ) {
        Spacer(space = 74.dp)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Column {
                PickDisplay1(
                    text = "${state.nickname}님은\n${state.typename}",
                    color = PickColor.White
                )
                Spacer(space = 8.dp)
                PickSubhead1(
                    text = "다시 추천받기",
                    color = PickColor.Gray05
                )
            }
            Image(
                painter = painterResource(id = R.drawable.ic_tag_105_101),
                contentDescription = null,
            )
        }
        Spacer(space = 50.dp)
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            state.tag.forEach { tar ->
                SmallTag(text = tar)
            }
        }
        Spacer(space = 24.dp)
        PrimaryLargeButton(
            text = "테스트 다시하기",
            enabled = true,
        ) {

        }
        Spacer(space = 8.dp)
        OutlineLargeButton(
            text = "공유하기",
            enabled = true,
            textColor = PickColor.Primary,
        ) {

        }
        Spacer(space = 36.dp)
        PickHeadline(text = "나랑 비슷한 키워드를 가진\n사람들이 추천 받은 영화", color = PickColor.White)
        Spacer(space = 20.dp)
        HorizontalPager(
            modifier = Modifier.fillMaxScreenWidth(),
            state = rememberPagerState(),
            pageCount = state.similarRecommends.count(),
            contentPadding = PaddingValues(
                horizontal = 16.dp,
            )
        ) { page ->
            val similarTargetMovie = state.similarRecommends[page]
            Column {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 14.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .aspectRatio(3f / 4f),
                    model = similarTargetMovie.image,
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
                Spacer(space = 8.dp)
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    PickHeadline(text = similarTargetMovie.name)
                    Spacer(space = 8.dp)
                    RowStar(starNum = similarTargetMovie.starNum)
                }
            }
        }
        Spacer(space = 32.dp)
        PickHeadline(text = "나랑 정반대 키워드를 가진\n사람들이 받은 추천 영화", color = PickColor.White)
        Spacer(space = 24.dp)
        LazyRow(
            modifier = Modifier.fillMaxScreenWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            item {
                androidx.compose.foundation.layout.Spacer(modifier = Modifier.width(16.dp))
            }
            items(state.oppositeRecommends) { item ->
                SmallMovieContent(simpleMovie = item)
            }
            item {
                androidx.compose.foundation.layout.Spacer(modifier = Modifier.width(16.dp))
            }
        }
        Spacer(space = 56.dp)
    }
}

@Composable
private fun SmallMovieContent(
    simpleMovie: SimpleMovie,
) {
    Column(
        modifier = Modifier.width(150.dp)
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(3f / 4f)
                .clip(RoundedCornerShape(8.dp)),
            model = simpleMovie.image,
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Spacer(space = 8.dp)
        PickHeadline(text = simpleMovie.name, color = PickColor.White)
        RowStar(
            modifier = Modifier.fillMaxWidth(),
            starNum = simpleMovie.starNum
        )
        Spacer(space = 8.dp)
        PickBody1(text = simpleMovie.description, color = PickColor.Gray08)
    }
}

@Composable
private fun RowScope.BottomNavSelectItem(
    @DrawableRes icon: Int,
    @DrawableRes selectIcon: Int,
    title: String,
    index: Int,
    selectedIndex: (Int) -> Unit,
    isSelected: Boolean,
) {
    val icon = animateIntAsState(targetValue = if (isSelected) selectIcon else icon, label = "")
    val textColor = animateColorAsState(
        targetValue = if (isSelected) PickColor.Primary else PickColor.Gray07,
        label = ""
    )

    Column(
        modifier = Modifier
            .weight(1f)
            .height(56.dp)
            .pickClickable {
                selectedIndex(index)
            },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(id = icon.value),
            contentDescription = null,
        )
        PickBody1(text = title, color = textColor.value)
    }
}