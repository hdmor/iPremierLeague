package m.eight.ipl.data.source.remote.dto.matches


import com.google.gson.annotations.SerializedName
import m.eight.ipl.domain.model.Time

data class HalfTimeDto(
    @SerializedName("away")
    val away: Int?,
    @SerializedName("home")
    val home: Int?
)

fun HalfTimeDto.toTime(): Time = Time(away = away, home = home)