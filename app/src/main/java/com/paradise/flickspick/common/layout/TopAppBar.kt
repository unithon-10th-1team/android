package com.paradise.flickspick.common.layout

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.paradise.flickspick.common.style.PickBody3

@Composable
fun TopAppBar(
    leadingContent: @Composable () -> Unit,
    text: String,
    trailingContent: @Composable () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
    ) {
        Box(modifier = Modifier.align(Alignment.CenterStart)) {
            leadingContent()
        }
        PickBody3(modifier = Modifier.align(Alignment.Center), text = text)
        Box(modifier = Modifier.align(Alignment.CenterEnd)) {
            trailingContent()
        }
    }
}