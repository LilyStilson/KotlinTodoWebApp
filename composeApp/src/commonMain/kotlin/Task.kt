import kotlin.random.Random

data class Task(
    val content: String,
    var isChecked: Boolean = false,
    val key: Int = Random.nextInt(),
)
