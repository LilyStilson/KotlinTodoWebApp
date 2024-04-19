import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@Composable
fun ErrorLabel(modifier: Modifier = Modifier, text: String?) {
    if (text != null)
        Text(text, modifier, style = TextStyle.Default.copy(MaterialTheme.colors.error))
}

@Composable
fun TodoInput(
    modifier: Modifier = Modifier,
    text: TextFieldValue,
    onTextChanged: (TextFieldValue) -> Unit,
    onTaskAdded: () -> Unit,
//    isError: Boolean = false,
    isAddBtnVisible: Boolean? = true,
//    errorText: String? = null
) {
    var isError by remember { mutableStateOf(false) }
    var errorText by remember { mutableStateOf<String?>(null) }
    
    fun taskAddHandler() {
        if (text.text.isBlank() || text.text.isEmpty()) {
            isError = true
            errorText = "You can't add an empty item to a list"
        } else {
            onTaskAdded()
        }
    }
    
    Column {
        OutlinedTextField(
            value = text,
            singleLine = true,
            onValueChange = {
                isError = false
                errorText = null
                onTextChanged(it)
            },
            placeholder = { Text("I want to...") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = { taskAddHandler() }),
            modifier = modifier.padding(bottom = 0.dp)
                .onKeyEvent { 
                    if (it.key == Key.Escape) {
                        if (isAddBtnVisible == false) {
                            taskAddHandler()
                        }
                        false
                    } else {
                        false
                    }
                },
            trailingIcon = {
                if (isAddBtnVisible != null) {
                    IconButton(onClick = {
                        taskAddHandler()
                    }) {
                        if (isAddBtnVisible == true)
                            Icon(Icons.Default.Add, contentDescription = "Add task")
                        else if (isAddBtnVisible == false)
                            Icon(Icons.AutoMirrored.Filled.Send, contentDescription = "Save task")
                    }
                }
            },
            isError = isError,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                placeholderColor = MaterialTheme.colors.onBackground.copy(alpha = 0.75f)
            )
        )
        ErrorLabel(modifier.padding(top = 0.dp), errorText)
    }
}