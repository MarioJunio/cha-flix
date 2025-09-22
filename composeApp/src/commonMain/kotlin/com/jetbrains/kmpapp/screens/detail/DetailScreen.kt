package com.jetbrains.kmpapp.screens.detail

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.TvOff
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.mj.shared.infra.domain.model.Season
import br.com.mj.shared.infra.domain.model.Show
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import coil3.compose.SubcomposeAsyncImage
import components.Chip
import components.EmptyScreenContent
import components.EpisodeFrame
import kmp_app_template.composeapp.generated.resources.Res
import kmp_app_template.composeapp.generated.resources.no_episodes_found
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

data class DetailScreen(private val show: Show) : Screen {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val viewModel = koinViewModel<DetailViewModel>()
        val scrollState = rememberScrollState()

        viewModel.getSeasons(show.id)

        val uiState by viewModel.uiState.collectAsStateWithLifecycle(initialValue = null)
        val navigator = LocalNavigator.current

        Scaffold(
            containerColor = Color.White,
            topBar = {
                MediumTopAppBar(
                    title = { },
                    navigationIcon = {
                        IconButton(onClick = {
                            navigator?.pop()
                        }) {
                            Box(
                                modifier = Modifier.clip(CircleShape)
                                    .background(color = Color.White.copy(alpha = .8f))
                                    .padding(8.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.ArrowBackIosNew,
                                    contentDescription = "Back"
                                )
                            }
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Transparent
                    )
                )
            }) {
            Column(
                modifier = Modifier.verticalScroll(scrollState)
            ) {
                SubcomposeAsyncImage(
                    model = show.image?.original,
                    contentDescription = show.name,
                    contentScale = ContentScale.Crop,
                    loading = {
                        Box(
                            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(modifier = Modifier.size(48.dp))
                        }
                    },
                    modifier = Modifier.fillMaxWidth().aspectRatio(1f)
                )

                Column(
                    modifier = Modifier.padding(8.dp).fillMaxWidth()
                ) {
                    Text(show.name, style = MaterialTheme.typography.titleMedium)

                    Row {
                        if (uiState?.seasons != null) {
                            Chip(
                                "${uiState?.seasons?.size} seasons", fontWeight = FontWeight.W600
                            )
                            Spacer(Modifier.size(8.dp))
                        }

                        LazyRow {
                            items(show.genres) { genre ->
                                Chip(genre, color = Color.DarkGray.copy(alpha = .1f))
                                Spacer(Modifier.size(4.dp))
                            }
                        }
                    }

                    Spacer(Modifier.size(4.dp))

                    Text(show.summary ?: "", style = MaterialTheme.typography.bodyMedium)
                }

                SeasonTabs(uiState)
            }
        }
    }

    @Composable
    fun SeasonTabs(uiState: UiState?) {
        AnimatedContent(uiState) { uiState ->
            if (uiState?.isLoading == true) {
                Box(
                    contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()
                ) {
                    CircularProgressIndicator()
                }
            } else if (uiState?.seasons?.isNotEmpty() == true) {
                DisplaySeasons(uiState.seasons)
            } else {
                EmptyScreenContent(
                    Modifier.padding(top = 32.dp).fillMaxSize(),
                    message = stringResource(Res.string.no_episodes_found),
                    icon = Icons.Default.TvOff
                )
            }
        }
    }

    @Composable
    fun DisplaySeasons(seasons: List<Season>) {
        val viewModel = koinViewModel<DetailViewModel>()
        val selectedTabIndex = remember { mutableIntStateOf(0) }
        val pagerState = rememberPagerState { seasons.size }
        val coroutineScope = rememberCoroutineScope()

        LaunchedEffect(pagerState) {
            snapshotFlow { pagerState.currentPage }.collect { page ->
                selectedTabIndex.intValue = page
            }
        }

        Column(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp)
        ) {
            ScrollableTabRow(
                selectedTabIndex = selectedTabIndex.intValue,
                containerColor = Color.LightGray.copy(alpha = .1f),
                contentColor = Color.Black,
                divider = {
                    HorizontalDivider(
                        modifier = Modifier.fillMaxWidth(),
                        color = Color.LightGray.copy(alpha = 0.3f),
                        thickness = 2.dp
                    )
                },
                indicator = { tabPositions ->
                    TabRowDefaults.SecondaryIndicator(
                        modifier = Modifier.tabIndicatorOffset(
                            tabPositions[selectedTabIndex.intValue]
                        ), height = 2.dp, color = Color.LightGray
                    )
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                seasons.forEachIndexed { index, season ->
                    Tab(
                        selected = selectedTabIndex.intValue == index,
                        onClick = {
                            selectedTabIndex.intValue = index

                            coroutineScope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        },
                        text = { Text("Season ${season.season}") },
                    )
                }
            }

            HorizontalPager(state = pagerState) { page ->
                val season = seasons[page]

                Column {
                    season.episodes.map {
                        EpisodeFrame(it, viewModel::markAsWatched)
                    }
                }
            }
        }
    }

}

