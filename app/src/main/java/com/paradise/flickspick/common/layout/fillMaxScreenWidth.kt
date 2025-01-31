package com.paradise.flickspick.common.layout

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import kotlin.math.roundToInt

@Composable
fun Modifier.fillMaxScreenWidth(): Modifier {
    val configuration = LocalConfiguration.current
    val density = LocalDensity.current
    val screenWidthPx = remember { configuration.screenWidthDp * density.density }

    return layout { measurables, constraints ->
        val placeable = measurables.measure(
            constraints.copy(
                maxWidth = screenWidthPx.roundToInt(),
            ),
        )

        layout(screenWidthPx.roundToInt(), placeable.height) {
            placeable.place(0, 0)
        }
    }
}
