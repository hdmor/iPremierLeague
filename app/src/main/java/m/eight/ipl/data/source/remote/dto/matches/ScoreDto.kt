package m.eight.ipl.data.source.remote.dto.matches


import com.google.gson.annotations.SerializedName
import m.eight.ipl.data.source.remote.dto.seasons.WinnerDto

data class ScoreDto(
    @SerializedName("duration")
    val duration: String,
    @SerializedName("fullTime")
    val fullTimeDto: FullTimeDto,
    @SerializedName("halfTime")
    val halfTimeDto: HalfTimeDto,
    @SerializedName("winner")
    val winner: WinnerDto?
)