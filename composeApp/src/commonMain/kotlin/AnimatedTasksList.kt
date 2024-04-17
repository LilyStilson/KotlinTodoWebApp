import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AnimatedTasksList(
    tasks: List<Task>,
    onItemChecked: (Int, Boolean) -> Unit,
    onItemDeleted: (Int) -> Unit
) {
    val animatedHeight by animateDpAsState(
        targetValue = if (tasks.isNotEmpty()) 72.dp * tasks.count() else 0.dp,
        animationSpec = TweenSpec(durationMillis = 250)
    )

    LazyColumn(
        modifier = Modifier
            .padding(16.dp)
            .height(animatedHeight)
            .widthIn(256.dp, 512.dp)
    ) {
        itemsIndexed(tasks) { idx, task ->
            TodoItem(
                task.content,
                task.isChecked,
                onChecked = { onItemChecked(idx, it) },
                onDeleteClick = { onItemDeleted(idx) }
            )
        }
    }
}