import androidx.compose.animation.*
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import kotlinx.coroutines.delay

private fun <T> List<T>.filterBy(filter: ((T) -> Boolean)?): List<T> =
    if (filter != null) filter { filter(it) } else this


@Composable
fun AnimatedTasksList(
    tasks: List<Task>,
    onItemChecked: (Int, Boolean) -> Unit,
    onItemChanged: (Int, String) -> Unit,
    onItemDeleted: (Int) -> Unit,
    filter: Boolean?
) {
    val filteredTasks = if (filter != null) tasks.filter { task -> task.isChecked == filter } else tasks
    
    val animatedHeight by animateDpAsState(
        targetValue = if (tasks.isNotEmpty()) 72.dp * filteredTasks.count() else 0.dp,
        animationSpec = TweenSpec(durationMillis = 250)
    )

    Box(contentAlignment = Alignment.Center) {
        LazyColumn(
            modifier = Modifier
                .padding(16.dp)
                .height(animatedHeight)
                .widthIn(256.dp, 512.dp),
        ) {
            itemsIndexed(filteredTasks) { idx, task ->
                AnimatedVisibility(
                    visible = true,
                    enter = slideInVertically() + fadeIn(),
                    exit = slideOutVertically() + fadeOut()
                ) {
                    TodoItem(
                        task.content,
                        task.isChecked,
                        onChecked = { onItemChecked(idx, it) },
                        onItemChanged = { onItemChanged(idx, it) },
                        onDeleteClick = { onItemDeleted(idx) }
                    )
                }
            }
        }
        if (filteredTasks.isEmpty() && tasks.isNotEmpty()) {
            Text("No items found", fontSize = 2.em)
        }
    }

}