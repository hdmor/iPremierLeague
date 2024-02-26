package m.eight.ipl.data.source.remote.dto.matches


import com.google.gson.annotations.SerializedName

data class AwayTeamDto(
    @SerializedName("crest")
    val crest: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("shortName")
    val shortName: String,
    @SerializedName("tla")
    val tla: String
)