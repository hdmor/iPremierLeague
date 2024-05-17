package m.eight.ipl.data.source.remote.dto.matches


import com.google.gson.annotations.SerializedName
import m.eight.ipl.data.source.remote.dto.seasons.AreaDto
import m.eight.ipl.data.source.remote.dto.seasons.SeasonDto
import m.eight.ipl.domain.model.Match

data class MatchDto(
    @SerializedName("area")
    val areaDto: AreaDto,
    @SerializedName("awayTeam")
    val awayTeamDto: AwayTeamDto,
    @SerializedName("competition")
    val competitionDto: CompetitionDto,
    @SerializedName("group")
    val group: Any?,
    @SerializedName("homeTeam")
    val homeTeamDto: HomeTeamDto,
    @SerializedName("id")
    val id: Int,
    @SerializedName("lastUpdated")
    val lastUpdated: String,
    @SerializedName("matchday")
    val matchday: Int,
    @SerializedName("odds")
    val oddsDto: OddsDto,
    @SerializedName("referees")
    val referees: List<RefereeDto>,
    @SerializedName("score")
    val scoreDto: ScoreDto,
    @SerializedName("season")
    val season: SeasonDto,
    @SerializedName("stage")
    val stage: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("utcDate")
    val utcDate: String
)

fun MatchDto.toMatch(): Match =
    Match(id, utcDate, status, lastUpdated, homeTeamDto.toTeam(), awayTeamDto.toTeam(), scoreDto.toScore(), referees.map { it.toReferee() })