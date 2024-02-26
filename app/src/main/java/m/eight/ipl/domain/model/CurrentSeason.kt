package m.eight.ipl.domain.model

data class CurrentSeason(
    val currentMatchDay: Int,
    val endDate: String,
    val id: Int,
    val startDate: String,
    val winner: Champion? = null
)
