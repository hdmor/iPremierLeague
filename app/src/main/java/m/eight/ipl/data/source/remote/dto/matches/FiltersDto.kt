package m.eight.ipl.data.source.remote.dto.matches


import com.google.gson.annotations.SerializedName

data class FiltersDto(
    @SerializedName("matchday")
    val matchday: String,
    @SerializedName("season")
    val season: String
)