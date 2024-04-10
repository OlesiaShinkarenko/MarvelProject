package effective.office.marvelproject.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import effective.office.marvelproject.data.db.models.RemoteKey

@Dao
interface RemoteKeyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(remoteKey: RemoteKey)

    @Query("SELECT * FROM remote_keys WHERE label = :id")
    suspend fun remoteKeyById(id: String): RemoteKey

    @Query("DELETE FROM remote_keys WHERE label = :id")
    suspend fun deleteById(id: String)
}