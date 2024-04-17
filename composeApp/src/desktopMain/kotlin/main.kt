import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication, 
        title = "Compose Multiplatform Todo App", 
        state = WindowState(
            size = DpSize(1280.dp, 720.dp), 
            position = WindowPosition.Aligned(Alignment.Center)
        )
    ) {
        App()
    }
}

@Preview
@Composable
fun AppDesktopPreview() {
    App(listOf(
        Task("Eat breakfast", true), 
        Task("Buy groceries", false), 
        Task("Send a message to John", true), 
        Task("Fix a bug", false)
    ))
}