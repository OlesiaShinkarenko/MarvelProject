package effective.office.marvelproject.data.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import effective.office.marvelproject.MarvelApplication
import effective.office.marvelproject.data.db.dao.CharacterDao
import effective.office.marvelproject.data.db.dao.RemoteKeyDao
import effective.office.marvelproject.data.db.models.CharacterEntity
import effective.office.marvelproject.data.db.models.RemoteKey

@Database(entities = [CharacterEntity::class, RemoteKey::class], version = 1, exportSchema = false)
abstract class MarvelAppDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
    abstract fun remoteKeyDao(): RemoteKeyDao

    companion object {
        private var INSTANCE: MarvelAppDatabase? = null
        fun getInstance(application: MarvelApplication): MarvelAppDatabase =
            INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    application.applicationContext,
                    MarvelAppDatabase::class.java,
                    "marvel_app"
                ).build()
                INSTANCE = instance
                instance
            }
    }
}