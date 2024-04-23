package m.eight.ipl.presentation.standings

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import m.eight.ipl.common.Resource
import m.eight.ipl.domain.model.Standing
import m.eight.ipl.domain.usecase.GetStandingsUseCase
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class StandingsViewModel @Inject constructor(savedStateHandle: SavedStateHandle, private val getStandingsUseCase: GetStandingsUseCase) : ViewModel() {

    private val _state = mutableStateOf(StandingsState())
    val state: State<StandingsState> get() = _state

    init {
        savedStateHandle.get<String>("startDate")?.let { startDate ->
            val parsedDate = LocalDate.parse(startDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
            savedStateHandle.get<Int>("currentMatchDay")?.let { currentMatchDay ->
                getStandings(currentSeason = parsedDate.year, currentMatchDay = currentMatchDay)
            }
        }
    }

    private fun getStandings(currentSeason: Int, currentMatchDay: Int) {
        getStandingsUseCase(currentSeason, currentMatchDay).onEach { result ->
            when (result) {
                is Resource.Error -> _state.value = state.value.copy(isLoading = false, error = result.message ?: "An unexpected error occurred.")
                is Resource.Loading -> _state.value = state.value.copy(isLoading = true)
                is Resource.Success -> _state.value = state.value.copy(isLoading = false, standings = result.data ?: emptyList())
            }
        }.launchIn(viewModelScope)
    }
}

data class StandingsState(
    val isLoading: Boolean = false,
    val standings: List<Standing> = emptyList(),
    val error: String = ""
)