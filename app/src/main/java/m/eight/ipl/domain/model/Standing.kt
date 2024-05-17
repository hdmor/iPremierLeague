package m.eight.ipl.domain.model

data class Standing(
    val position: Int,
    val team: Team,
    val playedGames: Int,
    val form: String,
    val won: Int,
    val draw: Int,
    val lost: Int,
    val points: Int,
    val goalsFor: Int,
    val goalsAgainst: Int,
    val goalDifference: Int
)
