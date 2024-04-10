package effective.office.marvelproject.data.db.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import effective.office.marvelproject.data.db.models.CharacterEntity

@Dao
interface CharacterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(characters: List<CharacterEntity>)

    @Query("SELECT * FROM  characters")
    fun getCharacters(): PagingSource<Int, CharacterEntity>

    @Query("DELETE FROM characters")
    suspend fun clear()
}