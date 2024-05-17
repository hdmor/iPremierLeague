package m.eight.ipl.data.source.remote.dto.seasons


import com.google.gson.annotations.SerializedName

data class SeasonsDto(
    @SerializedName("area")
    val areaDto: AreaDto,
    @SerializedName("code")
    val code: String,
    @SerializedName("currentSeason")
    val currentSeasonDto: CurrentSeasonDto,
    @SerializedName("emblem")
    val emblem: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("lastUpdated")
    val lastUpdated: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("seasons")
    val seasonDtos: List<SeasonDto>,
    @SerializedName("type")
    val type: String
)