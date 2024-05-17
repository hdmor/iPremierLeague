package m.eight.ipl.data.source.remote.dto.seasons


import com.google.gson.annotations.SerializedName

data class SeasonDto(
    @SerializedName("currentMatchday")
    val currentMatchday: Int?,
    @SerializedName("endDate")
    val endDate: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("startDate")
    val startDate: String,
    @SerializedName("winner")
    val winnerDto: WinnerDto?
)