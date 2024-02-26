package m.eight.ipl.domain.model

data class Match(
    val id: Int,
    val utcDate: String,
    val status: String,
    val lastUpdated: String,
    val homeTeam: Team,
    val awayTeam: Team,
    val score: Score,
    val referees: List<Referee>
)
