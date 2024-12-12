package com.task.planner.presentation.widget

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.task.planner.presentation.theme.CircularProgressIndicatorSize

@Composable
internal fun CustomCircularProgressIndicator(
    modifier: Modifier = Modifier,
    indicatorSize: CircularProgressIndicatorSize = CircularProgressIndicatorSize.BIG
) {
    Box(modifier = modifier) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(indicatorSize.size)
                .align(Alignment.Center),
            color = MaterialTheme.colorScheme.secondary,
            trackColor = MaterialTheme.colorScheme.surfaceVariant,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ComposeLoaderPreview() {
    CustomCircularProgressIndicator(modifier = Modifier.fillMaxSize())
}
