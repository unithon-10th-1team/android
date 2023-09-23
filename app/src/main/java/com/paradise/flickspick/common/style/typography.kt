package com.paradise.flickspick.common.style

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import com.paradise.flickspick.R
import com.paradise.flickspick.util.pickClickable
import com.paradise.flickspick.util.runIf

internal val pretendardFamily = FontFamily(
    Font(R.font.pretendard_black, FontWeight.Black),
    Font(R.font.pretendard_bold, FontWeight.Bold),
    Font(R.font.pretendard_extra_light, FontWeight.ExtraLight),
    Font(R.font.pretendard_light, FontWeight.Light),
    Font(R.font.pretendard_medium, FontWeight.Medium),
    Font(R.font.pretendard_regular, FontWeight.Normal),
    Font(R.font.pretendard_semi_bold, FontWeight.SemiBold),
    Font(R.font.pretendard_thin, FontWeight.Thin),
    Font(R.font.pretendrad_extra_bold, FontWeight.ExtraBold),
)

internal object PickTextStyle {

    val Display4 = TextStyle(
        color = Color.Black,
        fontSize = 36.sp,
        fontFamily = pretendardFamily,
//        lineHeight = 58.sp,
        fontWeight = FontWeight.Bold,
    )

    val Display3 = TextStyle(
        color = Color.Black,
        fontSize = 32.sp,
        fontFamily = pretendardFamily,
//        lineHeight = 52.sp,
        fontWeight = FontWeight.Bold,
    )

    val Display2 = TextStyle(
        color = Color.Black,
        fontSize = 28.sp,
        fontFamily = pretendardFamily,
//        lineHeight = 44.sp,
        fontWeight = FontWeight.Bold,
    )

    val Display1 = TextStyle(
        color = Color.Black,
        fontSize = 24.sp,
        fontFamily = pretendardFamily,
//        lineHeight = 38.sp,
        fontWeight = FontWeight.Bold,
    )

    val Headline = TextStyle(
        color = Color.Black,
        fontSize = 20.sp,
        fontFamily = pretendardFamily,
//        lineHeight = 32.sp,
        fontWeight = FontWeight.SemiBold,
    )

    val Subhead3 = TextStyle(
        color = Color.Black,
        fontSize = 16.sp,
        fontFamily = pretendardFamily,
//        lineHeight = 26.sp,
        fontWeight = FontWeight.SemiBold,
    )

    val Subhead2 = TextStyle(
        color = Color.Black,
        fontSize = 14.sp,
        fontFamily = pretendardFamily,
//        lineHeight = 22.sp,
        fontWeight = FontWeight.SemiBold,
    )

    val Subhead1 = TextStyle(
        color = Color.Black,
        fontSize = 12.sp,
        fontFamily = pretendardFamily,
//        lineHeight = 20.sp,
        fontWeight = FontWeight.SemiBold,
    )

    val Body4 = TextStyle(
        color = Color.Black,
        fontSize = 20.sp,
        fontFamily = pretendardFamily,
//        lineHeight = 32.sp,
        fontWeight = FontWeight.Medium,
    )

    val Body3 = TextStyle(
        color = Color.Black,
        fontSize = 16.sp,
        fontFamily = pretendardFamily,
        lineHeight = 26.sp,
        fontWeight = FontWeight.Medium,
    )

    val Body2 = TextStyle(
        color = Color.Black,
        fontSize = 14.sp,
        fontFamily = pretendardFamily,
//        lineHeight = 22.sp,
        fontWeight = FontWeight.Medium,
    )

    val Body1 = TextStyle(
        color = Color.Black,
        fontSize = 12.sp,
        fontFamily = pretendardFamily,
//        lineHeight = 20.sp,
        fontWeight = FontWeight.Medium,
    )
}


@Composable
fun PickDisplay4(
    modifier: Modifier = Modifier,
    padding: PaddingValues? = null,
    text: String,
    color: Color = PickColor.Black,
    align: TextAlign = TextAlign.Start,
    rippleEnabled: Boolean = false,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    onClick: (() -> Unit)? = null
) {
    Text(
        modifier = modifier
            .pickClickable(
                rippleEnabled = rippleEnabled,
                onClick = onClick
            ).runIf(
                condition = padding != null
            ) {
                padding(
                    paddingValues = padding!!
                )
            },
        text = text,
        style = PickTextStyle.Display4.copy(
            color = color,
            textAlign = align
        ),
        overflow = overflow,
        textAlign = TextAlign.Center
    )
}

@Composable
fun PickDisplay3(
    modifier: Modifier = Modifier,
    padding: PaddingValues? = null,
    text: String,
    color: Color = PickColor.Black,
    align: TextAlign = TextAlign.Start,
    rippleEnabled: Boolean = false,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    onClick: (() -> Unit)? = null
) {
    Text(
        modifier = modifier
            .pickClickable(
                rippleEnabled = rippleEnabled,
                onClick = onClick
            ).runIf(
                condition = padding != null
            ) {
                padding(
                    paddingValues = padding!!
                )
            },
        text = text,
        style = PickTextStyle.Display3.copy(
            color = color,
            textAlign = align
        ),
        overflow = overflow,
        textAlign = TextAlign.Center
    )
}

@Composable
fun PickDisplay2(
    modifier: Modifier = Modifier,
    padding: PaddingValues? = null,
    text: String,
    color: Color = PickColor.Black,
    align: TextAlign = TextAlign.Start,
    rippleEnabled: Boolean = false,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    onClick: (() -> Unit)? = null
) {
    Text(
        modifier = modifier
            .pickClickable(
                rippleEnabled = rippleEnabled,
                onClick = onClick
            ).runIf(
                condition = padding != null
            ) {
                padding(
                    paddingValues = padding!!
                )
            },
        text = text,
        style = PickTextStyle.Display2.copy(
            color = color,
            textAlign = align
        ),
        overflow = overflow,
        textAlign = TextAlign.Center
    )
}


@Composable
fun PickDisplay1(
    modifier: Modifier = Modifier,
    padding: PaddingValues? = null,
    text: String,
    color: Color = PickColor.Black,
    align: TextAlign = TextAlign.Start,
    rippleEnabled: Boolean = false,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    onClick: (() -> Unit)? = null
) {
    Text(
        modifier = modifier
            .pickClickable(
                rippleEnabled = rippleEnabled,
                onClick = onClick
            ).runIf(
                condition = padding != null
            ) {
                padding(
                    paddingValues = padding!!
                )
            },
        text = text,
        style = PickTextStyle.Display1.copy(
            color = color,
            textAlign = align
        ),
        overflow = overflow,
    )
}

@Composable
fun PickHeadline(
    modifier: Modifier = Modifier,
    padding: PaddingValues? = null,
    text: String,
    color: Color = PickColor.Black,
    align: TextAlign = TextAlign.Start,
    rippleEnabled: Boolean = false,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    onClick: (() -> Unit)? = null
) {
    Text(
        modifier = modifier
            .pickClickable(
                rippleEnabled = rippleEnabled,
                onClick = onClick
            ).runIf(
                condition = padding != null
            ) {
                padding(
                    paddingValues = padding!!
                )
            },
        text = text,
        style = PickTextStyle.Headline.copy(
            color = color,
            textAlign = align
        ),
        overflow = overflow,
    )
}


@Composable
fun PickSubhead3(
    modifier: Modifier = Modifier,
    padding: PaddingValues? = null,
    text: String,
    color: Color = PickColor.Black,
    align: TextAlign = TextAlign.Start,
    rippleEnabled: Boolean = false,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    onClick: (() -> Unit)? = null
) {
    Text(
        modifier = modifier
            .pickClickable(
                rippleEnabled = rippleEnabled,
                onClick = onClick
            ).runIf(
                condition = padding != null
            ) {
                padding(
                    paddingValues = padding!!
                )
            },
        text = text,
        style = PickTextStyle.Subhead3.copy(
            color = color,
            textAlign = align
        ),
        overflow = overflow,
    )
}

@Composable
fun PickSubhead2(
    modifier: Modifier = Modifier,
    padding: PaddingValues? = null,
    text: String,
    color: Color = PickColor.Black,
    align: TextAlign = TextAlign.Start,
    rippleEnabled: Boolean = false,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    onClick: (() -> Unit)? = null
) {
    Text(
        modifier = modifier
            .pickClickable(
                rippleEnabled = rippleEnabled,
                onClick = onClick
            ).runIf(
                condition = padding != null
            ) {
                padding(
                    paddingValues = padding!!
                )
            },
        text = text,
        style = PickTextStyle.Subhead2.copy(
            color = color,
            textAlign = align
        ),
        overflow = overflow,
    )
}

@Composable
fun PickSubhead1(
    modifier: Modifier = Modifier,
    padding: PaddingValues? = null,
    text: String,
    color: Color = PickColor.Black,
    align: TextAlign = TextAlign.Start,
    rippleEnabled: Boolean = false,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    onClick: (() -> Unit)? = null
) {
    Text(
        modifier = modifier
            .pickClickable(
                rippleEnabled = rippleEnabled,
                onClick = onClick
            ).runIf(
                condition = padding != null
            ) {
                padding(
                    paddingValues = padding!!
                )
            },
        text = text,
        style = PickTextStyle.Subhead1.copy(
            color = color,
            textAlign = align
        ),
        overflow = overflow,
    )
}

@Composable
fun PickBody4(
    modifier: Modifier = Modifier,
    padding: PaddingValues? = null,
    text: String,
    color: Color = PickColor.Black,
    align: TextAlign = TextAlign.Start,
    rippleEnabled: Boolean = false,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    onClick: (() -> Unit)? = null
) {
    Text(
        modifier = modifier
            .pickClickable(
                rippleEnabled = rippleEnabled,
                onClick = onClick
            ).runIf(
                condition = padding != null
            ) {
                padding(
                    paddingValues = padding!!
                )
            },
        text = text,
        style = PickTextStyle.Body4.copy(
            color = color,
            textAlign = align
        ),
        overflow = overflow,
    )
}

@Composable
fun PickBody3(
    modifier: Modifier = Modifier,
    padding: PaddingValues? = null,
    text: String,
    color: Color = PickColor.Black,
    align: TextAlign = TextAlign.Start,
    rippleEnabled: Boolean = false,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    onClick: (() -> Unit)? = null
) {
    Text(
        modifier = modifier
            .pickClickable(
                rippleEnabled = rippleEnabled,
                onClick = onClick
            ).runIf(
                condition = padding != null
            ) {
                padding(
                    paddingValues = padding!!
                )
            },
        text = text,
        style = PickTextStyle.Body3.copy(
            color = color,
            textAlign = align
        ),
        overflow = overflow,
    )
}

@Composable
fun PickBody2(
    modifier: Modifier = Modifier,
    padding: PaddingValues? = null,
    text: String,
    color: Color = PickColor.Black,
    align: TextAlign = TextAlign.Start,
    rippleEnabled: Boolean = false,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    onClick: (() -> Unit)? = null
) {
    Text(
        modifier = modifier
            .pickClickable(
                rippleEnabled = rippleEnabled,
                onClick = onClick
            ).runIf(
                condition = padding != null
            ) {
                padding(
                    paddingValues = padding!!
                )
            },
        text = text,
        style = PickTextStyle.Body2.copy(
            color = color,
            textAlign = align
        ),
        overflow = overflow,
    )
}

@Composable
fun PickBody1(
    modifier: Modifier = Modifier,
    padding: PaddingValues? = null,
    text: String,
    color: Color = PickColor.Black,
    align: TextAlign = TextAlign.Start,
    rippleEnabled: Boolean = false,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    onClick: (() -> Unit)? = null
) {
    Text(
        modifier = modifier
            .pickClickable(
                rippleEnabled = rippleEnabled,
                onClick = onClick
            ).runIf(
                condition = padding != null
            ) {
                padding(
                    paddingValues = padding!!
                )
            },
        text = text,
        style = PickTextStyle.Body1.copy(
            color = color,
            textAlign = align
        ),
        overflow = overflow,
    )
}
