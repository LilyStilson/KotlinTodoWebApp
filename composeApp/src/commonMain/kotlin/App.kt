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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import theme.DarkThemeColors
import theme.LightThemeColors

data class Task(val content: String, var isChecked: Boolean = false)

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun App(testTasks: List<Task> = listOf()) {
    MaterialTheme(
        colors = if(isSystemInDarkTheme()) DarkThemeColors else LightThemeColors
    ) {
        Surface(Modifier.fillMaxSize(), ) {
            var current by rememberSaveable { mutableStateOf(TextFieldValue("")) }

            var doneSelected by rememberSaveable { mutableStateOf(false) }
            var notDoneSelected by rememberSaveable { mutableStateOf(false) }
            // true - done, false - not done, null - don't filter
            // also, this is cursed :D
            val filter: Boolean? = if (doneSelected || notDoneSelected) if (doneSelected) true else false else null
            val tasks by rememberSaveable { mutableStateOf(mutableStateListOf(*testTasks.toTypedArray())) }
            
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
                        text = current,
                        onTextChanged = { 
                            current = it
                        },
                        onTaskAdded = {
                            tasks += Task(current.text, false)
                            current = TextFieldValue("")
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
                    tasks = tasks,
                    onItemChecked = { idx, value -> tasks[idx] = tasks[idx].copy(isChecked = value) },
                    onItemChanged = { idx, text -> tasks[idx] = tasks[idx].copy(content = text) },
                    onItemDeleted = { idx -> tasks.removeAt(idx) },
                    filter = filter
                )
            }
        }
    }
}