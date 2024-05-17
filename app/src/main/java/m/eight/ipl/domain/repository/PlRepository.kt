package m.eight.ipl.domain.repository

import m.eight.ipl.data.source.remote.dto.matches.MatchesDto
import m.eight.ipl.data.source.remote.dto.seasons.SeasonsDto
import m.eight.ipl.data.source.remote.dto.standings.StandingsDto

interface PlRepository {

    suspend fun getSeasons(): SeasonsDto
    suspend fun getMatches(matchDay: Int): MatchesDto
    suspend fun getStandings(season: Int, matchDay: Int): StandingsDto
}