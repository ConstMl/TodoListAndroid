package com.task.planner.presentation.screens.task_list_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
internal fun MultiSelectionBottomPanel(
    modifier: Modifier = Modifier,
    itemsSelected: Boolean = false,
    removeEnabled: Boolean = true,
    onToggleMultiSelection: () -> Unit = {},
    onClose: () -> Unit = {},
    onRemove: () -> Unit = {}
) {
    val iconColor = MaterialTheme.colorScheme.secondary
    val iconColorSelection = if (removeEnabled) {
        iconColor
    } else {
        iconColor.copy(alpha = .3f)
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(52.dp)
            .background(MaterialTheme.colorScheme.secondaryContainer)
            .padding(start = 2.dp, end = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Checkbox(
            checked = itemsSelected,
            onCheckedChange = { onToggleMultiSelection.invoke() }
        )
        IconButton(
            onClick = { if (removeEnabled) onRemove.invoke() }
        ) {
            Icon(
                imageVector = Icons.Rounded.Delete,
                tint = iconColorSelection,
                contentDescription = null
            )
        }
        IconButton(onClick = onClose) {
            Icon(
                imageVector = Icons.Rounded.Close,
                tint = iconColor,
                contentDescription = null
            )
        }
    }
}

@Composable
@Preview
private fun MultiSelectionBottomPanelPreview() {
    MultiSelectionBottomPanel()
}
