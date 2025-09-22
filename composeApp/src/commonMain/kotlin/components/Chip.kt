package components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun Chip(text: String, color: Color? = null, fontWeight: FontWeight = FontWeight.W400) {
    Text(
        text,
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.bodySmall.copy(fontWeight = fontWeight),
        modifier = Modifier.clip(RoundedCornerShape(4.dp))
            .background(color = color ?: Color.DarkGray.copy(alpha = .1f))
            .padding(horizontal = 6.dp, vertical = 2.dp)
    )
}