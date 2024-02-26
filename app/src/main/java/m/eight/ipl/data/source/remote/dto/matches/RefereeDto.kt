package m.eight.ipl.data.source.remote.dto.matches


import com.google.gson.annotations.SerializedName
import m.eight.ipl.domain.model.Referee

data class RefereeDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("nationality")
    val nationality: String,
    @SerializedName("type")
    val type: String
)

fun RefereeDto.toReferee(): Referee = Referee(id = id, name = name, type = type, nationality = nationality)