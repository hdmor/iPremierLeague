package m.eight.ipl.presentation

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import m.eight.ipl.presentation.matches.MatchesScreen
import m.eight.ipl.presentation.standings.StandingsScreen
import m.eight.ipl.presentation.ui.theme.IPLTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IPLTheme {
                val viewModel: CurrentSeasonViewModel = hiltViewModel()
                val state = viewModel.state.value

                val navController = rememberNavController()
                val currentBackStackEntry by navController.currentBackStackEntryAsState()

                var scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
                val configuration = LocalConfiguration.current
                if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
                val snackBarHostState = remember { SnackbarHostState() }

                Scaffold(
                    modifier = Modifier.fillMaxSize().nestedScroll(scrollBehavior.nestedScrollConnection),
                    topBar = {
                        TopAppBar(
                            title = {
                                FlowRow(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                    if (currentBackStackEntry?.destination?.route?.contains(Screens.TableScreen.route) == true)
                                        Button(onClick = {}) { Text(text = "Table") }
                                    else OutlinedButton(
                                        onClick = {
                                            if (state.startDate.isNotEmpty() && state.currentMatchDay > 0)
                                                navController.navigate(route = "${Screens.TableScreen.route}/${state.startDate}/${state.currentMatchDay}")
                                        }
                                    ) { Text(text = "Table") }
                                    if (currentBackStackEntry?.destination?.route?.contains(Screens.MatchesScreen.route) == true)
                                        Button(onClick = {}) { Text(text = "Matches") }
                                    else OutlinedButton(
                                        onClick = {
                                            if (state.currentMatchDay > 0)
                                                navController.navigate(route = "${Screens.MatchesScreen.route}/${state.currentMatchDay}")
                                        }
                                    ) { Text(text = "Matches") }
                                }
                            },
                            scrollBehavior = scrollBehavior
                        )
                    },
                    snackbarHost = { SnackbarHost(hostState = snackBarHostState) }
                ) { paddingValues ->

                    Box(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
                        HorizontalDivider()
                        NavHost(navController = navController, startDestination = Screens.TableScreen.route + "/{startDate}/{currentMatchDay}") {
                            composable(
                                route = Screens.TableScreen.route + "/{startDate}/{currentMatchDay}",
                                arguments = listOf(
                                    navArgument(name = "startDate") { type = NavType.StringType },
                                    navArgument(name = "currentMatchDay") { type = NavType.IntType }
                                )
                            ) { StandingsScreen() }
                            composable(
                                route = Screens.MatchesScreen.route + "/{currentMatchDay}",
                                arguments = listOf(navArgument(name = "currentMatchDay") { type = NavType.IntType })
                            ) { MatchesScreen() }
                        }
                        if (state.isLoading) CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                        if (state.startDate.isNotEmpty() && state.currentMatchDay > 0)
                            navController.navigate(route = "${Screens.TableScreen.route}/${state.startDate}/${state.currentMatchDay}")
                    }
                }
            }
        }
    }
}


sealed class Screens(val route: String) {
    data object MatchesScreen : Screens("matches")
    data object TableScreen : Screens("table")
}