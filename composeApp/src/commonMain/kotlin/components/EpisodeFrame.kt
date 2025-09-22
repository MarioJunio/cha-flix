package components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import br.com.mj.shared.infra.domain.model.Episode
import coil3.compose.SubcomposeAsyncImage

/**
 * Episode frame reusable component
 */
@Composable
fun EpisodeFrame(episode: Episode, markWatched: (Int, Boolean) -> Unit) {
    val watched = remember { mutableStateOf(episode.isWatched) }

    Box(
        modifier = Modifier.padding(8.dp)
    ) {
        Column(
            modifier = Modifier.clip(MaterialTheme.shapes.small).border(
                border = BorderStroke(
                    width = 1.dp, color = Color.LightGray.copy(alpha = .3f)
                ), shape = MaterialTheme.shapes.small
            ).background(color = Color.LightGray.copy(alpha = .1f))
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    SubcomposeAsyncImage(
                        model = episode.image.medium,
                        contentDescription = episode.name,
                        contentScale = ContentScale.Crop,
                        loading = {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator(modifier = Modifier.size(24.dp))
                            }
                        },
                        modifier = Modifier.size(60.dp).aspectRatio(1f)
                    )

                    Spacer(Modifier.size(8.dp))

                    Column {
                        Text(
                            episode.name,
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.W700
                        )

                        Row {
                            Text(
                                "${episode.runtime}m", style = MaterialTheme.typography.bodySmall
                            )
                            Spacer(Modifier.size(8.dp))
                            Text(
                                episode.airdate.year.toString(),
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                    }
                }

                Checkbox(
                    checked = watched.value, onCheckedChange = {
                        watched.value = it
                        markWatched(episode.id, it)
                    })
            }

            Spacer(modifier = Modifier.fillMaxWidth())

            Text(
                episode.summary,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
            )
        }
    }
}