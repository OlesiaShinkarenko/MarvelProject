package effective.office.marvelproject.ui.theme

import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

object Elevation {
    val elevation10
        @Composable
        get() = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        )
}