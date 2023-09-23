package com.paradise.flickspick.common.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.paradise.flickspick.common.style.PickColor
import com.paradise.flickspick.common.style.PickSubhead2
import com.paradise.flickspick.util.pickClickable

@Composable
fun PrimaryLargeButton(
    text: String,
    enabled: Boolean,
    shape: Shape = RoundedCornerShape(8.dp),
    onClick: () -> Unit,
) {
    val color =
        animateColorAsState(if (enabled) PickColor.Primary else PickColor.Disabled, label = "")
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(42.dp)
            .background(
                color = color.value,
                shape = shape,
            )
            .pickClickable {
                onClick()
            }
        ,
        contentAlignment = Alignment.Center,
    ) {
        PickSubhead2(text = text)
    }
}

@Composable
fun OutlineLargeButton(
    text: String,
    textColor: Color = PickColor.Black,
    enabled: Boolean,
    onClick: () -> Unit,
) {
    val color =
        animateColorAsState(if (enabled) PickColor.Primary else PickColor.Disabled, label = "")
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(42.dp)
            .border(
                width = 1.dp,
                color = color.value,
                shape = RoundedCornerShape(8.dp),
            )
            .pickClickable {
                onClick()
            }
        ,
        contentAlignment = Alignment.Center,
    ) {
        PickSubhead2(text = text, color = textColor)
    }
}


@Preview
@Composable
fun PreviewButton() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        PrimaryLargeButton(text = "sad", enabled = true) {

        }
    }
}