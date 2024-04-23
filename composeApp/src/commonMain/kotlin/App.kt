import androidx.compose.animation.*
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import theme.DarkThemeColors
import theme.LightThemeColors

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun App(testTasks: List<Task> = listOf()) {
    MaterialTheme(
        colors = if(isSystemInDarkTheme()) DarkThemeColors else LightThemeColors
    ) {
        Surface(Modifier.fillMaxSize(), ) {
            var doneSelected by rememberSaveable { mutableStateOf(false) }
            var notDoneSelected by rememberSaveable { mutableStateOf(false) }
            // true - done, false - not done, null - don't filter
            // also, this is cursed :D
            val filter: Boolean? = if (doneSelected || notDoneSelected) if (doneSelected) true else false else null
            val tasks by remember { mutableStateOf(mutableStateListOf(*testTasks.toTypedArray())) }
            val filteredTasks = if (filter != null) tasks.filter { task -> task.isChecked == filter } else tasks
            
            Column(
                modifier = Modifier
                    .wrapContentWidth(Alignment.CenterHorizontally)
                    .wrapContentHeight()
                    .widthIn(256.dp, 512.dp),
                
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Box {
                    TodoInput(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        onTaskAdded = {
                            tasks += it
                        }
                    )
                }
                AnimatedVisibility(
                    visible = tasks.isNotEmpty(),
                    enter = slideInVertically(initialOffsetY = { it / 2 }) + fadeIn(),
                    exit = slideOutVertically(targetOffsetY = { it / 2 }) + fadeOut(),
                ) {
                    Row {
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
                AnimatedTasksList(
                    tasks = filteredTasks,
                    initialCount = tasks.count(),
                    onItemChanged = { task ->
                        val idx = tasks.indexOfFirst { it.key == task.key }
                        tasks[idx] = task
                    },
                    onItemDeleted = { key ->
                        val idx = tasks.indexOfFirst { it.key == key }
                        tasks.removeAt(idx)
                    }
                )
            }
        }
    }
}