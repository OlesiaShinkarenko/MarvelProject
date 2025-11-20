package effective.office.marvelproject.presentation.components

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

@Composable
fun ShowToast(context: Context, error: Int?) {
    error?.let {
        Toast.makeText(context, stringResource(id = error), Toast.LENGTH_SHORT)
            .show()
    }
}