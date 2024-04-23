package m.eight.ipl.data.source.remote.dto.standings


import com.google.gson.annotations.SerializedName
import m.eight.ipl.data.source.remote.dto.matches.CompetitionDto
import m.eight.ipl.data.source.remote.dto.matches.FiltersDto
import m.eight.ipl.data.source.remote.dto.seasons.AreaDto
import m.eight.ipl.data.source.remote.dto.seasons.SeasonDto
import m.eight.ipl.domain.model.Standing

data class StandingsDto(
    @SerializedName("area")
    val areaDto: AreaDto,
    @SerializedName("competition")
    val competition: CompetitionDto,
    @SerializedName("filters")
    val filters: FiltersDto,
    @SerializedName("season")
    val season: SeasonDto,
    @SerializedName("standings")
    val standingsDto: List<StandingDto>
)

fun StandingsDto.toStandings(): List<Standing> = standingsDto[0].tableDto.map { it.toStanding() }