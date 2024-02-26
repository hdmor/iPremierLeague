package m.eight.ipl.data.source.remote

import m.eight.ipl.data.source.remote.dto.matches.MatchesDto
import m.eight.ipl.data.source.remote.dto.seasons.SeasonsDto
import m.eight.ipl.data.source.remote.dto.standings.StandingsDto
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiService {

    @GET("/v4/competitions/PL")
    suspend fun getSeasons(@Header("X-Auth-Token") apiToken: String = API_TOKEN): SeasonsDto

    @GET("/v4/competitions/PL/matches")
    suspend fun getMatches(@Header("X-Auth-Token") apiToken: String = API_TOKEN, @Query("matchday") matchDay: Int): MatchesDto

    @GET("/v4/competitions/PL/standings")
    suspend fun getStandings(
        @Header("X-Auth-Token") apiToken: String = API_TOKEN,
        @Query("season") season: Int,
        @Query("matchday") matchDay: Int
    ): StandingsDto

    companion object {
        const val API_TOKEN = "efa7dd138dbf469a8ab5a4aad3577452"
        const val BASE_URL = "https://api.football-data.org/"
    }
}