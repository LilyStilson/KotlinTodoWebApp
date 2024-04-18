import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue

@Composable
fun ErrorLabel(text: String?) {
    if (text != null)
        Text(text, style = TextStyle.Default.copy(MaterialTheme.colors.error))
}

@Composable
fun TodoInput(
    modifier: Modifier = Modifier,
    text: TextFieldValue,
    onTextChanged: (TextFieldValue) -> Unit,
    onTaskAdded: () -> Unit,
    isError: Boolean = false,
    errorText: String? = null
) {
    Column {
        OutlinedTextField(
            value = text,
            singleLine = true,
            onValueChange = {
                onTextChanged(it)
            },
            placeholder = { Text("I want to...") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = { onTaskAdded() }),
            modifier = modifier
                .onKeyEvent { 
                    if (it.key == Key.Escape) {
                        onTaskAdded()
                        false
                    } else {
                        false
                    }
                },
            isError = isError,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                placeholderColor = MaterialTheme.colors.onBackground.copy(alpha = 0.75f)
            )
        )
        ErrorLabel(errorText)
    }
}