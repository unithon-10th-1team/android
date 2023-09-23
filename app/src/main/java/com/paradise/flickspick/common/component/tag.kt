package com.paradise.flickspick.common.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.paradise.flickspick.common.style.PickColor.Primary
import com.paradise.flickspick.common.style.PickSubhead2
import com.paradise.flickspick.util.pickClickable

@Composable
fun SmallTag(
    text: String,
    onClick: (() -> Unit)? = null,
) {
    Box(
        modifier = Modifier
            .border(
                width = 1.dp,
                color = Primary,
                shape = RoundedCornerShape(20.dp),
            )
            .pickClickable(
                onClick = onClick,
            )
            .padding(
                horizontal = 16.dp,
                vertical = 3.dp,
            ),
        contentAlignment = Alignment.Center,
    ) {
        PickSubhead2(
            text = text,
            color = Primary,
        )
    }
}