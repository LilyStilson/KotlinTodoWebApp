import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import theme.DarkThemeColors
import theme.LightThemeColors

data class Task(val content: String, var isChecked: Boolean = false)

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun App(testTasks: List<Task> = listOf()) {
    MaterialTheme(
        colors = if(isSystemInDarkTheme()) DarkThemeColors else LightThemeColors
    ) {
        Surface(Modifier.fillMaxSize()) {
            var current by rememberSaveable { mutableStateOf(TextFieldValue("")) }

            var doneSelected by rememberSaveable { mutableStateOf(false) }
            var notDoneSelected by rememberSaveable { mutableStateOf(false) }
            // true - done, false - not done, null - don't filter
            // also, this is cursed :D
            val filter: Boolean? = if (doneSelected || notDoneSelected) if (doneSelected) true else false else null
            val tasks by rememberSaveable { mutableStateOf(mutableStateListOf(*testTasks.toTypedArray())) }

            val chipsOpacity by animateFloatAsState(
                targetValue = if (tasks.isNotEmpty()) 1f else 0f,
                animationSpec = TweenSpec(250)
            )

            Column(
                Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                TodoInput(
                    text = current,
                    onTextChanged = { current = it },
                    onTaskAdded = {
                        tasks += Task(current.text, false)
                        current = TextFieldValue("")
                    }
                )
                if (tasks.isNotEmpty()) {
                    Row(
                        modifier = Modifier
                            .alpha(chipsOpacity),
                    ) {
                        FilterChip(
                            selected = doneSelected,
                            onClick = {
                                doneSelected = !doneSelected
                                notDoneSelected = false
                            },
                            leadingIcon = {
                                Icon(Icons.Default.Done, contentDescription = "Done")
                            },
                            colors = ChipDefaults.filterChipColors(
                                selectedBackgroundColor = MaterialTheme.colors.secondary
                            )
                        ) {
                            Text("Done")
                        }
                        Spacer(modifier = Modifier.padding(4.dp))
                        FilterChip(
                            selected = notDoneSelected,
                            onClick = {
                                doneSelected = false
                                notDoneSelected = !notDoneSelected
                            },
                            leadingIcon = {
                                Icon(Icons.Default.Pending, contentDescription = "To do")
                            },
                            colors = ChipDefaults.filterChipColors(
                                selectedBackgroundColor = MaterialTheme.colors.secondary
                            )
                        ) {
                            Text("To do")
                        }
                    }
                }
                val filteredTasks = if (filter != null) tasks.filter { task -> task.isChecked == filter } else tasks
                if (filteredTasks.isNotEmpty()) {
                    AnimatedTasksList(
                        tasks = filteredTasks,
                        onItemChecked = { idx, value -> tasks[idx] = tasks[idx].copy(isChecked = value) },
                        onItemDeleted = { idx -> tasks.removeAt(idx) }
                    )
                } else {
                    if (tasks.isNotEmpty())
                        Text("No items found", fontSize = 2.em)
                }
            }
        }
    }
}