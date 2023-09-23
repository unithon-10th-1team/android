package com.paradise.flickspick.common.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.paradise.flickspick.common.style.PickBody3
import com.paradise.flickspick.common.style.PickColor

@Composable
fun TopAppBar(
    leadingContent: (@Composable () -> Unit)? = null,
    text: String? = null,
    trailingContent: (@Composable () -> Unit)? = null,
    backgroundColor: Color = PickColor.Black
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(backgroundColor)
            .padding(
                horizontal = 16.dp,
                vertical = 12.dp,
            )
    ) {
        Box(modifier = Modifier.align(Alignment.CenterStart)) {
            leadingContent?.invoke()
        }
        text?.let { text ->
            PickBody3(modifier = Modifier.align(Alignment.Center), text = text)
        }
        Box(modifier = Modifier.align(Alignment.CenterEnd)) {
            trailingContent?.invoke()

        }
    }
}