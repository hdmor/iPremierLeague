package m.eight.ipl.data.source.remote.dto.standings


import com.google.gson.annotations.SerializedName
import m.eight.ipl.domain.model.Team

data class TeamDto(
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

fun TeamDto.toTeam(): Team = Team(id = id, name = name, shortName = shortName, tla = tla, crest = crest)