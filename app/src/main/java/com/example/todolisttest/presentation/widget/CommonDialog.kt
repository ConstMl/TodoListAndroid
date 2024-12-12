package com.example.todolisttest.presentation.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.todolisttest.R
import com.example.todolisttest.presentation.theme.DialogButtonTextStyle
import com.example.todolisttest.presentation.theme.DialogTitleTextStyle

@Composable
internal fun CommonDialog(
    title: String,
    onConfirm: () -> Unit = { },
    onCancel: () -> Unit = { }
) {
    Dialog(
        onDismissRequest = onCancel,
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true,
            decorFitsSystemWindows = true,
        ),
    ) {
        Column(
            Modifier
                .width(320.dp)
                .wrapContentHeight()
                .background(
                    color = MaterialTheme.colorScheme.tertiaryContainer,
                    shape = RoundedCornerShape(10.dp)
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(top = 28.dp, start = 16.dp, end = 16.dp),
                text = title,
                style = DialogTitleTextStyle.copy(
                    color = MaterialTheme.colorScheme.tertiary
                ),
                textAlign = TextAlign.Center
            )
            Row(
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .height(42.dp)
            ) {
                Button(
                    colors = ButtonDefaults.buttonColors(Color.Transparent),
                    onClick = { onConfirm.invoke() }
                ) {
                    Text(
                        text = stringResource(id = R.string.yes),
                        style = DialogButtonTextStyle.copy(
                            color = MaterialTheme.colorScheme.tertiary
                        )
                    )
                }
                VerticalDivider(
                    modifier = Modifier
                        .fillMaxHeight(.55f)
                        .align(Alignment.CenterVertically),
                    color = MaterialTheme.colorScheme.primary
                )
                Button(
                    colors = ButtonDefaults.buttonColors(Color.Transparent),
                    onClick = { onCancel.invoke() }
                ) {
                    Text(
                        text = stringResource(id = R.string.no),
                        style = DialogButtonTextStyle.copy(
                            color = MaterialTheme.colorScheme.tertiary
                        )
                    )
                }
            }
        }
    }
}

@Composable
@Preview
private fun CommonDialogPreview() {
    CommonDialog(
        title = stringResource(id = R.string.confirm_multi_selected_remove_task_dialog_title)
    )
}
