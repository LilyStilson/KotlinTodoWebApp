import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@Composable
fun ErrorLabel(text: String?) {
    if (text != null)
        Text(text, style = TextStyle.Default.copy(MaterialTheme.colors.error))
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TodoInput(
    text: TextFieldValue,
    onTextChanged: (TextFieldValue) -> Unit,
    onTaskAdded: () -> Unit
) {
    var isError by remember { mutableStateOf(false) }
    var errorText by remember { mutableStateOf<String?>(null) }
    Column(
        modifier = Modifier
            .padding(16.dp)
            .widthIn(256.dp, 512.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
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
                keyboardActions = KeyboardActions(onDone = { onTaskAdded() }),
                modifier = Modifier.weight(1f),
                isError = isError,
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    placeholderColor = MaterialTheme.colors.onBackground.copy(alpha = 0.75f)
                ),
            )
            Spacer(modifier = Modifier.width(8.dp))
            IconButton(
                onClick = {
                    if (text.text.isBlank() || text.text.isEmpty()) {
                        isError = true
                        errorText = "You can't add an empty item to a list"
                    } else {
                        onTaskAdded()
                    }
                },
                modifier = Modifier.wrapContentWidth()
            ) {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = "Add item"
                )
            }
        }
        ErrorLabel(errorText)
    }
}