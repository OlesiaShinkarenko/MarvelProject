package effective.office.marvelproject

import android.app.Application
import timber.log.Timber

class MarvelApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}