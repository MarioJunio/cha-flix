package com.jetbrains.kmpapp.screens.shows

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.FolderOff
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.mj.shared.infra.domain.model.Show
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import components.EmptyScreenContent
import com.jetbrains.kmpapp.screens.detail.DetailScreen
import components.ErrorScreen
import components.ShowFrame
import kmp_app_template.composeapp.generated.resources.Res
import kmp_app_template.composeapp.generated.resources.no_shows_found
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

object ShowsScreen : Screen {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val viewModel = koinViewModel<ListViewModel>()
        val shows by viewModel.shows.collectAsStateWithLifecycle()

        // search for shows when the text changes
        LaunchedEffect(viewModel.submittedText.value) {
            viewModel.search()
        }

        Scaffold(
            containerColor = Color(0xffF5F5F5),
        ) { paddingValues ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxWidth()
            ) {
                OutlinedTextField(
                    viewModel.searchText.value,
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                    onValueChange = { viewModel.searchText.value = it },
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color.White,
                        focusedContainerColor = Color.White,
                        disabledContainerColor = Color.White,
                        errorContainerColor = Color.White,
                        errorIndicatorColor = Color.LightGray,
                        focusedIndicatorColor = Color.LightGray,
                        unfocusedIndicatorColor = Color.LightGray,
                        errorLabelColor = Color.Black,
                        focusedLabelColor = Color.Black,
                        unfocusedLabelColor = Color.Black,
                        cursorColor = Color.Black,
                        unfocusedPlaceholderColor = Color.DarkGray,
                        focusedPlaceholderColor = Color.DarkGray,
                    ),
                    suffix = {
                        if (viewModel.searchText.value.isNotEmpty())
                            Icon(
                                imageVector = Icons.Default.Clear,
                                contentDescription = "Clear",
                                modifier = Modifier.clickable {
                                    viewModel.searchText.value = ""
                                    viewModel.submittedText.value = ""
                                }
                            )
                    },
                    placeholder = {
                        Text("Search shows by name")
                    },
                    label = {
                        Text("Search shows")
                    },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    singleLine = true,
                    keyboardActions = KeyboardActions(
                        onDone = {
                            viewModel.submittedText.value = viewModel.searchText.value
                        })
                )

                Spacer(Modifier.height(8.dp))

                AnimatedContent(shows) { showState ->
                    if (showState.error?.isNotEmpty() == true) {
                        ErrorScreen(
                            message = showState.error,
                            icon = Icons.Filled.Error,
                            modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp)
                        )
                    } else if (showState.isLoading == true) {
                        Box(
                            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    } else if (showState.data?.isNotEmpty() == true) {
                        ShowsGrid(
                            shows = shows.data ?: emptyList(),
                        )
                    } else {
                        EmptyScreenContent(
                            Modifier.fillMaxSize(),
                            message = stringResource(Res.string.no_shows_found),
                            icon = Icons.Filled.FolderOff
                        )
                    }
                }
            }
        }

    }

    @Composable
    private fun ShowsGrid(
        shows: List<Show>,
        modifier: Modifier = Modifier,
    ) {
        val navigator = LocalNavigator.current

        LazyVerticalGrid(
            columns = GridCells.Adaptive(128.dp),
            modifier = modifier.fillMaxSize(),
        ) {
            items(shows, key = { it.id }) { show ->
                ShowFrame(
                    show = show,
                    onClick = {
                        navigator?.push(DetailScreen(show))
                    },
                    modifier = Modifier.height(200.dp)
                )
            }
        }
    }
}



