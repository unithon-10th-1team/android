package com.paradise.flickspick.common.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.paradise.flickspick.R

private const val MaxStarNumber: Int = 5
@Composable
fun RowStar(
    modifier: Modifier = Modifier,
    starNum: Int
) {
    Row(
        modifier = modifier
    ) {
        (1..starNum).forEach {
            Image(
                painter = painterResource(id = R.drawable.ic_star_filled_24),
                contentDescription = null
            )
        }
        (1..MaxStarNumber - starNum).forEach {
            Image(
                painter = painterResource(id = R.drawable.ic_star_outlined_24),
                contentDescription = null
            )
        }
    }
}