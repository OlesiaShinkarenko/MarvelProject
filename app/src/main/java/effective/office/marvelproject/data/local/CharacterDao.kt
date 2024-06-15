package effective.office.marvelproject.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import effective.office.marvelproject.data.local.model.CharacterEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(characters: List<CharacterEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(characters: CharacterEntity)

    @Query("SELECT * FROM  characters")
    fun getCharacters(): Flow<List<CharacterEntity>>

    @Query("SELECT character_id FROM characters order by random() limit 1")
    fun getRandomId(): Int

    @Query("SELECT * FROM characters where character_id = :id")
    suspend fun getCharacter(id: Int): CharacterEntity?

    @Query("DELETE FROM characters")
    suspend fun clear()
}