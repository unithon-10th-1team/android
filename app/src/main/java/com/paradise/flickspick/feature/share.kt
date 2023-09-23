package com.paradise.flickspick.feature

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.paradise.flickspick.R
import com.paradise.flickspick.common.component.SmallTag
import com.paradise.flickspick.common.layout.Spacer
import com.paradise.flickspick.common.style.PickColor
import com.paradise.flickspick.common.style.PickDisplay1
import com.paradise.flickspick.common.style.PickHeadline
import com.paradise.flickspick.common.style.PickSubhead2

@Composable
fun ResultShare(
    nickname: String,
    typename: String,
    tag: List<String>,
    recommendMovie: List<Pair<Painter, String>>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = PickColor.Black)
            .clip(RoundedCornerShape(32.dp))
            .padding(horizontal = 16.dp),
    ) {
        Spacer(space = 125.dp)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            PickDisplay1(
                text = "${nickname}님은\n${typename}",
                color = PickColor.White
            )
            Image(
                painter = painterResource(id = R.drawable.ic_tag_105_101),
                contentDescription = null,
            )
        }
        Spacer(space = 34.dp)
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            tag.forEach { tar ->
                SmallTag(text = tar)
            }
        }
        Spacer(space = 64.dp)
        PickHeadline(text = "추천 드리는 영화", color = PickColor.Primary)
        Spacer(space = 16.dp)
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            recommendMovie.forEach { (painter, title) ->
                ReviewMovie(
                    modifier = Modifier.weight(1f),
                    imagePainter = painter,
                    title = title,
                )
            }
        }
        Spacer(space = 56.dp)
    }
}

@Composable
private fun ReviewMovie(
    modifier: Modifier = Modifier,
    imagePainter: Painter,
    title: String,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            modifier = Modifier
                .size(100.dp)
                .clip(RoundedCornerShape(30.dp)),
            painter = imagePainter,
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )
        Spacer(space = 10.dp)
        PickSubhead2(
            text = title,
            color = PickColor.White,
        )
    }
}