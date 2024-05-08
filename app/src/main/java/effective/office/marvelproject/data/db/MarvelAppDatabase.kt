package effective.office.marvelproject.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import effective.office.marvelproject.data.db.dao.CharacterDao
import effective.office.marvelproject.data.db.models.CharacterEntity

@Database(entities = [CharacterEntity::class], version = 1, exportSchema = false)
abstract class MarvelAppDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
}