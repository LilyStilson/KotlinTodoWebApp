package theme

import androidx.compose.material.darkColors
import androidx.compose.ui.graphics.Color

val DarkThemeColors = darkColors(
    background = Color(0xFF262626),
    surface = Color(0xFF262626),

    primary = Color(0xFF02a2ee),
    primaryVariant = Color(0xFF02a2ee).copy(alpha = 0.75f),
    secondary = Color(0xFFed0167),
    secondaryVariant = Color(0xFFed0167).copy(alpha = 0.75f),
    error = Color(0xFFd92f31),

    onPrimary = Color.White,
    onSecondary = Color.White,
    onSurface = Color.White,
    onBackground = Color.White,
    onError = Color.White
)