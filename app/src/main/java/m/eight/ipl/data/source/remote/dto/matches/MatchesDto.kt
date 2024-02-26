package m.eight.ipl.data.source.remote.dto.matches


import com.google.gson.annotations.SerializedName

data class MatchesDto(
    @SerializedName("competition")
    val competitionDto: CompetitionDto,
    @SerializedName("filters")
    val filtersDto: FiltersDto,
    @SerializedName("matches")
    val matches: List<MatchDto>,
    @SerializedName("resultSet")
    val resultSetDto: ResultSetDto
)