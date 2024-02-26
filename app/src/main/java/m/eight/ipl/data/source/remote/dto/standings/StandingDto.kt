package m.eight.ipl.data.source.remote.dto.standings


import com.google.gson.annotations.SerializedName

data class StandingDto(
    @SerializedName("group")
    val group: Any,
    @SerializedName("stage")
    val stage: String,
    @SerializedName("table")
    val tableDto: List<TableDto>,
    @SerializedName("type")
    val type: String
)