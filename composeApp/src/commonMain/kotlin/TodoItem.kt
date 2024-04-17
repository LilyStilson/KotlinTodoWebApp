import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun TodoItem(
    text: String,
    isChecked: Boolean,
    onChecked: (Boolean) -> Unit,
    onDeleteClick: () -> Unit
) {
    val opacity: Float by animateFloatAsState(
        targetValue = if (isChecked) 0.5f else 1f,
        animationSpec = TweenSpec(250)
    )

    val strikeWidth: Float by animateFloatAsState(
        targetValue = if (isChecked) 1f else 0f,
        animationSpec = TweenSpec(250)
    )

    val borderWidth: Dp by animateDpAsState(
        targetValue = if (isChecked) 0.dp else 1.dp,
        animationSpec = TweenSpec(250)
    )

    Card(
        modifier = Modifier
            .height(72.dp)
            .padding(top = 8.dp, bottom = 8.dp)
            .fillMaxWidth()
            .border(borderWidth, color = MaterialTheme.colors.onSurface.copy(alpha = 0.25f), shape = RoundedCornerShape(10))
            .alpha(opacity),
        elevation = 0.dp
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = isChecked,
                    onCheckedChange = { onChecked(it) },
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
                    Text(textAlign = TextAlign.Left, overflow = TextOverflow.Ellipsis, text = text)
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