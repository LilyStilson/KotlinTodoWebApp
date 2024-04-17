package theme

import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color

val LightThemeColors = lightColors(
    background = Color.White,
    surface = Color.White,
    
    primary = Color(0xFF02a2ee),
    primaryVariant = Color(0xFF02a2ee).copy(alpha = 0.75f),
    secondary = Color(0xFFed0167),
    secondaryVariant = Color(0xFFed0167).copy(alpha = 0.75f),
    error = Color(0xFFd92f31),

    onPrimary = Color.White,
    onSecondary = Color.White,
    onSurface = Color.Black,
    onBackground = Color.Black,
    onError = Color.White,
)

    