package m.eight.ipl.domain.model

data class Score(
    val winner: String? = null,
    val duration: String,
    val fullTime: Time,
    val halfTime: Time
)
