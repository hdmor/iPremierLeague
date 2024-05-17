package m.eight.ipl.data.source.remote.dto.seasons


import com.google.gson.annotations.SerializedName
import m.eight.ipl.domain.model.Champion

data class WinnerDto(
    @SerializedName("address")
    val address: String,
    @SerializedName("clubColors")
    val clubColors: String,
    @SerializedName("crest")
    val crest: String,
    @SerializedName("founded")
    val founded: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("lastUpdated")
    val lastUpdated: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("shortName")
    val shortName: String,
    @SerializedName("tla")
    val tla: String,
    @SerializedName("venue")
    val venue: String,
    @SerializedName("website")
    val website: String
)

fun WinnerDto.toChampion(): Champion =
    Champion(
        address = address,
        clubColors = clubColors,
        crest = crest,
        founded = founded,
        id = id,
        lastUpdated = lastUpdated,
        name = name,
        shortName = shortName,
        tla = tla,
        venue = venue,
        website = website
    )