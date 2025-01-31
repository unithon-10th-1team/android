package com.paradise.flickspick.common.layout

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Composable
fun ColumnScope.Spacer(space: Dp) {
    androidx.compose.foundation.layout.Spacer(modifier = Modifier.height(height = space))
}

@Composable
fun ColumnScope.Spacer(weight: Float) {
    androidx.compose.foundation.layout.Spacer(modifier = Modifier.weight(weight))
}

@Composable
fun RowScope.Spacer(space: Dp) {
    androidx.compose.foundation.layout.Spacer(modifier = Modifier.width(width = space))
}

@Composable
fun RowScope.Spacer(weight: Float) {
    androidx.compose.foundation.layout.Spacer(modifier = Modifier.weight(weight))
}
