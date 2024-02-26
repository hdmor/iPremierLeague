package m.eight.ipl.data.source.remote.dto.seasons


import com.google.gson.annotations.SerializedName

data class AreaDto(
    @SerializedName("code")
    val code: String,
    @SerializedName("flag")
    val flag: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)