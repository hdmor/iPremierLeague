package m.eight.ipl.presentation.matches

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun MatchesScreen(viewModel: MatchesViewModel = hiltViewModel()) {

    val state = viewModel.state.value
    if (state.isLoading) Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { CircularProgressIndicator() }
    if (state.standings.isNotEmpty()) {
        Box(modifier = Modifier.fillMaxSize().padding(8.dp), contentAlignment = Alignment.Center) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(items = state.standings) { match ->
                    Row(
                        modifier = Modifier.fillMaxWidth().border(1.dp, Color.LightGray, RectangleShape),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.fillMaxWidth(.6f).drawBehind {
                            drawLine(
                                color = Color.LightGray,
                                start = Offset(size.width, 8.dp.toPx()),
                                end = Offset(size.width, size.height - 8.dp.toPx()),
                                strokeWidth = 1.dp.toPx()
                            )
                        }.padding(horizontal = 8.dp, vertical = 4.dp)) {
                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                Text(text = match.homeTeam.shortName, fontSize = 12.sp)
                                Text(text = match.score.fullTime.home.toString(), fontSize = 12.sp)
                            }
                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                Text(text = match.awayTeam.shortName, fontSize = 12.sp)
                                Text(text = match.score.fullTime.away.toString(), fontSize = 12.sp)
                            }
                        }
                        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(text = match.status, fontSize = 8.sp)
                            val date = LocalDate.parse(match.utcDate, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH))
                            Text(text = "${date.dayOfWeek.toString().substring(0, 3)}, ${date.month} ${date.dayOfMonth}", fontSize = 8.sp)
                        }
                    }
                }
            }
        }
    }
}

/*
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
private fun MatchesScreenPreview() {
    IPLTheme {
        val testList = listOf(
            Match(
                436220, "2024-03-09T12:30:00Z", "FINISHED", "2024-03-10T00:21:22Z",
                Team(66, "Manchester United FC", "Man United", "MUN", "https://crests.football-data.org/66.png"),
                Team(62, "Everton FC", "Everton", "EVE", "https://crests.football-data.org/62.png"),
                Score("HOME_TEAM", "REGULAR", Time(2, 0), Time(2, 0)),
                listOf(Referee(11430, "Simon Hooper", "REFEREE", "England"))
            ),
            Match(
                436213, "2024-03-09T15:00:00Z", "FINISHED", "2024-03-10T00:21:22Z",
                Team(1044, "AFC Bournemouth", "Bournemouth", "BOU", "https://crests.football-data.org/1044.png"),
                Team(356, "Sheffield United FC", "Sheffield Utd", "SHE", "https://crests.football-data.org/356.svg"),
                Score("DRAW", "REGULAR", Time(2, 2), Time(0, 1)),
                listOf(Referee(11580, "Anthony Taylor", "REFEREE", "England"))
            ),
            Match(
                436218, "2024-03-09T15:00:00Z", "FINISHED", "2024-03-10T00:21:22Z",
                Team(354, "Crystal Palace FC", "Crystal Palace", "CRY", "https://crests.football-data.org/354.png"),
                Team(389, "Luton Town FC", "Luton Town", "LUT", "https://crests.football-data.org/389.png"),
                Score("DRAW", "REGULAR", Time(1, 1), Time(1, 0)),
                listOf(Referee(150766, "Sunny Gill", "REFEREE", "England"))
            ),
            Match(
                436222, "2024-03-09T15:00:00Z", "FINISHED", "2024-03-13T05:21:29Z",
                Team(76, "Wolverhampton Wanderers FC", "Wolverhampton", "WOL", "https://crests.football-data.org/76.svg"),
                Team(63, "Fulham FC", "Fulham", "FUL", "https://crests.football-data.org/63.svg"),
                Score("HOME_TEAM", "REGULAR", Time(2, 1), Time(0, 0)),
                listOf(Referee(11378, "Tony Harrington", "REFEREE", "England"))
            ),
            Match(
                436214, "2024-03-09T17:30:00Z", "FINISHED", "2024-03-10T00:21:22Z",
                Team(57, "Arsenal FC", "Arsenal", "ARS", "https://crests.football-data.org/57.png"),
                Team(402, "Brentford FC", "Brentford", "BRE", "https://crests.football-data.org/402.png"),
                Score("HOME_TEAM", "REGULAR", Time(2, 1), Time(1, 1)),
                listOf(Referee(11446, "Robert Jones", "REFEREE", "England"))
            ),
            Match(
                436215, "2024-03-10T13:00:00Z", "FINISHED", "2024-03-10T20:20:58Z",
                Team(58, "Aston Villa FC", "Aston Villa", "AVL", "https://crests.football-data.org/58.png"),
                Team(73, "Tottenham Hotspur FC", "Tottenham", "TOT", "https://crests.football-data.org/73.svg"),
                Score("AWAY_TEAM", "REGULAR", Time(0, 4), Time(0, 0)),
                listOf(Referee(11443, "Chris Kavanagh", "REFEREE", "England"))
            ),
            Match(
                436216, "2024-03-10T14:00:00Z", "FINISHED", "2024-03-10T20:20:58Z",
                Team(397, "Brighton & Hove Albion FC", "Brighton Hove", "BHA", "https://crests.football-data.org/397.svg"),
                Team(351, "Nottingham Forest FC", "Nottingham", "NOT", "https://crests.football-data.org/351.png"),
                Score("HOME_TEAM", "REGULAR", Time(1, 0), Time(1, 0)),
                listOf(Referee(11405, "Michael Salisbury", "REFEREE", "England"))
            ),
            Match(
                436221, "2024-03-10T14:00:00Z", "FINISHED", "2024-03-10T20:20:58Z",
                Team(563, "West Ham United FC", "West Ham", "WHU", "https://crests.football-data.org/563.png"),
                Team(328, "Burnley FC", "Burnley", "BUR", "https://crests.football-data.org/328.png"),
                Score("DRAW", "REGULAR", Time(2, 2), Time(0, 2)),
                listOf(Referee(11469, "Darren England", "REFEREE", "England"))
            ),
            Match(
                436219, "2024-03-10T15:45:00Z", "FINISHED", "2024-03-13T05:21:29Z",
                Team(64, "Liverpool FC", "Liverpool", "LIV", "https://crests.football-data.org/64.png"),
                Team(65, "Manchester City FC", "Man City", "MCI", "https://crests.football-data.org/65.png"),
                Score("DRAW", "REGULAR", Time(1, 1), Time(0, 1)),
                listOf(Referee(11605, "Michael Oliver", "REFEREE", "England"))
            ),
            Match(
                436217, "2024-03-11T20:00:00Z", "FINISHED", "2024-03-13T05:21:29Z",
                Team(61, "Chelsea FC", "Chelsea", "CHE", "https://crests.football-data.org/61.png"),
                Team(67, "Newcastle United FC", "Newcastle", "NEW", "https://crests.football-data.org/67.png"),
                Score("HOME_TEAM", "REGULAR", Time(3, 2), Time(1, 1)),
                listOf(Referee(11327, "John Brooks", "REFEREE", "England"))
            )
        )
        MatchesScreen(testList = testList)
    }
}*/
