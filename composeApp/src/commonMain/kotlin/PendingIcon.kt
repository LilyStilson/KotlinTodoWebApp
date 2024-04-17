import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

@Composable
fun rememberPending(): ImageVector {
    return remember {
        ImageVector.Builder(
            name = "pending",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 40.0f,
            viewportHeight = 40.0f
        ).apply {
            path(
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                stroke = null,
                strokeAlpha = 1f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(11.375f, 22.125f)
                quadToRelative(0.875f, 0f, 1.5f, -0.625f)
                reflectiveQuadTo(13.5f, 20f)
                quadToRelative(0f, -0.875f, -0.625f, -1.5f)
                reflectiveQuadToRelative(-1.5f, -0.625f)
                quadToRelative(-0.917f, 0f, -1.542f, 0.625f)
                reflectiveQuadTo(9.208f, 20f)
                quadToRelative(0f, 0.875f, 0.625f, 1.5f)
                reflectiveQuadToRelative(1.542f, 0.625f)
                close()
                moveToRelative(8.625f, 0f)
                quadToRelative(0.875f, 0f, 1.5f, -0.625f)
                reflectiveQuadToRelative(0.625f, -1.5f)
                quadToRelative(0f, -0.875f, -0.625f, -1.5f)
                reflectiveQuadToRelative(-1.5f, -0.625f)
                quadToRelative(-0.875f, 0f, -1.5f, 0.625f)
                reflectiveQuadToRelative(-0.625f, 1.5f)
                quadToRelative(0f, 0.875f, 0.625f, 1.5f)
                reflectiveQuadToRelative(1.5f, 0.625f)
                close()
                moveToRelative(8.625f, 0f)
                quadToRelative(0.875f, 0f, 1.5f, -0.625f)
                reflectiveQuadToRelative(0.625f, -1.5f)
                quadToRelative(0f, -0.875f, -0.625f, -1.5f)
                reflectiveQuadToRelative(-1.5f, -0.625f)
                quadToRelative(-0.917f, 0f, -1.542f, 0.625f)
                reflectiveQuadToRelative(-0.625f, 1.5f)
                quadToRelative(0f, 0.875f, 0.625f, 1.5f)
                reflectiveQuadToRelative(1.542f, 0.625f)
                close()
                moveTo(20f, 36.375f)
                quadToRelative(-3.375f, 0f, -6.375f, -1.292f)
                quadToRelative(-3f, -1.291f, -5.208f, -3.521f)
                quadToRelative(-2.209f, -2.229f, -3.5f, -5.208f)
                quadTo(3.625f, 23.375f, 3.625f, 20f)
                quadToRelative(0f, -3.417f, 1.292f, -6.396f)
                quadToRelative(1.291f, -2.979f, 3.521f, -5.208f)
                quadToRelative(2.229f, -2.229f, 5.208f, -3.5f)
                reflectiveQuadTo(20f, 3.625f)
                quadToRelative(3.417f, 0f, 6.396f, 1.292f)
                quadToRelative(2.979f, 1.291f, 5.208f, 3.5f)
                quadToRelative(2.229f, 2.208f, 3.5f, 5.187f)
                reflectiveQuadTo(36.375f, 20f)
                quadToRelative(0f, 3.375f, -1.292f, 6.375f)
                quadToRelative(-1.291f, 3f, -3.5f, 5.208f)
                quadToRelative(-2.208f, 2.209f, -5.187f, 3.5f)
                quadToRelative(-2.979f, 1.292f, -6.396f, 1.292f)
                close()
                moveToRelative(0f, -2.625f)
                quadToRelative(5.75f, 0f, 9.75f, -4.021f)
                reflectiveQuadToRelative(4f, -9.729f)
                quadToRelative(0f, -5.75f, -4f, -9.75f)
                reflectiveQuadToRelative(-9.75f, -4f)
                quadToRelative(-5.708f, 0f, -9.729f, 4f)
                quadToRelative(-4.021f, 4f, -4.021f, 9.75f)
                quadToRelative(0f, 5.708f, 4.021f, 9.729f)
                quadTo(14.292f, 33.75f, 20f, 33.75f)
                close()
                moveTo(20f, 20f)
                close()
            }
        }.build()
    }
}

val Icons.Filled.Pending: ImageVector
    @Composable
    get() = rememberPending()