package m.eight.ipl.data.source.remote.dto.seasons


import com.google.gson.annotations.SerializedName
import m.eight.ipl.domain.model.CurrentSeason

data class CurrentSeasonDto(
    @SerializedName("currentMatchday")
    val currentMatchday: Int,
    @SerializedName("endDate")
    val endDate: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("startDate")
    val startDate: String,
    @SerializedName("winner")
    val winner: WinnerDto?
)

fun CurrentSeasonDto.toCurrentSeason(): CurrentSeason =
    CurrentSeason(currentMatchDay = currentMatchday, endDate = endDate, id = id, startDate = startDate, champion = winner?.toChampion())