package effective.office.marvelproject.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import effective.office.marvelproject.data.local.model.CharacterEntity

@Database(entities = [CharacterEntity::class], version = 1, exportSchema = false)
abstract class MarvelAppDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
}