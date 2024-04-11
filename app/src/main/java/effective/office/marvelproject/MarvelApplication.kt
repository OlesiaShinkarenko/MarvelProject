package effective.office.marvelproject

import android.app.Application
import effective.office.marvelproject.data.db.MarvelAppDatabase
import effective.office.marvelproject.domain.repositories.MarvelRepository
import timber.log.Timber

class MarvelApplication : Application() {
    private val database: MarvelAppDatabase by lazy {
        MarvelAppDatabase.getInstance(this)
    }
    val repository by lazy {
        MarvelRepository(database)
    }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}