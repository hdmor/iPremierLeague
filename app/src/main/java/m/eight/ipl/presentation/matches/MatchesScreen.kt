package m.eight.ipl.presentation.matches

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun MatchesScreen(viewModel: MatchesViewModel = hiltViewModel()) {

    val state = viewModel.state.value
    if (state.isLoading) Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { CircularProgressIndicator() }
    if (state.standings.isNotEmpty()) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            items(items = state.standings) { match ->
                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp, vertical = 8.dp).border(1.dp, Color.LightGray, RectangleShape),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.fillMaxWidth(.65f).drawBehind {
                        drawLine(
                            color = Color.LightGray,
                            start = Offset(size.width, 8.dp.toPx()),
                            end = Offset(size.width, size.height - 8.dp.toPx()),
                            strokeWidth = 1.dp.toPx()
                        )
                    }.padding(horizontal = 8.dp, vertical = 4.dp)) {
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Row(
                                modifier = Modifier.wrapContentWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                AsyncImage(
                                    model = ImageRequest.Builder(LocalContext.current).data(match.homeTeam.crest)
                                        .decoderFactory(SvgDecoder.Factory()).build(),
                                    contentDescription = match.homeTeam.tla,
                                    modifier = Modifier.size(16.dp)
                                )
                                Text(text = match.homeTeam.name)
                            }
                            Text(text = match.score.fullTime.home.toString())
                        }
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Row(
                                modifier = Modifier.wrapContentWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                AsyncImage(
                                    model = ImageRequest.Builder(LocalContext.current).data(match.awayTeam.crest)
                                        .decoderFactory(SvgDecoder.Factory()).build(),
                                    contentDescription = match.awayTeam.tla,
                                    modifier = Modifier.size(16.dp)
                                )
                                Text(text = match.awayTeam.name)
                            }
                            Text(text = match.score.fullTime.away.toString())
                        }
                    }
                    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = match.status)
                        val date = LocalDate.parse(match.utcDate, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH))
                        Text(text = "${date.dayOfWeek.toString().substring(0, 3)}, ${date.month} ${date.dayOfMonth}")
                    }
                }
            }
        }
    }
}