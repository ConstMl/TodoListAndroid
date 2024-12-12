package com.task.planner.presentation.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

val SmallDp: Dp = 4.dp
val MediumDp: Dp = 8.dp
val LargeDp: Dp = 16.dp

val TaskItemHeight: Dp = 48.dp
val TaskItemIconSize: Dp = 24.dp
val TaskItemActionButtonRippleRadius: Dp = 36.dp

val TodoInputBarHeight: Dp = 64.dp
val OverlappingHeight = TodoInputBarHeight

enum class CircularProgressIndicatorSize(val size: Dp) {
    SMALL(20.dp),
    MEDIUM(34.dp),
    BIG(64.dp)
}
