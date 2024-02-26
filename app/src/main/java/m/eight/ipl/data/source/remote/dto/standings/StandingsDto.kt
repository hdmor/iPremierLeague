package m.eight.ipl.data.source.remote.dto.standings


import com.google.gson.annotations.SerializedName
import m.eight.ipl.data.source.remote.dto.matches.CompetitionDto
import m.eight.ipl.data.source.remote.dto.matches.FiltersDto
import m.eight.ipl.data.source.remote.dto.seasons.SeasonDto

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
    val standingDtos: List<StandingDto>
)