package m.eight.ipl.data.source.remote.dto.matches


import com.google.gson.annotations.SerializedName

data class CompetitionDto(
    @SerializedName("code")
    val code: String,
    @SerializedName("emblem")
    val emblem: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("type")
    val type: String
)