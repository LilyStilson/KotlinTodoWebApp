import androidx.compose.animation.*
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em

@Composable
fun AnimatedTasksList(
    tasks: List<Task>,
    initialCount: Int? = null,
    onItemChanged: (Task) -> Unit,
    onItemDeleted: (Int) -> Unit
) {
    val animatedHeight by animateDpAsState(
        targetValue = if (tasks.isNotEmpty()) 72.dp * if (initialCount != null && initialCount != 0 && tasks.isEmpty()) 1 else tasks.count() else 0.dp,
        animationSpec = TweenSpec(durationMillis = 250)
    )

    Box(contentAlignment = Alignment.Center) {
        LazyColumn(
            modifier = Modifier
                .padding(16.dp)
                .height(animatedHeight)
                .fillMaxWidth(),
        ) {
            items(tasks) { 
                AnimatedVisibility(
                    visible = true,
                    enter = slideInVertically() + fadeIn(),
                    exit = slideOutVertically() + fadeOut()
                ) {
                    TodoItem(
                        it,
                        onItemChanged = { task -> onItemChanged(task) },
                        onDeleteClick = { onItemDeleted(it.key) }
                    )
                }
            }
        }

        if (initialCount != null && initialCount != 0 && tasks.isEmpty()) {
            Text("No items found", fontSize = 2.em)
        }
    }

}