package m.eight.ipl.data.source.remote.dto.matches


import com.google.gson.annotations.SerializedName
import m.eight.ipl.domain.model.Score

data class ScoreDto(
    @SerializedName("duration")
    val duration: String,
    @SerializedName("fullTime")
    val fullTimeDto: FullTimeDto,
    @SerializedName("halfTime")
    val halfTimeDto: HalfTimeDto,
    @SerializedName("winner")
    val winner: String?
)

fun ScoreDto.toScore(): Score = Score(winner, duration, fullTimeDto.toTime(), halfTimeDto.toTime())