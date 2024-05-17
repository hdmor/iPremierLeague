package m.eight.ipl.data.source.remote.dto.matches


import com.google.gson.annotations.SerializedName

data class ResultSetDto(
    @SerializedName("count")
    val count: Int,
    @SerializedName("first")
    val first: String,
    @SerializedName("last")
    val last: String,
    @SerializedName("played")
    val played: Int
)