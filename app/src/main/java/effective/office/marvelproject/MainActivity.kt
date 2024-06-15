package effective.office.marvelproject

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.AndroidEntryPoint
import effective.office.marvelproject.notification.checkGooglePlayServices
import effective.office.marvelproject.presentation.navigation.NavGraph
import effective.office.marvelproject.ui.theme.AppTheme
import timber.log.Timber


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (checkGooglePlayServices(this)) {
            FirebaseMessaging.getInstance().token.addOnCompleteListener(
                OnCompleteListener { task ->
                    if (!task.isSuccessful) {
                        return@OnCompleteListener
                    }

                    val token = task.result
                    Timber.tag("TOKEN").d(token)
                }
            )
        }
        setContent {
            AppTheme {
                Surface(
                    color = AppTheme.colors.backgroundColor
                ) {
                    val permissionState =
                        rememberPermissionState(permission = Manifest.permission.POST_NOTIFICATIONS)

                    LaunchedEffect(key1 = Unit) {
                        permissionState.launchPermissionRequest()
                    }
                    NavGraph()
                }
            }
        }
    }
}