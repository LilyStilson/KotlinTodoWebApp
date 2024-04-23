import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun TodoItem(
    task: Task,
    onItemChanged: (Task) -> Unit,
    onDeleteClick: () -> Unit
) {
    val initialText = task.content
    var isEditing by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }
    
    val opacity: Float by animateFloatAsState(
        targetValue = if (task.isChecked) 0.5f else 1f,
        animationSpec = TweenSpec(250)
    )

    val strikeWidth: Float by animateFloatAsState(
        targetValue = if (task.isChecked) 1f else 0f,
        animationSpec = TweenSpec(250)
    )

    val borderWidth: Dp by animateDpAsState(
        targetValue = if (task.isChecked) 0.dp else 1.dp,
        animationSpec = TweenSpec(250)
    )

    Box(modifier = Modifier) {
        if (isEditing) {
            TodoInput(
                task = task,
                modifier = Modifier
                    .height(72.dp)
                    .padding(top = 8.dp, bottom = 8.dp)
                    .fillMaxWidth()
                    .focusRequester(focusRequester),
                onEditingCanceled = {
                    isEditing = false
                    onItemChanged(task.copy(content = initialText))
                },
                onTaskAdded = {
                    isEditing = false
                    onItemChanged(task.copy(content = it.content))
                },
                isAddBtnVisible = false
            )
            
            LaunchedEffect(Unit) {
                focusRequester.requestFocus()
            }
        } else {
            Card(
                modifier = Modifier
                    .height(72.dp)
                    .padding(top = 8.dp, bottom = 8.dp)
                    .fillMaxWidth()
                    .border(
                        borderWidth,
                        color = MaterialTheme.colors.onSurface.copy(alpha = 0.25f),
                        shape = RoundedCornerShape(10)
                    )
                    .clickable { 
                        isEditing = true
                    }
                    .alpha(opacity),
                elevation = 0.dp
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(
                            checked = task.isChecked,
                            onCheckedChange = { onItemChanged(task.copy(isChecked = it)) },
                            colors = CheckboxDefaults.colors(checkedColor = MaterialTheme.colors.primary)
                        )
                        val lineColor = MaterialTheme.colors.onSurface
                        Box(
                            modifier = Modifier
                                .drawWithContent {
                                    drawContent()
                                    drawRect(
                                        color = lineColor, // Can't use directly, apparently
                                        topLeft = Offset(0f, size.height / 2),
                                        size = Size(strikeWidth * size.width, 2.dp.value)
                                    )
                                }
                        ) {
                            Text(textAlign = TextAlign.Left, overflow = TextOverflow.Ellipsis, text = task.content)
                        }
                    }

                    IconButton(
                        onClick = { onDeleteClick() }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Delete"
                        )
                    }
                }
            }
        }
    }
}