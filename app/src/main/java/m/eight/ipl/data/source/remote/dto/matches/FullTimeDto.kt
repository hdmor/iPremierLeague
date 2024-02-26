package m.eight.ipl.data.source.remote.dto.matches


import com.google.gson.annotations.SerializedName

data class FullTimeDto(
    @SerializedName("away")
    val away: Any?,
    @SerializedName("home")
    val home: Any?
)