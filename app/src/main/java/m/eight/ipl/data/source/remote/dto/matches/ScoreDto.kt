package m.eight.ipl.data.source.remote.dto.matches


import com.google.gson.annotations.SerializedName

data class ScoreDto(
    @SerializedName("duration")
    val duration: String,
    @SerializedName("fullTime")
    val fullTimeDto: FullTimeDto,
    @SerializedName("halfTime")
    val halfTimeDto: HalfTimeDto,
    @SerializedName("winner")
    val winner: Any
)