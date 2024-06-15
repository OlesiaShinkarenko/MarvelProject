package effective.office.marvelproject.notification.services

import android.content.Context
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability


fun checkGooglePlayServices(context: Context): Boolean {
    val status = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context)
    return status == ConnectionResult.SUCCESS
}