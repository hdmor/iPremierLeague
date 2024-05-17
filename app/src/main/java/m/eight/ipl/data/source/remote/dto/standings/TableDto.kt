package m.eight.ipl.data.source.remote.dto.standings


import com.google.gson.annotations.SerializedName
import m.eight.ipl.domain.model.Standing

data class TableDto(
    @SerializedName("draw")
    val draw: Int,
    @SerializedName("form")
    val form: String,
    @SerializedName("goalDifference")
    val goalDifference: Int,
    @SerializedName("goalsAgainst")
    val goalsAgainst: Int,
    @SerializedName("goalsFor")
    val goalsFor: Int,
    @SerializedName("lost")
    val lost: Int,
    @SerializedName("playedGames")
    val playedGames: Int,
    @SerializedName("points")
    val points: Int,
    @SerializedName("position")
    val position: Int,
    @SerializedName("team")
    val teamDto: TeamDto,
    @SerializedName("won")
    val won: Int
)

fun TableDto.toStanding(): Standing =
    Standing(position, teamDto.toTeam(), playedGames, form, won, draw, lost, points, goalsFor, goalsAgainst, goalDifference)