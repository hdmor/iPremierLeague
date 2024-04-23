package m.eight.ipl.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import m.eight.ipl.domain.usecase.GetCurrentSeasonUseCase
import javax.inject.Inject

@HiltViewModel
class CurrentSeasonViewModel @Inject constructor(private val getCurrentSeasonUseCase: GetCurrentSeasonUseCase) : ViewModel() {

    private val _state = mutableStateOf(CurrentSeasonState())
    val state: State<CurrentSeasonState> get() = _state

    init {
        _state.value = state.value.copy(isLoading = true)
        viewModelScope.launch {
            getCurrentSeasonUseCase().apply {
                _state.value = state.value.copy(isLoading = false, startDate = startDate, currentMatchDay = currentMatchDay)
            }
        }
    }
}

data class CurrentSeasonState(
    val isLoading: Boolean = false,
    val startDate: String = "",
    val currentMatchDay: Int = 0,
    val error: String = ""
)