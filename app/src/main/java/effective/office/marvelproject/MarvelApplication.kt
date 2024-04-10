package effective.office.marvelproject

import android.app.Application
import effective.office.marvelproject.data.db.MarvelAppDatabase
import timber.log.Timber

class MarvelApplication : Application() {
    private val database: MarvelAppDatabase by lazy {
        MarvelAppDatabase.getInstance(this)
    }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}