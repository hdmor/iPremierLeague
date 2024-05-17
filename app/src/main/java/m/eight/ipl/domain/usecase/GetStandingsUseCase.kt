package m.eight.ipl.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import m.eight.ipl.common.Resource
import m.eight.ipl.data.source.remote.dto.standings.toStandings
import m.eight.ipl.domain.model.Standing
import m.eight.ipl.domain.repository.PlRepository
import retrofit2.HttpException
import java.io.IOException

class GetStandingsUseCase(private val repository: PlRepository) {

    operator fun invoke(season: Int, matchDay: Int): Flow<Resource<List<Standing>>> = flow {
        try {
            emit(Resource.Loading())
            val standings = repository.getStandings(season = season, matchDay = matchDay).toStandings()
            emit(Resource.Success(data = standings))
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage ?: "An unexpected error occurred."))
        } catch (io: IOException) {
            emit(Resource.Error(message = "Couldn't reach server, Check your internet connection."))
        }
    }
}