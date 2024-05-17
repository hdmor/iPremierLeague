package m.eight.ipl.presentation.standings

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import m.eight.ipl.R

@SuppressLint("RememberReturnType")
@Composable
fun StandingsScreen(viewModel: StandingsViewModel = hiltViewModel()) {

    val state = viewModel.state.value

    if (state.isLoading) Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { CircularProgressIndicator() }
    if (state.standings.isNotEmpty()) {
        Column(modifier = Modifier.fillMaxSize()) {
            Text(text = "Season 2023-24", modifier = Modifier.padding(8.dp))
            HorizontalDivider()
            val configuration = LocalConfiguration.current
            when (configuration.orientation) {
                Configuration.ORIENTATION_LANDSCAPE -> {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "Club")
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
                            CustomText(text = "MP")
                            CustomText(text = "W")
                            CustomText(text = "D")
                            CustomText(text = "L")
                            CustomText(text = "GF")
                            CustomText(text = "GA")
                            CustomText(text = "GD")
                            CustomText(text = "Pts", fontWeight = FontWeight.SemiBold)
                            Text(text = "Last 5", modifier = Modifier.width(100.dp), textAlign = TextAlign.Center)
                        }
                    }
                    HorizontalDivider()
                    LazyColumn(modifier = Modifier.fillMaxSize(), contentPadding = PaddingValues(vertical = 4.dp)) {
                        items(items = state.standings, key = { it.team.id }) { standing ->
                            Row(
                                modifier = Modifier.fillMaxWidth().padding(8.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(
                                    modifier = Modifier.wrapContentWidth(),
                                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = standing.position.toString(),
                                        modifier = Modifier.wrapContentWidth().widthIn(20.dp),
                                        textAlign = TextAlign.Center
                                    )
                                    AsyncImage(
                                        model = ImageRequest.Builder(LocalContext.current).data(standing.team.crest)
                                            .decoderFactory(SvgDecoder.Factory()).build(),
                                        contentDescription = standing.team.tla,
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Text(text = standing.team.name)
                                }
                                Row(
                                    modifier = Modifier.wrapContentWidth(),
                                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    CustomText(text = standing.playedGames.toString())
                                    CustomText(text = standing.won.toString())
                                    CustomText(text = standing.draw.toString())
                                    CustomText(text = standing.lost.toString())
                                    CustomText(text = standing.goalsFor.toString())
                                    CustomText(text = standing.goalsAgainst.toString())
                                    CustomText(text = standing.goalDifference.toString())
                                    CustomText(text = standing.points.toString(), fontWeight = FontWeight.SemiBold)
                                    Row(
                                        modifier = Modifier.width(100.dp),
                                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        standing.form.split(",").forEach {
                                            Image(
                                                painter = painterResource(
                                                    id = when (it) {
                                                        "W" -> R.drawable.win
                                                        "D" -> R.drawable.draw
                                                        "L" -> R.drawable.loss
                                                        else -> R.drawable.none
                                                    }
                                                ), contentDescription = it
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                Configuration.ORIENTATION_PORTRAIT -> {
                    var scrollStateWidth by remember { mutableIntStateOf(0) }
                    val density = LocalDensity.current
                    val scrollStateWidthDp = remember(scrollStateWidth, density) {
                        with(density) {
                            scrollStateWidth.toDp()
                        }
                    }
                    Row(modifier = Modifier.fillMaxSize()) {
                        LazyColumn(
                            modifier = Modifier.fillMaxWidth(.45f).fillMaxHeight(),
                            verticalArrangement = Arrangement.SpaceEvenly
                        ) {
                            item {
                                Row(
                                    modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
                                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) { Text(text = "Club") }
                                Spacer(modifier = Modifier.height(8.dp))
                                HorizontalDivider()
                            }
                            items(items = state.standings, key = { it.team.id }) { standing ->
                                Row(
                                    modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
                                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = standing.position.toString(),
                                        modifier = Modifier.wrapContentWidth().widthIn(20.dp),
                                        textAlign = TextAlign.Center
                                    )
                                    AsyncImage(
                                        model = ImageRequest.Builder(LocalContext.current).data(standing.team.crest)
                                            .decoderFactory(SvgDecoder.Factory()).build(),
                                        contentDescription = standing.team.tla,
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Text(text = standing.team.shortName)
                                }
                            }
                        }
                        LazyColumn(
                            modifier = Modifier.wrapContentWidth().fillMaxHeight().horizontalScroll(rememberScrollState())
                                .onSizeChanged { scrollStateWidth = it.width },
                            verticalArrangement = Arrangement.SpaceEvenly
                        ) {
                            item {
                                Row(
                                    modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
                                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    CustomText(text = "MP")
                                    CustomText(text = "W")
                                    CustomText(text = "D")
                                    CustomText(text = "L")
                                    CustomText(text = "GF")
                                    CustomText(text = "GA")
                                    CustomText(text = "GD")
                                    CustomText(text = "Pts", fontWeight = FontWeight.SemiBold)
                                    Text(text = "Last 5", modifier = Modifier.width(100.dp), textAlign = TextAlign.Center)
                                }
                                Spacer(modifier = Modifier.height(8.dp))
                                HorizontalDivider(modifier = Modifier.width(scrollStateWidthDp))
                            }
                            items(items = state.standings, key = { it.team.id }) { standing ->
                                Row(
                                    modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
                                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    CustomText(text = standing.playedGames.toString())
                                    CustomText(text = standing.won.toString())
                                    CustomText(text = standing.draw.toString())
                                    CustomText(text = standing.lost.toString())
                                    CustomText(text = standing.goalsFor.toString())
                                    CustomText(text = standing.goalsAgainst.toString())
                                    CustomText(text = standing.goalDifference.toString())
                                    CustomText(text = standing.points.toString(), fontWeight = FontWeight.SemiBold)
                                    Row(
                                        modifier = Modifier.width(100.dp),
                                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        standing.form.split(",").forEach {
                                            Image(
                                                painter = painterResource(
                                                    id = when (it) {
                                                        "W" -> R.drawable.win
                                                        "D" -> R.drawable.draw
                                                        "L" -> R.drawable.loss
                                                        else -> R.drawable.none
                                                    }
                                                ), contentDescription = it
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                else -> println("Unknown screen rotation!")
            }
        }
    }
}

@Composable
private fun CustomText(text: String, fontWeight: FontWeight? = null) {
    Text(text = text, modifier = Modifier.width(30.dp), fontWeight = fontWeight, textAlign = TextAlign.Center)
}